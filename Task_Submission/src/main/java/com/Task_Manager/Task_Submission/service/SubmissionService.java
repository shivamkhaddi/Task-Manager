package com.Task_Manager.Task_Submission.service;

import com.Task_Manager.Task_Submission.modal.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitTask(Long taskId, String githubLink, Long userId, String jwt)throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmission();

    List<Submission> getTaskSubmissionByTaskId(Long taskId);

    Submission acceptDeclineSubmission(Long id, String status)throws Exception;
}
