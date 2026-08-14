package dev.astor.canary;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private static final List<Task> FIXED_TASKS = List.of(new Task(LocalDate.of(2026, 8, 13)));

	@GetMapping("/due")
	public List<TaskResponse> due(@RequestParam LocalDate referenceDate) {
		return FIXED_TASKS.stream()
				.map(task -> new TaskResponse(task.dueDate(),
						TaskClassifier.classify(task.dueDate(), referenceDate)))
				.toList();
	}

	private record Task(LocalDate dueDate) {
	}

	private record TaskResponse(LocalDate dueDate, TaskDueStatus dueStatus) {
	}
}