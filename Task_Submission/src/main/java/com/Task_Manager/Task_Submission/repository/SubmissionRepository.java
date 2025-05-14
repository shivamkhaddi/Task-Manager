package com.Task_Manager.Task_Submission.repository;

import com.Task_Manager.Task_Submission.modal.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission,Long> {

    List<Submission> findByTaskId(Long taskId);
}
