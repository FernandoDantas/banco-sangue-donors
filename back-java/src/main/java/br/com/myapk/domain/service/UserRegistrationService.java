package br.com.myapk.domain.service;

import br.com.myapk.domain.exception.BusinessException;
import br.com.myapk.domain.exception.UserNotFoundException;
import br.com.myapk.domain.model.Grouping;
import br.com.myapk.domain.model.Tenant;
import br.com.myapk.domain.model.Users;
import br.com.myapk.domain.repository.TenantRepository;
import br.com.myapk.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserRegistrationService {


	@Autowired
	private TenantRepository tenantRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private GroupingResgistrationService groupingResgistrationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;  
	
	@Transactional
    public Users save(Users user) {		
		usersRepository.detach(user);
    	
    	Optional<Users> existingUser = usersRepository.findByUserEmail(user.getUserEmail());
    	
    	if(existingUser.isPresent() && !existingUser.get().equals(user)) {
    		throw new BusinessException(
    				String.format("There is already a registered user with the email %s", user.getUserEmail()));
    	}
    	
    	if(user.isNew()) {
			//Save the tenant by username when the user make registration
			Tenant tenantUser = createNewTenant(user.getUserName());

			user.setTenant(tenantUser);
    		user.setCryptedPass(passwordEncoder.encode(user.getCryptedPass()));
		}
    	
    	user.setStatus("1");
        return usersRepository.save(user);
    }

	private Tenant createNewTenant(String name) {
		Tenant tenant = new Tenant();
		tenant.setTenantName(name);
		tenant.setStatus("1");
		return tenantRepository.save(tenant);
	}

	@Transactional
	public void updateCryptedPass(Long userId, Long tenantId, String actualCyptedPass, String newCyptedPass) {
		Users user = seekOrFail(userId, tenantId);
		
		if(!passwordEncoder.matches(actualCyptedPass, user.getCryptedPass())) {
			throw new BusinessException("Current password entered does not match the user's password.");
		}
		
		user.setCryptedPass(passwordEncoder.encode(newCyptedPass));
	}
	
	public Users seekOrFail(Long userId, Long tenantId) {
		return usersRepository.findByIdAndTenantId(userId, tenantId)
			.orElseThrow(() -> new UserNotFoundException(userId));
	}

	public Users seekOrFailByUserId(Long userId) {
		return usersRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Transactional
	public void disassociateGrouping(Long userId, Long groupingId) {
		Users user = seekOrFailByUserId(userId);
		Grouping grouping = groupingResgistrationService.seekOrFail(groupingId);
		
		user.removeGroup(grouping);		
	}
	
	@Transactional
	public void associateGrouping(Long userId, Long groupingId) {
		Users user = seekOrFailByUserId(userId);
		Grouping grouping = groupingResgistrationService.seekOrFail(groupingId);
		
		user.addGroup(grouping);
	}
	
}
