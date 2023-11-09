package br.com.myapk.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "groups")
@Getter
@Setter
public class GroupingModel extends RepresentationModel<GroupingModel>{

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Manager")
	private String name;

}
