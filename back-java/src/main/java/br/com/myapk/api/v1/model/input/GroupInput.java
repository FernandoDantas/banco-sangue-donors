package br.com.myapk.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInput {

    @Schema(example = "1", required = true)
    @NotBlank
	private String name;
	
}
