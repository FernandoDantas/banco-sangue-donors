package br.com.myapk.domain.repository;

import br.com.myapk.domain.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
