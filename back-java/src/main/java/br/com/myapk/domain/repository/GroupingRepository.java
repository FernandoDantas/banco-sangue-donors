package br.com.myapk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myapk.domain.model.Grouping;

@Repository
public interface GroupingRepository extends JpaRepository<Grouping, Long>{

}
