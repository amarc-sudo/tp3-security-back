package com.uqac.back.controller;

import com.uqac.back.beans.ClientAffaire;
import com.uqac.back.repository.ClientAffaireRepository;
import com.uqac.back.services.TokenService;
import com.uqac.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest/api/client-affaire")
@CrossOrigin("*")
public class ClientAffaireController {

    @Autowired
    ClientAffaireRepository clientAffaireRepository;

    /**
     * Fonction qui peut return true que si le token est bon car il passe par l'APISecurity
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @GetMapping("getAll")
    public List<ClientAffaire> checkAdmin(){
        return clientAffaireRepository.findAll();
    }


}
