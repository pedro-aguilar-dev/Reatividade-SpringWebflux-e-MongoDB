package com.reactive.springwebflux.webflux.controller;

import com.reactive.springwebflux.webflux.model.PokedexModel;
import com.reactive.springwebflux.webflux.service.PokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
public class PokedexController {

    @Autowired
     PokedexService pokedexService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/pokedex/inserir")
    public Mono<PokedexModel> inserirPokemon(@RequestBody PokedexModel pokedexModel){
        return pokedexService.save(pokedexModel);
    }

    @GetMapping("/api/pokedex/listarTodos")
    public Flux<PokedexModel> listarTodos(){
        return pokedexService.findAll();
    }

    @GetMapping("/api/pokedex/buscarPorId/{id}")
    public Mono<PokedexModel> buscarPorId(@PathVariable String id){
        return pokedexService.findById(id);
    }

    //REATIVIDADE NO ENDPOINT

    //ao fazer a requisição da url /pokedex/events no browser, ele irá um stream de eventos(dados)
    //ele não irá retornar somente um objeto PokedexModel como de costume
    //é preciso referenciar o metodo produces, ele serve para setar o MediaType como Text Event
    //Nesse caso o retorno será de mais de um elemento, por conta do metodo findAll
    //por esse motivo é usado o Flux
    //é setado um intervalo para que os itens da lista sejam vistos
    //Isso irá permitir que as requisições não sejam bloqueantes, independente de quantas existirem
    //será possivel retornar listas de forma ilimitada, sem bloqueios de requisição
    //uma lista não impactará na outra


    //Será possivel fazer requisições com a url /pokedex/events
    //As requisições irão ser feitas sem que nenhuma tenha impacto na outra

    //tarefa = REQUISIÇÃO
    //Sendo assim, é criado um processo assincrono. Com várias tarefas em background
    //as tarefas irão ser executadas em seu próprio tempo(interval do flux)
    // não existirá a pendencia do termino de uma tarefa para poder realizar outra
    @GetMapping(value="/pokedex/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, PokedexModel>> getPokemonsEvents(){
        Flux<Long> intervalo = Flux.interval(Duration.ofSeconds(10));
        Flux<PokedexModel> events = pokedexService.findAll();
        System.out.println("O EVENTS IRÁ PASSAR AQUI");
        return Flux.zip(intervalo, events);
    }



}
