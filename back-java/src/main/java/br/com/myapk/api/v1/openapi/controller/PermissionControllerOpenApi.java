package br.com.myapk.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

import br.com.myapk.api.v1.model.PermissionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permission")
public interface PermissionControllerOpenApi {

	@Operation(summary = "List the permissions")
	CollectionModel<PermissionModel> list();

}
