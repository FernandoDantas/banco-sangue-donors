package br.com.myapk.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionModel extends RepresentationModel<PermissionModel>{

	@Schema(example = "1")
	private Long id;

	@Schema(example = "CONSULTAR_USUARIOS")
	private String name;

	@Schema(example = "Allows querying users")
	private String description;
	
}
