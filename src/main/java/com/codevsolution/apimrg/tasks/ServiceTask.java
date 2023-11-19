package com.codevsolution.apimrg.tasks;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ServiceTask {

	Flux<DtoTask> listTasks();
	
	Flux<DtoTask> pendingTasks();

	Mono<DtoTask> getTask(String id);
	
	Flux<DtoTask> listTasksByUser(String user);
	
	Flux<DtoTask> listTasksByOwner(String owner);
	
	Flux<DtoTask> listTasksByOwnerOrUser(String owner,String user);
	
	Flux<DtoTask> pendingTasksByUser(String user);
	
	Flux<DtoTask> pendingTasksByOwner(String owner);
	
	Flux<DtoTask> pendingTasksByOwnerOrUser(String owner,String user);

	Mono<DtoTask> createTask(InputDtoTask dto);
	
	Mono<DtoTask> updateTask(UpdateDtoTask dto);

	Mono<Boolean> deleteTask(String id);
	
}
