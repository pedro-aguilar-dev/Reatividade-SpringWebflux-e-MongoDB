package com.reactive.springwebflux.webflux.service;

import com.reactive.springwebflux.webflux.model.PokedexModel;
import com.reactive.springwebflux.webflux.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokedexServiceImple implements PokedexService {


    @Autowired
    PokedexRepository pokedexRepository;

    @Override
    public Flux<PokedexModel> findAll() {
        return pokedexRepository.findAll();
    }

    @Override
    public Mono<PokedexModel> findById(String id) {
        return pokedexRepository.findById(id);
    }

    @Override
    public Mono<PokedexModel> save(PokedexModel pokedexModel) {
        return pokedexRepository.save(pokedexModel);
    }
}
