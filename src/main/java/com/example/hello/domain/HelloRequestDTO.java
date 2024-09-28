package com.example.hello.domain;

import jakarta.validation.constraints.*;

public record HelloRequestDTO(
        @NotNull
        @Size(min = 3, max=55, message = "O título deve ter entre 3 e 100 caracteres")
        String title, 

        @Size(max = 255, message = "O conteúdo pode ter no máximo 255 caracteres")
        String content
    ) {}
