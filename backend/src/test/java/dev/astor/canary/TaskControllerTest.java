package dev.astor.canary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void dueEndpointWithExplicitReferenceDateReturnsDueTodayForEqualFixture() throws Exception {
		mockMvc.perform(get("/api/tasks/due").param("referenceDate", "2026-08-13"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[?(@.dueDate == '2026-08-13')].dueStatus").value("due_today"));
	}

	@Test
	void dueEndpointWithoutReferenceDateReturnsBadRequest() throws Exception {
		mockMvc.perform(get("/api/tasks/due"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void dueEndpointWithMalformedReferenceDateReturnsBadRequest() throws Exception {
		mockMvc.perform(get("/api/tasks/due").param("referenceDate", "not-a-date"))
				.andExpect(status().isBadRequest());
	}
}
