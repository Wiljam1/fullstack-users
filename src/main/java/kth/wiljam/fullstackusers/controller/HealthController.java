package kth.wiljam.fullstackusers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> status() {return new ResponseEntity<>("its working! (users)", HttpStatus.OK);}

    @GetMapping("/healthz")
    public ResponseEntity<String> healthz() {return new ResponseEntity<>(HttpStatus.OK);}
}
