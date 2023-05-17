package com.apirest.applicationWebPersonne.service;

import com.apirest.applicationWebPersonne.modele.Personne;
import com.apirest.applicationWebPersonne.modele.PersonneDTO;
import com.apirest.applicationWebPersonne.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonneServiceImpl implements PersonneService{
    @Autowired
    private PersonneRepository personneRepository;

    @Override
    public ResponseEntity creer(Personne personne) {
        if (valideAge(personne)) {
            Personne savedPersonne = personneRepository.save(personne);
            return ResponseEntity.ok(savedPersonne);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("L'âge de la personne doit être inférieur à 150 ans.");
        }
    }

    @Override
    public ResponseEntity lire() {
        List<Personne> personnes = personneRepository.findAllByOrderByNomAsc();

        List<PersonneDTO> personnesDTO = personnes.stream()
                .map(personne -> {
                    int age = calculerAge(personne.getDateNaissance());
                    return new PersonneDTO(personne.getNom(), personne.getPrenom(), age);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(personnesDTO);
    }

    private boolean valideAge(Personne personne) {
        LocalDate now = LocalDate.now();
        Period age = Period.between(personne.getDateNaissance(), now);
        return age.getYears() < 150;
    }

    private int calculerAge(LocalDate dateNaissance) {
        LocalDate now = LocalDate.now();
        Period age = Period.between(dateNaissance, now);
        return age.getYears();
    }
}
