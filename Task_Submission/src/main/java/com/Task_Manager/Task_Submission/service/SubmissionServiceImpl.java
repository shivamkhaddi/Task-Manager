package com.Task_Manager.Task_Submission.service;


import com.Task_Manager.Task_Submission.modal.Submission;
import com.Task_Manager.Task_Submission.modal.TaskDto;
import com.Task_Manager.Task_Submission.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception {
        TaskDto task = taskService.getTaskById(taskId,jwt);

        if(task != null){
            Submission submission = new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        }
        throw new Exception("Task not found with id "+taskId);
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId).orElseThrow(()->new Exception("Task submission not found with id "+submissionId));
    }

    @Override
    public List<Submission> getAllTaskSubmission() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        Submission submission = getTaskSubmissionById(id);
        submission.setStatus(status);
        if(status.equals("Accept")){
           taskService.completeTask(submission.getTaskId());
        }

        return submissionRepository.save(submission);
    }
}
