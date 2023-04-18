package com.reactive.springwebflux.webflux.data;

import com.reactive.springwebflux.webflux.model.PokedexModel;
import com.reactive.springwebflux.webflux.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

    //injetando o repository
    @Autowired
    private PokedexRepository pokedexRepository;

    DummyData(PokedexRepository pokedexRepository){
        this.pokedexRepository = pokedexRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        pokedexRepository.deleteAll()
                .thenMany(
                        Flux.just("Bulbasaur", "Venusaur", "Charmander", "Pikachu", "Squirtle")
                                .map(nome -> new PokedexModel (UUID.randomUUID().toString(),nome))
                                .flatMap(pokedexRepository::save))
                .subscribe(System.out::println);

    }
}

