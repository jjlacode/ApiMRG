package com.codevsolution.apimrg.tasks.prv;

import org.springframework.stereotype.Service;

import com.codevsolution.apimrg.tasks.DtoTask;
import com.codevsolution.apimrg.tasks.InputDtoTask;
import com.codevsolution.apimrg.tasks.ServiceTask;
import com.codevsolution.apimrg.tasks.UpdateDtoTask;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ImplServiceTask implements ServiceTask{

	private final RepositoryTask data;

	@Override
	public Flux<DtoTask> listTasks() {
		return data.findAll().map(task -> new DtoTask(task));
	}

	@Override
	public Mono<DtoTask> getTask(String id) {
		return data.findById(id).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> listTasksByUser(String user) {
		return data.findByUser(user).map(task -> new DtoTask(task));
	}

	@Override
	public Mono<DtoTask> createTask(InputDtoTask dto) {
		return data.save(dto.getTask()).map(task -> new DtoTask(task));
	}

	@Override
	public Mono<Boolean> deleteTask(String id) {
		return data.deleteById(id).map(v -> data.findById(id).equals(Mono.empty()));		
	}

	@Override
	public Mono<DtoTask> updateTask(UpdateDtoTask dto) {
		return data.save(dto.getTask()).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> pendingTasks() {
		return data.findAllByCompletedBetween(-1, 100).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> pendingTasksByUser(String user) {
		return data.findByUserAndCompletedBetween(user, -1, 100).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> listTasksByOwner(String owner) {
		return data.findByOwner(owner).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> listTasksByOwnerOrUser(String owner, String user) {
		return data.findByOwnerOrUser(owner, user).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> pendingTasksByOwner(String owner) {
		return data.findByOwnerAndCompletedBetween(owner, -1, 100).map(task -> new DtoTask(task));
	}

	@Override
	public Flux<DtoTask> pendingTasksByOwnerOrUser(String owner, String user) {
		return data.findByOwnerOrUserAndCompletedBetween(owner, user, -1, 100).map(task -> new DtoTask(task));
	}
	
	
}
