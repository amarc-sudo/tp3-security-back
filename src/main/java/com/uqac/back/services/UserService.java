package com.uqac.back.services;

import com.uqac.back.beans.Token;
import com.uqac.back.beans.User;
import com.uqac.back.repository.UserRepository;
import com.uqac.back.utils.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    public Map<String, Object> login(String login, String password){
        String passwordCrypt = "";
        User salt = userRepository.findByLogin(login);
        try {
            if(salt != null)
                passwordCrypt = Hasher.hashPassword(salt.getSalt(), password,1000);
            System.out.println(passwordCrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userRepository.findByPasswordAndLogin(passwordCrypt,login);
        String tokenConnexion = null;
        Map<String, Object> mapInformations = null;
        if (user!=null && user.getTentative() <= 3){
            mapInformations = new HashMap<>();
            tokenConnexion = tokenService.generateToken(user);
            mapInformations.put("token", tokenConnexion);
            mapInformations.put("role", user.getRole());
        } else if (user!=null && user.getTentative() >= 3){
            mapInformations = new HashMap<>();
            mapInformations.put("error", "Trop de tentative");
        }
        else if(salt!=null) {
            salt.setTentative(salt.getTentative() + 1);
            mapInformations = new HashMap<>();
            mapInformations.put("error", "Mauvais mots de passe ou login");
        } else {
            mapInformations = new HashMap<>();
            mapInformations.put("error", "Mauvais mots de passe ou login");
        }
        return mapInformations;
    }
}
