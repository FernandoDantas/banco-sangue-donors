package br.com.myapk.api.v1.openapi.controller;

import br.com.myapk.api.exceptionhandler.Problem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.myapk.api.v1.model.PermissionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grouping")
@Tag(name = "Grouping")
public interface GroupPermissionControllerOpenApi {

	@Operation(summary = "Lists the permissions associated with a group", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid group id", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "Group not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    CollectionModel<PermissionModel> list(
            @Parameter(description = "Group id", example = "1", required = true) Long groupId);


    @Operation(summary = "Permission association with group", responses = {
            @ApiResponse(responseCode = "204", description = "Successful association"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> associate(
            @Parameter(description = "Group id", example = "1", required = true) Long groupId,
            @Parameter(description = "Permission id", example = "1", required = true) Long permissionId);

    @Operation(summary = "Permission disassociation with group", responses = {
            @ApiResponse(responseCode = "204", description = "Successfully disassociated"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> disassociate(
            @Parameter(description = "Group id", example = "1", required = true) Long groupId,
            @Parameter(description = "Permission id", example = "1", required = true) Long permissionId);
	
}


