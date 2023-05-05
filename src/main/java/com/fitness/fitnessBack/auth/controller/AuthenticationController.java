package com.fitness.fitnessBack.auth.controller;

import com.fitness.fitnessBack.auth.model.AuthenticationRequest;
import com.fitness.fitnessBack.auth.model.AuthenticationResponse;
import com.fitness.fitnessBack.auth.model.RegisterRequest;
import com.fitness.fitnessBack.auth.service.AuthenticationService;
import com.fitness.fitnessBack.config.JwtService;
import com.fitness.fitnessBack.user.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
            ){

       return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/checktoken")
    public ResponseEntity<HashMap<String, String>> checkToken(@AuthenticationPrincipal User user){
        HashMap<String, String> userMailAndRole = new HashMap<>();
        userMailAndRole.put("email",user.getEmail());
        userMailAndRole.put("role", user.getRole().toString());
        return ResponseEntity.ok(userMailAndRole);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token, @AuthenticationPrincipal User user){
        token = token.split(" ")[1].trim();
        try{
            Boolean isValidToken = jwtService.isTokenValid(token, user);
            return ResponseEntity.ok(isValidToken);
        }catch(ExpiredJwtException e){
            return ResponseEntity.ok(false);
        }
    }




}
