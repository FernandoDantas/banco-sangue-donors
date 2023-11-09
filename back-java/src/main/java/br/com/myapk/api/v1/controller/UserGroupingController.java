package br.com.myapk.api.v1.controller;

import br.com.myapk.domain.service.LoggedTokenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.myapk.api.v1.BackLinks;
import br.com.myapk.api.v1.assembler.GroupingModelAssembler;
import br.com.myapk.api.v1.model.GroupingModel;
import br.com.myapk.api.v1.openapi.controller.UserGroupControllerOpenApi;
import br.com.myapk.core.security.BackSecurity;
import br.com.myapk.core.security.CheckSecurity;
import br.com.myapk.domain.model.Users;
import br.com.myapk.domain.service.UserRegistrationService;

@RestController
@RequestMapping(path = "/v1/users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupingController implements UserGroupControllerOpenApi {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private LoggedTokenUserService loggedTokenUserService;

    @Autowired
    private GroupingModelAssembler groupingModelAssembler;

    @Autowired
    private BackLinks backLinks;

    @Autowired
    private BackSecurity backSecurity;

    @CheckSecurity.UsersGroupsPermissions.CanConsult
    @Override
    @GetMapping
    public CollectionModel<GroupingModel> list(@PathVariable Long userId) {
        Users user = userRegistrationService.seekOrFailByUserId(userId);

        CollectionModel<GroupingModel> groupingsModel = groupingModelAssembler.toCollectionModel(user.getGroupings())
                .removeLinks();

        if (backSecurity.canEditUsersGroupsPermissions()) {
            groupingsModel.add(backLinks.linkToUserGroupAssociate(userId, "associate"));

            groupingsModel.getContent().forEach(groupModel -> {
                groupModel.add(backLinks.linkToUserGroupDesassociate(
                        userId, groupModel.getId(), "disassociate"));
            });
        }
        return groupingsModel;
    }

    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @Override
    @DeleteMapping("/{groupingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long groupingId) {
        userRegistrationService.disassociateGrouping(userId, groupingId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @Override
    @PutMapping("/{groupingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long userId, @PathVariable Long groupingId) {
        userRegistrationService.associateGrouping(userId, groupingId);

        return ResponseEntity.noContent().build();
    }
}
