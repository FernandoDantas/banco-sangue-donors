package br.com.myapk.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myapk.domain.exception.PermissionNotFoundException;
import br.com.myapk.domain.model.Permission;
import br.com.myapk.domain.repository.PermissionRepository;

@Service
public class PermissionRegistrationService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public Permission seekOrFail(Long permissionId) {
		return permissionRepository.findById(permissionId)
				.orElseThrow(() -> new PermissionNotFoundException(permissionId));
	}
	
}
