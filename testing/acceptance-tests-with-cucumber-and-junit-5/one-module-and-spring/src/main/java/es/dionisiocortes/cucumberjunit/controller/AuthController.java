package es.dionisiocortes.cucumberjunit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto){

        if (Objects.equals(loginDto.username(), "admin")) {
            return new ResponseEntity<>("Come in", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not allowed", HttpStatus.FORBIDDEN);
        }
    }
}
