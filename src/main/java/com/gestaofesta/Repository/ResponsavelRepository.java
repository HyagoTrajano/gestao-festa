package com.gestaofesta.Repository;

import com.gestaofesta.Model.Festa;
import com.gestaofesta.Model.Responsavel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{
    Iterable<Responsavel> findByFesta(Festa festa);    
}
