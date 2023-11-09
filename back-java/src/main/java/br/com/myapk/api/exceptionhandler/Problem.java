package br.com.myapk.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problem")
public class Problem {

	@Schema(example = "400")
	private Integer status;

	@Schema(example = "https://www.myapk.com.br/dados-invalidos")
	private String type;

	@Schema(example = "Invalid data")
	private String title;

	@Schema(example = "One or more fields are invalid. Please fill in correctly and try again.")
	private String detail;

	@Schema(example = "One or more fields are invalid. Please fill in correctly and try again.")
	private String userMessage;

	@Schema(example = "2022-08-01T18:09:02.70844Z")
	private OffsetDateTime timestamp;
	
	@Schema(description = "List of objects or fields that generated the error (optional)")
	private List<Object> objects;

	@Getter
	@Builder
	@Schema(example = "ObjectProblem")
	public static class Object {

		@Schema(example = "password")
		private String name;

		@Schema(example = "Password is mandatory")
		private String userMessage;
	}
	
}
