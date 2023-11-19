/**
 * 
 */
package com.codevsolution.apimrg.tasks.prv;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
/**
 * 
 */
public interface RepositoryTask extends ReactiveMongoRepository<Task, String>{

	Flux<Task> findByUser(String user);
	Flux<Task> findByOwner(String owner);
	Flux<Task> findByOwnerOrUser(String owner, String user);
	Flux<Task> findAllByCompletedBetween(Integer inicio, Integer fin);
	Flux<Task> findByUserAndCompletedBetween(String user,Integer inicio, Integer fin);
	Flux<Task> findByOwnerAndCompletedBetween(String owner,Integer inicio, Integer fin);
	Flux<Task> findByOwnerOrUserAndCompletedBetween(String owner,String user,Integer inicio, Integer fin);
}
