package com.uqac.back.controller;

import com.uqac.back.beans.ClientAffaire;
import com.uqac.back.beans.SecurityDatum;
import com.uqac.back.repository.ClientAffaireRepository;
import com.uqac.back.repository.SecurityDatumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/security")
@CrossOrigin("*")
public class SecurityDatumController {

    @Autowired
    SecurityDatumRepository securityRepository;

    /**
     * Fonction qui permet de recuperer toutes les options de securite
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @GetMapping("getAll")
    public List<SecurityDatum> getAll(){
        return securityRepository.findAll();
    }

    /**
     * Fonction qui permet de save toutes les options de securite
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @PostMapping("saveAll")
    public List<SecurityDatum> checkAdmin(@RequestBody List<SecurityDatum> securityDatum){
        return securityRepository.saveAll(securityDatum);
    }

}
