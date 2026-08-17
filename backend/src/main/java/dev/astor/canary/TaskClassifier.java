package dev.astor.canary;

import java.time.LocalDate;

public final class TaskClassifier {

	private TaskClassifier() {
	}

	public static TaskDueStatus classify(LocalDate dueDate, LocalDate referenceDate) {
		if (dueDate.isBefore(referenceDate)) {
			return TaskDueStatus.OVERDUE;
		} else if (dueDate.isEqual(referenceDate)) {
			return TaskDueStatus.DUE_TODAY;
		}
		return TaskDueStatus.UPCOMING;
	}
}