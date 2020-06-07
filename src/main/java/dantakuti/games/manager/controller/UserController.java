package dantakuti.games.manager.controller;

import dantakuti.games.manager.dao.RoleRepository;
import dantakuti.games.manager.entity.ERole;
import dantakuti.games.manager.entity.Player;
import dantakuti.games.manager.entity.Roles;
import dantakuti.games.manager.payload.requests.LoginRequest;
import dantakuti.games.manager.payload.requests.RegisterRequest;
import dantakuti.games.manager.payload.response.JwtResponse;
import dantakuti.games.manager.payload.response.MessageResponse;
import dantakuti.games.manager.security.jwt.JwtUtils;
import dantakuti.games.manager.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author adarshbhattarai on 2020-05-04
 * @project LeagueManager
 */

@RestController
@RequestMapping("/auth")
public class UserController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsService")
    @Autowired
    UserDetailService userDetailService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
        if (userDetailService.userPresent(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userDetailService.userPresent(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Player user = new Player(signUpRequest.getFirstName(),signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),true);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();
        if (strRoles == null) {
            Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
   //     userDetailService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }
}
