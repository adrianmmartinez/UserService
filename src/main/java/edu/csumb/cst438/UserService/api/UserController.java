package edu.csumb.cst438.UserService.api;

import edu.csumb.cst438.UserService.api.entities.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @CrossOrigin()
    @GetMapping("/balance/{id}")
    @ResponseBody
    int balance(@PathVariable String id) {
        int bal = dbBalance(id);
        return bal;
    }

    private int dbBalance(String id) {
        String url = "https://userdb438.herokuapp.com/balance/" + id;
        // Create the request body as a MultiValueMap
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, null);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> result = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Integer>() {
                });
        return result.getBody();
    }

    @CrossOrigin()
    @GetMapping("/verify/funds/{id}/{amount}")
    @ResponseBody
    boolean balance(@PathVariable String id, @PathVariable String amount) {
        boolean funds = dbVerifyPurchase(id, amount);
        return funds;
    }

    private boolean dbVerifyPurchase(String id, String amount) {
        String url = "https://userdb438.herokuapp.com/verify/funds/" + id + "/" + amount;
        // Create the request body as a MultiValueMap
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, null);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Boolean>() {
                });
        System.out.println(result.getBody());
        return result.getBody();
    }

    @CrossOrigin()
    @PostMapping("/login")
    @ResponseBody
    String signIn(@RequestParam String username) {
        String authorized = dbLogin(username);
        return authorized;
    }

    private String dbLogin(String username) {
        String url = "https://userdb438.herokuapp.com/login";
        // Create the request body as a MultiValueMap
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, null);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<String>() {
                });
        System.out.println(result);
        return result.getBody();
    }



}
