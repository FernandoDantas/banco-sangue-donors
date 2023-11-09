package br.com.myapk.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.myapk.api.v1.model.input.GroupInput;
import br.com.myapk.domain.model.Grouping;

@Component
public class GroupInputDisassembler {
	 
	@Autowired
    private ModelMapper modelMapper;
	
	public Grouping toDomainObject(GroupInput groupInput) {
		return modelMapper.map(groupInput, Grouping.class);
	}
	
	public void copyToDomainObject(GroupInput groupInput, Grouping grouping) {
		modelMapper.map(groupInput, grouping);
	}

}
