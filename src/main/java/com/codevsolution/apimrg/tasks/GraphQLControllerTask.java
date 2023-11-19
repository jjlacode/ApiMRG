package com.codevsolution.apimrg.tasks;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RequiredArgsConstructor
@Controller
public class GraphQLControllerTask {

	private final ServiceTask taskService;
	
	@SubscriptionMapping
	public Flux<DtoTask> subsListTasks(){
		
		return taskService.listTasks();
	}
	
	@SubscriptionMapping
	public Flux<DtoTask> subsListTasksByUser(String user){
		
		return taskService.listTasksByUser(user);
	}
	
	@SubscriptionMapping
	public Flux<DtoTask> subsPendingTasks(){
		
		return taskService.pendingTasks();
	}
	
	@SubscriptionMapping
	public Flux<DtoTask> subsPendingTasksByUser(String user){
		
		return taskService.pendingTasksByUser(user);
	}
	
	@QueryMapping
	public Flux<DtoTask> listTasks(){
		
		return taskService.listTasks();
	}
	
	@QueryMapping
	public Flux<DtoTask> pendingTasks(){
		
		return taskService.pendingTasks();
	}
	
	@QueryMapping
	public Mono<DtoTask> getTask(@Argument UpdateDtoTask filter){
		
		return taskService.getTask(filter.getId());
	}
	
	@QueryMapping
	public Flux<DtoTask> listTasksByUser(@Argument String user){
		
		return taskService.listTasksByUser(user);
	}
	
	@QueryMapping
	public Flux<DtoTask> pendingTasksByUser(@Argument String user){
		
		return taskService.pendingTasksByUser(user);
	}
	
	@MutationMapping
	public Mono<Boolean> deleteTask(@Argument String id){
		
		return taskService.deleteTask(id);
	}
	
	@MutationMapping
	public Mono<DtoTask> updateTask(@Argument UpdateDtoTask dto){
		log.info("dto:{}",dto);
		return taskService.updateTask(dto);
	}
	
	@MutationMapping
	public Mono<DtoTask> createTask(@Argument InputDtoTask dto){
		log.info("dto:{}",dto);
		return taskService.createTask(dto);
	}
}
