package com.Task_Manager.Task_Submission.controller;


import com.Task_Manager.Task_Submission.modal.Submission;
import com.Task_Manager.Task_Submission.modal.UserDto;
import com.Task_Manager.Task_Submission.service.SubmissionService;
import com.Task_Manager.Task_Submission.service.TaskService;
import com.Task_Manager.Task_Submission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Submission> submitTask(
            @RequestParam Long taskId,
            @RequestParam String githubLink,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId,githubLink,user.getId(),jwt);

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);

        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Submission>> getAllSubmission(
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getAllTaskSubmission();

        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllSubmissionByTaskId(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getTaskSubmissionByTaskId(taskId);

        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptDeclineSubmission(
            @PathVariable Long id,
            @RequestParam("status") String status,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(id,status);

        return new ResponseEntity<>(submission,HttpStatus.OK);
    }
}
