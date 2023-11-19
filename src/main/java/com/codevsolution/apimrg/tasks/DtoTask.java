package com.codevsolution.apimrg.tasks;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.codevsolution.apimrg.tasks.prv.Task;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class DtoTask {

	private String id;
	private String title;
	private String description;
	private Integer completed;
	private String owner;
	private String user;
	private String createdAt;
	private String endedAt;
	
	public DtoTask(Task task) {
		
		setId(task.getId());
		setTitle(task.getTitle());
		setDescription(task.getDescription());
		setCompleted(task.getCompleted());
		setOwner(task.getOwner());
		setUser(task.getUser());
		if (task.getCreatedAt()!=null)setCreatedAt(task.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); else setCreatedAt(LocalDateTime.now().atZone(ZoneId.of("Europe/Madrid")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		if (task.getEndedAt()!=null)setEndedAt(task.getEndedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}
	
	public Task getTask() {
		
		Task task = new Task();
		task.setId(id);
		task.setTitle(title);
		task.setDescription(description);
		task.setCompleted(completed);
		task.setUser(user);
		task.setOwner(owner);
		if(createdAt!=null)task.setCreatedAt(LocalDateTime.parse(createdAt));
		if(endedAt!=null)task.setEndedAt(LocalDateTime.parse(endedAt));
		return task;
	}
	
}
