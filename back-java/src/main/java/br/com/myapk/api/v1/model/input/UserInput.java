package br.com.myapk.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInput {

	@Schema(example = "João da Silva", required = true)
	@NotBlank
	private String userName;
	
	@Schema(example = "joao.ger@myapk.com.br", required = true)
	@NotBlank
	@Email
	private String userEmail;
	
}
