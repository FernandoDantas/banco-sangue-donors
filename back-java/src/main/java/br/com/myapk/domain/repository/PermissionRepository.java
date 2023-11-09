package br.com.myapk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myapk.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
