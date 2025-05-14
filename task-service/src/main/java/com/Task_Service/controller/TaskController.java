package com.Task_Service.controller;

import com.Task_Service.modal.Task;
import com.Task_Service.modal.TaskStatus;
import com.Task_Service.modal.UserDto;
import com.Task_Service.service.TaskService;
import com.Task_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task createdTask= taskService.createTask(task,user.getRole());

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task= taskService.getTaskById(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required=false) TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        List<Task> tasks= taskService.assignedUsersTask(user.getId(), status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required=false) TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        List<Task> tasks= taskService.getAllTask(status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(@PathVariable Long id, @PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task tasks= taskService.assignedToUser(userId, id);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task req, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task tasks= taskService.updateTask(id,req,user.getId());

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception {

        Task tasks= taskService.completeTask(id);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception{

        taskService.deleteTask(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
