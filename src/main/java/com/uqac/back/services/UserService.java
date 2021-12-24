package com.uqac.back.services;

import com.uqac.back.beans.Token;
import com.uqac.back.beans.User;
import com.uqac.back.repository.SecurityDatumRepository;
import com.uqac.back.repository.UserRepository;
import com.uqac.back.utils.Hasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
@Transactional
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityDatumService securityDatumService;

    @Autowired
    TokenService tokenService;

    public Map<String, Object> login(String login, String password) {
        this.logger.info("Tentative de connexion de " + login);
        String passwordCrypt = "";
        User salt = userRepository.findByLogin(login);
        try {
            if (salt != null)
                passwordCrypt = Hasher.hashPassword(salt.getSalt(), password, 1000);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userRepository.findByPasswordAndLogin(passwordCrypt, login);
        String tokenConnexion = null;
        Map<String, Object> mapInformations = null;
        if (user != null && user.getTentative() <= securityDatumService.findByTag("tentative").getVariable()) {
            this.logger.info("Réussite de la connexion de " + login);
            mapInformations = new HashMap<>();
            tokenConnexion = tokenService.generateToken(user);
            mapInformations.put("token", tokenConnexion);
            mapInformations.put("role", user.getRole());
        } else if (salt.getTentative() >= 3) {
            this.logger.warn("Trop de tentative echouer pour" + login);
            mapInformations = new HashMap<>();
            mapInformations.put("error", "Trop de tentative, contacter un administrateur");
        } else if (salt != null) {
            this.logger.info("Echec de la connexion de " + login);
            salt.setTentative(salt.getTentative() + 1);
            mapInformations = new HashMap<>();
            mapInformations.put("error", "Mauvais mots de passe ou login");
        } else {
            this.logger.info(login + " n'existe pas");
            mapInformations = new HashMap<>();
            mapInformations.put("error", "Mauvais mots de passe ou login");
        }
        return mapInformations;
    }

    public void changePassword(String login, String password) {
        String passwordCrypt = "";
        User salt = userRepository.findByLogin(login);
        String newSalt = Hasher.generateSalt();
        try {
            if (salt != null)
                passwordCrypt = Hasher.hashPassword(newSalt, password, 1000);
                salt.setSalt(newSalt);
                salt.setPassword(passwordCrypt);
                userRepository.save(salt);
                this.logger.info("Changement du mot de passe de " + login);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void changePasswordUser(String login, String password, String newpassword) {

        String passwordCrypt = "";
        User salt = userRepository.findByLogin(login);
        String newSalt = Hasher.generateSalt();
        try {
            if (salt != null)
                passwordCrypt = Hasher.hashPassword(newSalt, password, 1000);
            salt.setSalt(newSalt);
            salt.setPassword(passwordCrypt);
            userRepository.save(salt);
            this.logger.info("Changement du mot de passe de " + login);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
