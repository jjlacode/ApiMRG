package com.codevsolution.apimrg.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

import com.codevsolution.apimrg.tasks.prv.Task;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class UpdateDtoTask {

	private String id;
	private String title;
	private String description;
	private Integer completed;
	private String user;
	private String owner;
	private String createdAt;
	private String endedAt;
	
	public Task getTask() {
		
		Task task = new Task();
		task.setId(id);
		task.setTitle(title);
		task.setDescription(description);
		task.setCompleted(completed!=null?completed:0);
		task.setUser(user);
		task.setOwner(owner);
		if(createdAt!=null)task.setCreatedAt(LocalDateTime.parse(createdAt));else setCreatedAt(null);
		if(endedAt!=null)task.setEndedAt(LocalDateTime.parse(endedAt));else setEndedAt(null);
		return task;
	}
	
}
