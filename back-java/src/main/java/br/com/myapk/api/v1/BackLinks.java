package br.com.myapk.api.v1;

import br.com.myapk.api.v1.controller.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BackLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));


	public Link linkToUser(Long userId, Jwt jwt, String rel) {
		return linkTo(methodOn(UsersController.class)
				.search(userId, jwt)).withRel(rel);
	}

	public Link linkToUser(Long userId, Jwt jwt) {
		return linkToUser(userId, jwt, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsers(String rel) {
		return linkTo(UsersController.class).withRel(rel);
	}

	public Link linkToUsers() {
		return linkToUsers(IanaLinkRelations.SELF.value());
	}

	public Link linkToUserGroupAssociate(Long userId, String rel) {
		return linkTo(methodOn(UserGroupingController.class)
				.associate(userId, null)).withRel(rel);
	}

	public Link linkToUserGroupDesassociate(Long userId, Long groupId, String rel) {
		return linkTo(methodOn(UserGroupingController.class)
				.disassociate(userId, groupId)).withRel(rel);
	}

	public Link linkToGroupingUser(Long userId, String rel) {
		return linkTo(methodOn(UserGroupingController.class)
				.list(userId)).withRel(rel);
	}

	public Link linkToGroupingUser(Long userId) {
		return linkToGroupingUser(userId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGroups(String rel) {
		return linkTo(GroupingController.class).withRel(rel);
	}

	public Link linkToGroups() {
		return linkToGroups(IanaLinkRelations.SELF.value());
	}

	public Link linkToGroupPermissions(Long groupId, String rel) {
		return linkTo(methodOn(GroupingPermissionController.class)
				.list(groupId)).withRel(rel);
	}

	public Link linkToPermissions(String rel) {
		return linkTo(PermissionController.class).withRel(rel);
	}

	public Link linkToPermissions() {
		return linkToPermissions(IanaLinkRelations.SELF.value());
	}

	public Link linkToGroupPermissions(Long groupId) {
		return linkToGroupPermissions(groupId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoPermissionAssociate(Long groupId, String rel) {
		return linkTo(methodOn(GroupingPermissionController.class)
				.associate(groupId, null)).withRel(rel);
	}

	public Link linkToGrupoPermissaoDesassociate(Long groupId, Long permissionId, String rel) {
		return linkTo(methodOn(GroupingPermissionController.class)
				.disassociate(groupId, permissionId)).withRel(rel);
	}
}
