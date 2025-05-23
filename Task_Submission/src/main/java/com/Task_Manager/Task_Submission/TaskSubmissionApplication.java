package com.Task_Manager.Task_Submission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskSubmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSubmissionApplication.class, args);
	}

}
