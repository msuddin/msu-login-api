package com.thetestroom;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    // [REF 1.0] Simple endpoint to check endpoint is up
    @RequestMapping("hello")
    public String sayHello() {
        return "Hello, I am awake";
    }

    // [REF 2.0] Get method that returns a string which we pass into the URL path
    @RequestMapping(value = "login/{username}/{password}", method = RequestMethod.GET)
    public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
        return "hello " + username + "/" + password;
    }

    // [REF 3.0] Get method in which we pass in variables but have an object returned
    @RequestMapping(value = "loginName", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    public LoginSession userSession(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return new LoginSession(userName, password);
    }

    // [REF 4.0] POST method in which we pass in an Object and have it returned
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public LoginSession userSession(@RequestBody LoginSession session) {
        return session;
    }

}
