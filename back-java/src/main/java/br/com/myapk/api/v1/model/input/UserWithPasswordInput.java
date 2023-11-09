package br.com.myapk.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserWithPasswordInput extends UserInput{

	@Schema(example = "123", required = true)
	@NotBlank
	private String cryptedPass;

}
