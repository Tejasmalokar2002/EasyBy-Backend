package com.Shop.EasyBy.auth.controller;

import com.Shop.EasyBy.auth.config.JWTTokenHelper;
import com.Shop.EasyBy.auth.dto.LoginRequest;
import com.Shop.EasyBy.auth.dto.RegistrationRequest;
import com.Shop.EasyBy.auth.dto.RegistrationResponse;
import com.Shop.EasyBy.auth.dto.UserToken;
import com.Shop.EasyBy.auth.entities.User;
import com.Shop.EasyBy.auth.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    public ResponseEntity<UserToken> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    );

            Authentication authenticationResponse = authenticationManager.authenticate(authToken);

            // ✅ Set the authentication into the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

            if (authenticationResponse.isAuthenticated()) {
                User user = (User) authenticationResponse.getPrincipal();

                if (!user.isEnabled()) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }

                String token = jwtTokenHelper.generateToken(user.getEmail());
                UserToken userToken = UserToken.builder().token(token).build();
                return ResponseEntity.ok(userToken);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Optional: log the exception
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }




    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request){
        RegistrationResponse registrationResponse = registrationService.createUser(request);

        return new ResponseEntity<>(registrationResponse,
                registrationResponse.getCode() == 200 ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String,String> map){
        String userName = map.get("userName");
        String code = map.get("code");

        User user= (User) userDetailsService.loadUserByUsername(userName);
        if(null != user && user.getVerificationCode().equals(code)){
            registrationService.verifyUser(userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
