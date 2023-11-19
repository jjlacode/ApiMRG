package com.codevsolution.apimrg.tasks;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class ControllerTask {

	private final ServiceTask taskService;
	
	@GetMapping()
	public Flux<DtoTask> listTasks(){
		
		return taskService.listTasks();
	}
	
	@GetMapping("/pend")
	public Flux<DtoTask> pendingTasks(){
		
		return taskService.pendingTasks();
	}
	
	@GetMapping("/{id}")
	public Mono<DtoTask> getTask(@PathVariable("id") String id){
		
		return taskService.getTask(id);
	}
	
	@GetMapping("/user/{user}")
	public Flux<DtoTask> listTasksByUser(@PathVariable("user") String user){
		
		return taskService.listTasksByUser(user);
	}
	
	@GetMapping("/pend/user/{user}")
	public Flux<DtoTask> pendingTasksByUser(@PathVariable("user") String user){
		
		return taskService.pendingTasksByUser(user);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Boolean> deleteTask(@PathVariable("user") String id){
		
		return taskService.deleteTask(id);
	}
	
	@PutMapping
	public Mono<DtoTask> updateTask(UpdateDtoTask dto){
		log.info("dto:{}",dto);
		return taskService.updateTask(dto);
	}
	
	@PostMapping
	public Mono<DtoTask> createTask(InputDtoTask dto){
		log.info("dto:{}",dto);
		return taskService.createTask(dto);
	}
}
