package com.javarush.jira.bugtracking;

import com.javarush.jira.common.error.IllegalRequestDataException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = TaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    public static final String REST_URL = "/api/task";

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping(value = "/{taskId}/addTags", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void addTags(@PathVariable("taskId") Optional<Long> taskId, @RequestParam("tags") List<String> tags) throws BindException {
        if (taskId.isPresent() || tags.isEmpty()) {
            throw new BindException("Incorrect request parameter or path variable.");
        }
        taskService.addTags(taskId.get(), tags);
    }
}
