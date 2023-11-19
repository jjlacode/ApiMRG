package com.codevsolution.apimrg.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class TaskHandler {

	@Autowired
	private ServiceTask service;
	
	public Mono<ServerResponse> getTasks(ServerRequest request){
		Flux<DtoTask> tasks = service.listTasks();
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasks,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksPend(ServerRequest request){
		Flux<DtoTask> tasks = service.pendingTasks();
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasks,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksUser(ServerRequest request){
		Flux<DtoTask> tasksUser =service.listTasksByUser(request.pathVariable("user"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasksUser,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksOwner(ServerRequest request){
		Flux<DtoTask> tasksUser =service.listTasksByOwner(request.pathVariable("owner"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasksUser,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksOwnerOrUser(ServerRequest request){
		Flux<DtoTask> tasksUser =service.listTasksByOwnerOrUser(request.pathVariable("owner"),request.pathVariable("user"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasksUser,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksPendUser(ServerRequest request){
		Flux<DtoTask> tasksUser =service.pendingTasksByUser(request.pathVariable("user"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasksUser,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksPendOwner(ServerRequest request){
		Flux<DtoTask> tasksUser =service.pendingTasksByOwner(request.pathVariable("owner"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasksUser,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTasksPendOwnerOrUser(ServerRequest request){
		Flux<DtoTask> tasksUser =service.pendingTasksByOwnerOrUser(request.pathVariable("owner"),request.pathVariable("user"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(tasksUser,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> getTask(ServerRequest request){
		Mono<DtoTask> task =service.getTask(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(task,DtoTask.class).switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> saveTask(ServerRequest request){
		Mono<InputDtoTask> postDtoMono = request.bodyToMono(InputDtoTask.class);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return postDtoMono.flatMap(dto ->
		
					ServerResponse
					.status(HttpStatus.CREATED)
					.contentType(MediaType.APPLICATION_JSON)
					.body(service.createTask(dto),DtoTask.class)).switchIfEmpty(notFound);
				
	}
	
	public Mono<ServerResponse> updateTask(ServerRequest request){
		Mono<UpdateDtoTask> postDtoMono = request.bodyToMono(UpdateDtoTask.class);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return postDtoMono.flatMap(dto -> {
					if(dto.getCompleted()<100) {
						dto.setEndedAt(null);
					}
					dto.setId(request.pathVariable("id"));
					return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(service.updateTask(dto),DtoTask.class);
					
		}).switchIfEmpty(notFound);
		
				
	}
	
	public Mono<ServerResponse> deleteTask(ServerRequest request){
		String id = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		return ServerResponse
				.status(HttpStatus.NO_CONTENT)
				.body(service.deleteTask(id),Boolean.class).switchIfEmpty(notFound);
				
	}
}
