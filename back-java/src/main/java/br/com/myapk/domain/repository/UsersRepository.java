package br.com.myapk.domain.repository;

import br.com.myapk.domain.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CustomJpaRepository<Users, Long>{	

	Optional<Users> findByUserEmail(String email);

	Optional<Users> findByIdAndTenantId(Long id, Long tenantId);

	List<Users> findByTenantId(Long tenantId);
	
}
