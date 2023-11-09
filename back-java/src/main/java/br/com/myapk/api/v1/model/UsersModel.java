package br.com.myapk.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "users")
@Getter
@Setter
public class UsersModel extends RepresentationModel<UsersModel>{

	@Schema(example = "1")
    private Long id;

	@Schema(example = "joaodasilva@email.com")
	private String userEmail;

	@Schema(example = "1")
	private String status;

	@Schema(example = "João")
	private String userName;

	@Schema(example = "image.jpg")
	private String photo;

}
