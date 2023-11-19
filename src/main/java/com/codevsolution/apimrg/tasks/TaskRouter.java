package com.codevsolution.apimrg.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration(proxyBeanMethods = false)
public class TaskRouter {

	@Bean
	RouterFunction<ServerResponse> routes(TaskHandler handler) {
		
		return RouterFunctions.nest(RequestPredicates.path("/api/tasks"),
				RouterFunctions.nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), 
				RouterFunctions.route(RequestPredicates.GET("/pend"), handler::getTasksPend)
				.andRoute(RequestPredicates.GET("/owner-user/{owner}/{user}"), handler::getTasksOwnerOrUser)
				.andRoute(RequestPredicates.GET("/owner-user-pend/{owner}/{user}"), handler::getTasksPendOwnerOrUser)
				.andRoute(RequestPredicates.GET("/owner/{owner}"), handler::getTasksOwner)
				.andRoute(RequestPredicates.GET("/owner-pend/{owner}"), handler::getTasksPendOwner)
				.andRoute(RequestPredicates.GET("/user/{user}"), handler::getTasksUser)
				.andRoute(RequestPredicates.GET("/user-pend/{user}"), handler::getTasksPendUser)
				.andRoute(RequestPredicates.GET("/{id}"), handler::getTask)
				.andRoute(RequestPredicates.GET(""), handler::getTasks)
				.andRoute(RequestPredicates.POST(""), handler::saveTask)
				.andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteTask)
				.andRoute(RequestPredicates.PUT("/{id}"), handler::updateTask)));
	}
}
