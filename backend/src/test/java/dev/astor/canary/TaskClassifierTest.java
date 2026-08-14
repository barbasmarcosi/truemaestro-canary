package dev.astor.canary;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TaskClassifierTest {

	private static final LocalDate REFERENCE_DATE = LocalDate.of(2026, 8, 13);

	@Test
	void beforeReferenceDateIsOverdue() {
		TaskDueStatus status = TaskClassifier.classify(LocalDate.of(2026, 8, 12), REFERENCE_DATE);
		assertThat(status).isEqualTo(TaskDueStatus.OVERDUE);
	}

	@Test
	void equalToReferenceDateIsDueToday() {
		TaskDueStatus status = TaskClassifier.classify(REFERENCE_DATE, REFERENCE_DATE);
		assertThat(status).isEqualTo(TaskDueStatus.DUE_TODAY);
	}

	@Test
	void afterReferenceDateIsUpcoming() {
		TaskDueStatus status = TaskClassifier.classify(LocalDate.of(2026, 8, 14), REFERENCE_DATE);
		assertThat(status).isEqualTo(TaskDueStatus.UPCOMING);
	}
}
