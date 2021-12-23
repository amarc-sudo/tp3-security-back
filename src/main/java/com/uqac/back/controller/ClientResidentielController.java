package com.uqac.back.controller;

import com.uqac.back.beans.ClientAffaire;
import com.uqac.back.beans.ClientResidentiel;
import com.uqac.back.repository.ClientAffaireRepository;
import com.uqac.back.repository.ClientResidentielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/api/residentiel")
@CrossOrigin("*")
public class ClientResidentielController {

    @Autowired
    ClientResidentielRepository clientResidentielRepository;

    /**
     * Fonction qui peut return true que si le token est bon car il passe par l'APISecurity
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @GetMapping("getAll")
    public List<ClientResidentiel> checkAdmin(){
        return clientResidentielRepository.findAll();
    }


}
