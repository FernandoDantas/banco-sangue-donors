package br.com.myapk.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.myapk.domain.exception.EntityInUseException;
import br.com.myapk.domain.exception.GroupingNotFoundException;
import br.com.myapk.domain.model.Grouping;
import br.com.myapk.domain.model.Permission;
import br.com.myapk.domain.repository.GroupingRepository;

@Service
public class GroupingResgistrationService {

	private static final String MSG_GROUPING_IN_USE 
    	= "Code group %d cannot be removed as it is in use";
	
	@Autowired
	private GroupingRepository groupingRepository;
	
	@Autowired
	private PermissionRegistrationService permissionRegistrationService;
	
	@Transactional
	public Grouping save(Grouping grouping) {
		return groupingRepository.save(grouping);
	}
	
	@Transactional
	public void delete(Long groupingId) {
		try {
			groupingRepository.deleteById(groupingId);
			groupingRepository.flush();
			
		}catch (EmptyResultDataAccessException e) {
			throw new GroupingNotFoundException(groupingId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_GROUPING_IN_USE, groupingId));			
		}
	}	
	
	public Grouping seekOrFail(Long groupingId) {
		return groupingRepository.findById(groupingId)
				.orElseThrow(() -> new GroupingNotFoundException(groupingId));
	}
	
	@Transactional
	public void disassociatePermission(Long groupId, Long permissionId) {
		Grouping grouping = seekOrFail(groupId);
		Permission permission = permissionRegistrationService.seekOrFail(permissionId);
		
		grouping.removePermission(permission);
	}
	
	@Transactional
	public void associatePermission(Long groupId, Long permissionId){
		Grouping grouping = seekOrFail(groupId);
		Permission permission = permissionRegistrationService.seekOrFail(permissionId);
		
		
		grouping.addPermission(permission);
	}
	
}
