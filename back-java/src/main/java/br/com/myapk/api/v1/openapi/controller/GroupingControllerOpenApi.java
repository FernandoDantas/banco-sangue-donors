package br.com.myapk.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

import br.com.myapk.api.v1.model.GroupingModel;
import br.com.myapk.api.v1.model.input.GroupInput;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grouping")
public interface GroupingControllerOpenApi {

	@Operation(summary = "List the groups")
	CollectionModel<GroupingModel> list();

	@Operation(summary = "Search a group by ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Invalid group id",
					content = {@Content(schema = @Schema(ref = "Problem")) }),
			@ApiResponse(responseCode = "404", description = "Group not found",
					content = {@Content(schema = @Schema(ref = "Problem")) }),
	})
	GroupingModel search(@Parameter(description = "Group ID", example = "1", required = true) Long grupoId);

	@Operation(summary = "Register a group", responses = {
			@ApiResponse(responseCode = "201", description = "Registered group"),
	})
	GroupingModel add(@Parameter(description = "Representation of a new group", required = true) GroupInput groupInput);

	@Operation(summary = "Update a group by ID", responses = {
			@ApiResponse(responseCode = "200", description = "Updated group"),
			@ApiResponse(responseCode = "404", description = "Group not found", content = {
					@Content(schema = @Schema(ref = "Problem"))
			}),
	})
	GroupingModel update(
			@Parameter(description = "ID of a group", example = "1", required = true) Long grupoId,
			@RequestBody(description = "Representation of a group with the new data", required = true) GroupInput groupInput);

	@Operation(summary = "Delete a group by ID", responses = {
			@ApiResponse(responseCode = "204", description = "Excluded group"),
			@ApiResponse(responseCode = "404", description = "Group not found", content = {
					@Content(schema = @Schema(ref = "Problem"))
			}),
	})
	ResponseEntity<Void> remove(
			@Parameter(description = "ID de um grupo") Long grupoId);
	
}
