package com.example.hello.service;


import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.example.hello.domain.Hello;
import com.example.hello.domain.HelloRequestDTO;
import com.example.hello.exception.ResourceNotFoundException;
import com.example.hello.repositories.HelloRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class HelloWorldService {

    @Autowired
    private HelloRepository textRepository;


    public String getHelloMessage(){
        return "Hello from Spring Boot API!";
    }

    @Transactional
    public Hello newHello(HelloRequestDTO data) {
        String content = data.content();
        if (content == null) {
            content = "Prazer em conhecer você!";
        }
        Hello txt = new Hello();
        txt.setTitle(data.title());
        txt.setContent(content);
        this.textRepository.save(txt);
        return txt;
    }

    public List<HelloRequestDTO> fetchHellos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Hello> hellosPage = this.textRepository.findAll(pageable);
        if(hellosPage.isEmpty()) throw new ResourceNotFoundException("Nenhum Hello encontrado.");
        return hellosPage.map(hello -> new HelloRequestDTO(hello.getTitle(), hello.getContent())).toList();
    }

    public HelloRequestDTO fetchIdHello(Integer id){
        Optional<Hello> text = this.textRepository.findById(id);

        return text.map(txt -> new HelloRequestDTO(txt.getTitle(), txt.getContent()))
        .orElseThrow(() ->  new ResourceNotFoundException("ID não encontrado."));   
    }

    public List<HelloRequestDTO> fetchByTitleLike(String title){
        List<Hello> texts = this.textRepository.findByTitleLike(title);
        if (texts.isEmpty()) {
            return new ArrayList<HelloRequestDTO>();
        }
       return texts.stream().map(text -> new HelloRequestDTO(text.getTitle(), text.getContent())).toList();
    }

    @Transactional
    public List<Hello> newHelloBatch(List<HelloRequestDTO> data){
        List<Hello> hellos = data.stream()
        .map( hello -> hello.content() != null ? new Hello(hello.title(), hello.content()) : new Hello(hello.title(), "Prazer em conhecer você!"))
        .toList();
        List<Hello> savedHellos = this.textRepository.saveAll(hellos);
        return savedHellos;
    }


}
