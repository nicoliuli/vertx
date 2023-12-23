package com.nico.web;

import com.alibaba.fastjson.JSON;
import com.nico.web.bean.Bean;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class SingleApplicationVerticle extends AbstractVerticle {

    private static final String HTTP_HOST = "0.0.0.0";
    private static final int HTTP_PORT = 8080;

    @Override
    public void start(Future<Void> future) throws Exception {
        Router router = Router.router(vertx);
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        Set<HttpMethod> allowMethods = new HashSet<>();
        allowMethods.add(HttpMethod.GET);
        allowMethods.add(HttpMethod.POST);
        allowMethods.add(HttpMethod.DELETE);
        allowMethods.add(HttpMethod.PATCH);

        router.get(Constants.API_GET).handler(this::handlerGetTodo);

        router.route()
                .handler(CorsHandler.create("*")
                        .allowedHeaders(allowHeaders)
                        .allowedMethods(allowMethods));

        router.route().handler(BodyHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(HTTP_PORT,HTTP_HOST,result -> {
                   if(result.succeeded()) {
                       future.complete();
                   } else {
                       future.fail(result.cause());
                   }
                });
        webClient = WebClient.create(vertx);
    }

    WebClient webClient;

    // http://127.0.0.1:8080/todos/1
    private void handlerGetTodo(RoutingContext ctx){
        String todoId = ctx.request().getParam("todoId");
        JsonObject json = JsonObject.mapFrom(new Bean("1","qqq"));
        webClient.post(8081,"localhost","/").sendJson(json,ret-> {
           if(ret.succeeded()) {
               String result = ret.result().bodyAsString();
               ctx.response().putHeader("content-type","application/json")
                       .end(result);
           } else {
               ctx.response().setStatusCode(404).end();
           }
        });

       /* ctx.response().putHeader("content-type","application/json")
                .end(JSON.toJSONString(new Bean(todoId,"张三")));*/
    }
}
