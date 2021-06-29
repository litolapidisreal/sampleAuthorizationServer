package com.sampleAuth.server.controller;

import com.sampleAuth.server.models.User;
import com.sampleAuth.server.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RegistrationController {
    private final UserLoginService userLoginService;

    public RegistrationController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> getTutorialById(@RequestBody User user) throws CredentialException {
        if (userLoginService.checkByUserUniqueness(user) &&
                ObjectUtils.allNotNull(user.getFirstName(),user.getLastName(),
                user.getBirthDate(), user.getAddressId(), user.getEmail(),
                user.getGender(),user.getUsername(), user.getPassword(),
                user.getUserType()) ) {
            return ResponseEntity.ok(userLoginService.saveUser(user));
        }
        throw new CredentialException("Non Unique Credentials found! Double check information.");
    }

//    @GetMapping("/sampler/{id}")
//    public ResponseEntity<List<Product>> createSampleData(@PathVariable("id") Integer id) {
//        return new ResponseEntity<>(productQueryService.findAll(), HttpStatus.OK);
//    }
}
