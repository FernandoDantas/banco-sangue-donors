package br.com.myapk.api.v1.openapi.controller;

import br.com.myapk.api.v1.model.UsersModel;
import br.com.myapk.api.v1.model.input.CryptedPassInput;
import br.com.myapk.api.v1.model.input.UserInput;
import br.com.myapk.api.v1.model.input.UserWithPasswordInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Users")
public interface UsersControllerOpenApi {

	@Operation( summary = "List users")
    CollectionModel<UsersModel> list(@Parameter(description = "JWT token", example = "GciLCJleHAiOjE2Nj...", required = true) Jwt jwt);

      @Operation(summary = "Search user by ID", responses = {
              @ApiResponse(responseCode = "200"),
              @ApiResponse(responseCode = "400", description = "Invalid User ID", content = {
              @Content(schema = @Schema(ref = "Problem")) }),
              @ApiResponse(responseCode = "404", description = "User not found")
      })
      UsersModel search(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                        @Parameter(description = "JWT token", example = "GciLCJleHAiOjE2Nj...", required = true) Jwt jwt);

      @Operation(summary = "Register a user", responses = {
              @ApiResponse(responseCode = "201", description = "Registered user")
      })
    UsersModel register(
            @RequestBody(description = "Representation of a new user", required = true) UserWithPasswordInput userInput);
    
      @Operation(summary = "Update a user by ID", responses = {
          @ApiResponse(responseCode = "200", description = "Updated user"),
          @ApiResponse(responseCode = "404", description = "User not found", content = {
                @Content(schema = @Schema(ref = "Problem")) })
    })
    UsersModel update(
            @Parameter(description = "User ID", example = "1", required = true)Long userId,
            @Parameter(description = "JWT token", example = "GciLCJleHAiOjE2Nj...", required = true) Jwt jwt,
            @RequestBody(description = "Representation of a user with the new data", required = true) UserInput userInput);

      @Operation(summary = "Update a user's password", responses = {
              @ApiResponse(responseCode = "204", description = "Password changed successfully"),
              @ApiResponse(responseCode = "404", description = "User not found", content = {
                      @Content(schema = @Schema(ref = "Problem")) })
      })
    ResponseEntity<Void> updateCryptedPass(
            @Parameter(description = "User ID", example = "1", required = true) Long userId,
            @Parameter(description = "JWT token", example = "GciLCJleHAiOjE2Nj...", required = true) Jwt jwt,
            @RequestBody(description = "User password update representation", required = true)
            CryptedPassInput cryptedPassInput);
}
