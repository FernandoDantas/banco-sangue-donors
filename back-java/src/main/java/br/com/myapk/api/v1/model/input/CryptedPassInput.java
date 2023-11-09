package br.com.myapk.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptedPassInput {

	@Schema(example = "123", required = true)
	@NotBlank
	private String actualCryptedPass;

	@Schema(example = "123", required = true)
	@NotBlank
	private String newCryptedPass;

}
