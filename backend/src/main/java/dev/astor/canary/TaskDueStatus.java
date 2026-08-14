package dev.astor.canary;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskDueStatus {

	OVERDUE("overdue"),
	DUE_TODAY("due_today"),
	UPCOMING("upcoming");

	private final String serialized;

	TaskDueStatus(String serialized) {
		this.serialized = serialized;
	}

	@JsonValue
	public String serialized() {
		return serialized;
	}
}