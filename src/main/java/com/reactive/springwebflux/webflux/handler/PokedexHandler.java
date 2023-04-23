package com.reactive.springwebflux.webflux.handler;

import com.reactive.springwebflux.webflux.model.PokedexModel;
import com.reactive.springwebflux.webflux.service.PokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import reactor.core.publisher.Mono;


/*@Component*/
public class PokedexHandler {

    @Autowired
    PokedexService pokedexService;

    //handler irá servir para fazer as requisições de modo funcional e assincrono

    //É usado o Mono, pois neste caso o servidor irá fazer apenas uma requisição
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pokedexService.findAll(), PokedexModel.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pokedexService.findById(id), PokedexModel.class);
    }

    //metodo save do handler
    //é do tipo Mono pois será dada apenas uma resposta a requisição do usuário
    public Mono<ServerResponse> save(ServerRequest request){

        //no metodo save irá ser passado o parametro request, o request será o body enviado
        //na requisição.

        //será um body com as propriedades da classe PokedexModel e que irá ser feito em um Json
       final Mono<PokedexModel> pokedexModelMono = request.bodyToMono(PokedexModel.class);
       return ok()

               //o contentType é passado no headers, o type será Json
               .contentType(MediaType.APPLICATION_JSON)

               //o fromPublisher é um metodo da classe BodyInserters
               //é um metodo responsavel por dar inserção ao Publisher
               //o Publisher é um provedor de um número potencialmente limitado de elementos sequenciados
               .body((fromPublisher(pokedexModelMono.flatMap(pokedexService::save), PokedexModel.class)));

    }


}
