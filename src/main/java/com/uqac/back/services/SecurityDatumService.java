package com.uqac.back.services;

import com.uqac.back.beans.SecurityDatum;
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
import java.util.List;
import java.util.Map;

@Service("SecurityDatumService")
@Transactional
public class SecurityDatumService {
    Logger logger = LoggerFactory.getLogger(SecurityDatumService.class);

    @Autowired
    SecurityDatumRepository securityDatumRepository;

    SecurityDatum findByTag(String tag){
        return securityDatumRepository.findByTagSecurity(tag);
    }

    List<SecurityDatum> findAll(){
        return securityDatumRepository.findAll();
    }

    void saveAll(List<SecurityDatum> securityDatum){
        securityDatumRepository.saveAll(securityDatum);
    }
}
