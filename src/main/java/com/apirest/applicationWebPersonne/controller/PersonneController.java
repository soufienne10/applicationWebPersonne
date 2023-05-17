package com.apirest.applicationWebPersonne.controller;

import com.apirest.applicationWebPersonne.modele.Personne;
import com.apirest.applicationWebPersonne.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personne")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Personne personne){
        return personneService.creer(personne);
    }

    @GetMapping("/read")
    public ResponseEntity read(){
        return personneService.lire();
    }
}
