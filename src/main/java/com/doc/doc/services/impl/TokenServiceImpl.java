package com.doc.doc.services.impl;

import com.doc.doc.services.TokenService;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getUserId(String jwtToken) {

        String claims = new String(Base64.getUrlDecoder().decode(jwtToken.split("\\.")[1]));
        JSONObject claimsJson = new JSONObject(claims);

        //issuer
        return (String)claimsJson.get("iss");
    }

    @Override
    public List<String> getUserRoles(String jwtToken) {

        String claims = new String(Base64.getUrlDecoder().decode(jwtToken.split("\\.")[1]));
        JSONObject claimsJson = new JSONObject(claims);

        // intended audience for the token
        String audience = claimsJson.getString("aud");
        final String[] split = audience.replace("[", "").replace("]", "").split(",");

        return Stream.of(split).map(String::trim).collect(Collectors.toList());
    }

}
