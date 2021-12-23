package com.uqac.back.controller;

import com.uqac.back.services.TokenService;
import com.uqac.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("rest/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /**
     * Fonction qui retourne une mapInformation soit avec un token et un role soit avec un message d'erreur
     * @param mapInformations
     * @return mapInformations
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody Map<String, String> mapInformations) throws NoSuchAlgorithmException {
        String password = mapInformations.get("password");
        String login = mapInformations.get("login");
        return userService.login( login, password);
    }

    /**
     * Fonction qui peut return true que si le token est bon car il passe par l'APISecurity
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @GetMapping("admin/check")
    public boolean checkAdmin(){
        return true;
    }

    /**
     * Fonction qui peut return true que si le token est bon car il passe par l'APISecurity
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @GetMapping("residentiel/check")
    public boolean checkResidentiel(){
        return true;
    }

    /**
     * Fonction qui peut return true que si le token est bon car il passe par l'APISecurity
     * @see com.uqac.back.configuration.APISecurity
     * @return Boolean
     */
    @CrossOrigin
    @GetMapping("affaire/check")
    public boolean CheckAffaire(){
        return true;
    }

}
