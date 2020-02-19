package com.organizer.jpa;

import com.organizer.model.SchedulerTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerTaskRepository extends JpaRepository<SchedulerTask, Long> {

}
