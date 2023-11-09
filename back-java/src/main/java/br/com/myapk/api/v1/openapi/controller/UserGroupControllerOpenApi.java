package br.com.myapk.api.v1.openapi.controller;

import br.com.myapk.api.v1.model.GroupingModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Users")
public interface UserGroupControllerOpenApi {

    @Operation(summary = "Lists the groups associated with a user", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "\n" +
                    "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    CollectionModel<GroupingModel> list(
            @Parameter(description = "User ID", example = "1", required = true)
            Long userId);


    @Operation(summary = "User group disassociation", responses = {
            @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Successfully disassociated", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> disassociate(
            @Parameter(description = "User ID", example = "1", required = true)
            Long userId,
    @Parameter(description = "Group ID", example = "1", required = true)
    Long groupingId);

    @Operation(summary = "User group membership", responses = {
            @ApiResponse(responseCode = "204", description = "Successful association"),
            @ApiResponse(responseCode = "404", description = "User or group not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> associate(
            @Parameter(description = "User ID", example = "1", required = true)
            Long userId,
            @Parameter(description = "Group ID", example = "1", required = true)
            Long groupingId);


}
