/**
 * 
 */
package com.codevsolution.apimrg.tasks.prv;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import lombok.Data;
/**
 * 
 */
@Data
public class Task {

	@Id 
	private String id;
	private String title;
	private String description;
	private Integer completed;
	private String owner;
	private String user;
	private LocalDateTime createdAt;
	private LocalDateTime endedAt;
}
