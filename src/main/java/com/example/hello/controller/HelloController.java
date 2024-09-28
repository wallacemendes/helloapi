package com.example.hello.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.hello.domain.Hello;
import com.example.hello.domain.HelloRequestDTO;
import com.example.hello.service.HelloWorldService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private HelloWorldService helloWorldService;


    @GetMapping("/")    
    public String index() {
        return this.helloWorldService.getHelloMessage();
    }

    @PostMapping("/hello")
    public ResponseEntity<Hello> newHello(@Valid @RequestBody HelloRequestDTO body) {    
        Hello newHelloInstance = this.helloWorldService.newHello(body);
        return ResponseEntity.ok(newHelloInstance);
    }
    @PostMapping("hellos")
    public ResponseEntity<List<Hello>> helloBatch(@Valid @RequestBody List<HelloRequestDTO> body) {
        List<Hello> savedHellos = this.helloWorldService.newHelloBatch(body);
        
        return ResponseEntity.ok(savedHellos);
    }
    

    @GetMapping("hellos")
    public ResponseEntity<List<HelloRequestDTO>> fetchHellos(
        @RequestParam(required = false) String title,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {

        if (title != null){
            return ResponseEntity.ok(
                helloWorldService.fetchByTitleLike("%" + title + "%")
            );
        }
        List<HelloRequestDTO> hellos = this.helloWorldService.fetchHellos(page, size);
        return ResponseEntity.ok(hellos);
    }

    @GetMapping("hello/{id}")
    public ResponseEntity<HelloRequestDTO> fetchHello(@PathVariable String id){
            HelloRequestDTO response = this.helloWorldService.fetchIdHello(Integer.parseInt(id));
            return ResponseEntity.ok(response);
    }
    
    
}
