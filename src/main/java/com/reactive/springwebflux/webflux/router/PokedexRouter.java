package com.reactive.springwebflux.webflux.router;

import com.reactive.springwebflux.webflux.handler.PokedexHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/*@Configuration*/
public class PokedexRouter {

    //metodo que irá fazer com que os endpoints sejam criados de maneira funcional

    @Bean
    public RouterFunction<ServerResponse> route(PokedexHandler handler){
        return RouterFunctions
                //rotas
                .route(GET("/api/pokedex/listarTodos").and(accept(MediaType.APPLICATION_JSON))
                ,handler::findAll)
                .andRoute(GET("api/pokedex/buscarPorid/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
                .andRoute(POST("api/pokedex/inserir").and(accept(MediaType.APPLICATION_JSON)), handler::save);
    }
}
