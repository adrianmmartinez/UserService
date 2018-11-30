package edu.csumb.cst438.UserService.api;

import edu.csumb.cst438.UserService.api.entities.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    boolean signIn(@RequestParam String username) {
        boolean authorized = dbLogin(username);
        return authorized;
    }

    private boolean dbLogin(String username) {
        String url = "http://localhost:8081/login";
        // Create the request body as a MultiValueMap
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, null);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Boolean>() {
                });
        return result.getBody();
    }



}
