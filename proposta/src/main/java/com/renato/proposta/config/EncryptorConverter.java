package com.renato.proposta.config;

import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class EncryptorConverter implements AttributeConverter<String, String> {

    @Value("${encryptorconverter.password}")
    private String password;

    @Value("${encryptorconverter.password}")
    private String salt;

    private TextEncryptor encryptor() {
        return Encryptors.text(password, salt);
    }

    @Override
    public String convertToDatabaseColumn(String atributo) {
        return encryptor().encrypt(atributo);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor().decrypt(dbData);
    }
}
