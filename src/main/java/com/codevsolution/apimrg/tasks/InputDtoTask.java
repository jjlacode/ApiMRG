package com.codevsolution.apimrg.tasks;

import java.time.LocalDateTime;

import com.codevsolution.apimrg.tasks.prv.Task;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class InputDtoTask {

	private String title;
	private String description;
	private String user;
	private String owner;
	
	public Task getTask() {
		
		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setCompleted(0);
		task.setUser(user);
		task.setOwner(owner);
		task.setCreatedAt(LocalDateTime.now());
		task.setEndedAt(null);
		return task;
	}
	
}
