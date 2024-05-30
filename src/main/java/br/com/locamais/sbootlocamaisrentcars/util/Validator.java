package br.com.locamais.sbootlocamaisrentcars.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

public class Validator {
    private Validator(){}

    public static UUID uuidValid(String id){
        try{
            return UUID.fromString(id);
        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
