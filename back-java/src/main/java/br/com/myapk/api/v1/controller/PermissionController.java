package br.com.myapk.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myapk.api.v1.assembler.PermissionModelAssembler;
import br.com.myapk.api.v1.model.PermissionModel;
import br.com.myapk.api.v1.openapi.controller.PermissionControllerOpenApi;
import br.com.myapk.core.security.CheckSecurity;
import br.com.myapk.domain.model.Permission;
import br.com.myapk.domain.repository.PermissionRepository;

@RestController
@RequestMapping(path = "/v1/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi{

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PermissionModelAssembler permissionModelAssembler;
	
	@CheckSecurity.UsersGroupsPermissions.CanConsult	
	@Override
	@GetMapping
	public CollectionModel<PermissionModel> list() {
		List<Permission> allPermissions = permissionRepository.findAll();
		
		return permissionModelAssembler.toCollectionModel(allPermissions);
	}

}
