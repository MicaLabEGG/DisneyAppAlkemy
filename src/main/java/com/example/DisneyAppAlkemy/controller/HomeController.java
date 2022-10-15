package com.example.DisneyAppAlkemy.controller;

import com.example.DisneyAppAlkemy.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /*@Autowired
    private AuthenticationManager authenticationManager;*/

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/hola")
    public String home() {
        return "Bienvenidos a Disney";
    }

	/*@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()
				)
			);
	    } catch(AuthenticationException e) {
	    	throw new Exception("Invalid_Credentials", e);
	    }

		final UserDetails userDetails = usuarioServicio.loadUserByUsername(jwtRequest.getUsername());

		//final String token = jwtUtility.generateToken(userDetails);

		//return new JwtResponse(token);
	}	*/
}

