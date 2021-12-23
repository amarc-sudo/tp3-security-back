package com.uqac.back.services;

import com.uqac.back.beans.Role;
import com.uqac.back.beans.Token;
import com.uqac.back.beans.User;
import com.uqac.back.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service("tokenService")
@Transactional
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    public Optional<Token> checkTokenUnique(String authToken) {
        return tokenRepository.findByTokenEquals(authToken);
    }

    public String generateToken(User user){
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setDateExpirationToken(Instant.now().plus(24, ChronoUnit.HOURS));
        token.setUser(user);
        tokenRepository.save(token);
        return token.getToken();
    }

    public boolean checkToken(String token) {
        return tokenRepository.findByTokenEquals(token).isPresent();
    }
}
