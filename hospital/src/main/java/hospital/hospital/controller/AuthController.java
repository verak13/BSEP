package hospital.hospital.controller;


import hospital.hospital.dto.AuthUserDTO;
import hospital.hospital.dto.AuthUserResponseDTO;
import hospital.hospital.model.User;
import hospital.hospital.security.TokenUtils;
import hospital.hospital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthUserDTO authenticationRequest,
                                                       HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();


            String jwt = tokenUtils.generateToken(user.getEmail());
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new AuthUserResponseDTO(jwt, expiresIn));

        }
        catch(BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
