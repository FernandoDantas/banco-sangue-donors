package br.com.myapk.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myapk.api.v1.BackLinks;
import br.com.myapk.core.security.BackSecurity;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointerController {

	@Autowired
	private BackLinks backLinks;

	@Autowired
	private BackSecurity backSecurity;
	
	@GetMapping
	@Operation(hidden = true)
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();

		if (backSecurity.canConsultUsersGroupsPermissions()) {
			rootEntryPointModel.add(backLinks.linkToGroups("groups"));
			rootEntryPointModel.add(backLinks.linkToUsers("users"));
			rootEntryPointModel.add(backLinks.linkToPermissions("permissions"));

		}
		return rootEntryPointModel;
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}

}
