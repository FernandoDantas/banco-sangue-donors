package br.com.myapk.api.v1.controller;

import br.com.myapk.api.v1.assembler.UserInputDisassembler;
import br.com.myapk.api.v1.assembler.UsersModelAssembler;
import br.com.myapk.api.v1.model.UsersModel;
import br.com.myapk.api.v1.model.input.CryptedPassInput;
import br.com.myapk.api.v1.model.input.UserInput;
import br.com.myapk.api.v1.model.input.UserWithPasswordInput;
import br.com.myapk.api.v1.openapi.controller.UsersControllerOpenApi;
import br.com.myapk.core.security.CheckSecurity;
import br.com.myapk.domain.model.Users;
import br.com.myapk.domain.repository.UsersRepository;
import br.com.myapk.domain.service.LoggedTokenUserService;
import br.com.myapk.domain.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController implements UsersControllerOpenApi{

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserRegistrationService userRegistrationService;

	@Autowired
	private LoggedTokenUserService loggedTokenUserService;
	
	@Autowired
	private UsersModelAssembler usersModelAssembler;
	
	@Autowired
	private UserInputDisassembler userInputDisassembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@GetMapping
	public CollectionModel<UsersModel> list(@AuthenticationPrincipal Jwt jwt){

		List<Users> allUsers = usersRepository.findByTenantId(loggedTokenUserService.loggedUser(jwt));

		return usersModelAssembler.toCollectionModel(allUsers);
	}

	@CheckSecurity.UsersGroupsPermissions.CanConsult
	@GetMapping("/{userId}")
	public UsersModel search(@PathVariable Long userId, @AuthenticationPrincipal Jwt jwt) {
		Users user = userRegistrationService.seekOrFail(userId, loggedTokenUserService.loggedUser(jwt));
		
		return usersModelAssembler.toModel(user);
	}
	
	@Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersModel register(@RequestBody @Valid UserWithPasswordInput userInput) {
        Users user = userInputDisassembler.toDomainObject(userInput);
        user = userRegistrationService.save(user);
        
        return usersModelAssembler.toModel(user);
    }
	
	@CheckSecurity.UsersGroupsPermissions.CanChangeUser
	@Override
	@PutMapping("/{userId}")
	public UsersModel update(@PathVariable Long userId,
			@AuthenticationPrincipal Jwt jwt,
			@RequestBody @Valid UserInput userInput){
		Users actualUser = userRegistrationService.seekOrFail(userId, loggedTokenUserService.loggedUser(jwt));
		userInputDisassembler.copyToDomainObject(userInput, actualUser);
		actualUser = userRegistrationService.save(actualUser);
		
		return usersModelAssembler.toModel(actualUser);
	}

	@CheckSecurity.UsersGroupsPermissions.CanChangeOwnPassword
	@Override
	@PutMapping("/{userId}/cryptedPass")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> updateCryptedPass(@PathVariable Long userId, @AuthenticationPrincipal Jwt jwt,
												  @RequestBody @Valid CryptedPassInput cryptedPass) {
		userRegistrationService.updateCryptedPass(userId, loggedTokenUserService.loggedUser(jwt), cryptedPass.getActualCryptedPass(), cryptedPass.getNewCryptedPass());
		return ResponseEntity.noContent().build();
	}
	
}
