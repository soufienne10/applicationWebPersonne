package com.apirest.applicationWebPersonne.repository;

import com.apirest.applicationWebPersonne.modele.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    List<Personne> findAllByOrderByNomAsc();
}
