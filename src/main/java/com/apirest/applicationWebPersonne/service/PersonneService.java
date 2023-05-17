package com.apirest.applicationWebPersonne.service;

import com.apirest.applicationWebPersonne.modele.Personne;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonneService {

    ResponseEntity creer(Personne personne);

    ResponseEntity lire();
}
