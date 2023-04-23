package com.reactive.springwebflux.webflux.service;

import com.reactive.springwebflux.webflux.model.PokedexModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokedexService {
    //Flux = fluxo(stream) com 0 a N elementos
    //O Flux é igualmente a classe List, a diferença é que na classe Flux é permitido
    // colocar listas assincronas

    Flux<PokedexModel> findAll();

    //Mono = fluxo(stream) com 0 a 1 elemento
    Mono<PokedexModel> findById(String id);

    Mono<PokedexModel> save(PokedexModel pokedexModel);
}
