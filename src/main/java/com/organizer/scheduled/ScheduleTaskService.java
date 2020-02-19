package com.organizer.scheduled;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduleTaskService {

    // Task Scheduler
    TaskScheduler scheduler;
    ScheduleLoader scheduleLoader;

    // A map for keeping scheduled tasks
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduleTaskService(TaskScheduler scheduler, ScheduleLoader scheduleLoader) {
        this.scheduler = scheduler;
        this.scheduleLoader = scheduleLoader;
    }


    public void addTaskToScheduler(int id, Runnable task) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new PeriodicTrigger(10, TimeUnit.SECONDS));
        jobsMap.put(id, scheduledTask);
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
    }

    // A context refresh event listener
    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }

    @PostConstruct
    public void init() {
        System.out.println("Post Constructor");
        List<Runnable> runnables = scheduleLoader.loadScheduledTasks();
        int index = 0;
        for(Runnable runnable : runnables){
            addTaskToScheduler(index, runnable);
            index++;
        }
    }

}