package com.thetestroom.controller;

import com.thetestroom.model.LoginSession;
import com.thetestroom.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountService accountService;

    // [REF 1.0] Simple endpoint to check endpoint is up
    @RequestMapping(value = "hello", produces = "application/json")
    public String sayHello() {
        return "{\"message\":\"Hello, I am awake\"}";
    }

    // [REF 2.0] Get method that returns a string which we pass into the URL path
    @RequestMapping(value = "login/{username}/{password}", method = RequestMethod.GET, produces = "application/json")
    public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
        if (ValidateCredentials(username, password)) {
            logger.info(String.format("New login session approved for %s", username));
            return "{\"message\":\"hello " + username + "/" + password + "\"}";
        }

        logger.info(String.format("New login session not approved for %s", username));
        return "{\"message\":\"Not authenticated\"}";
    }

    // [REF 3.0] Get method in which we pass in variables but have an object returned
    @RequestMapping(value = "loginName", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public Object userSession(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (ValidateCredentials(username, password)) {
            return new LoginSession(username, password);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    // [REF 4.0] POST method in which we pass in an Object and have it returned
    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public LoginSession userSession(@RequestBody LoginSession session) {
        if (ValidateCredentials(session.getUsername(), session.getPassword())) {
            logger.info(String.format("New login session for %s", session.getUsername()));
            return session;
        }
        return null;
    }

    private boolean ValidateCredentials(String username, String password) {
        for (int i = 1; i <= accountService.list().size(); i++) {
            if (accountService.get(Long.valueOf(i)).getName().equals(username) &&
                    accountService.get(Long.valueOf(i)).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
