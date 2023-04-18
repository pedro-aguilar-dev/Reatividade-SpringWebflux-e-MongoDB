package com.reactive.springwebflux.webflux.repository;

import com.reactive.springwebflux.webflux.model.PokedexModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<PokedexModel, String> {
}
