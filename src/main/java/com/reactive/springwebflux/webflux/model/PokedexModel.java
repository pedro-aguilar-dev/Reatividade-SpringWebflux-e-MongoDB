package com.reactive.springwebflux.webflux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PokedexModel  {
    @Id
    private String id;
    private String nome;

    public PokedexModel(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
