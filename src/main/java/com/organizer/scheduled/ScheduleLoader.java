package com.organizer.scheduled;

import com.organizer.jpa.SchedulerTaskRepository;
import com.organizer.model.SchedulerTask;
import com.organizer.scheduled.notification.NotificationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleLoader {

    @Autowired
    private SchedulerTaskRepository schedulerTaskRepository;
    private NotificationEmail notificationEmail;

    public ScheduleLoader(SchedulerTaskRepository schedulerTaskRepository, NotificationEmail notificationEmail) {
        this.schedulerTaskRepository = schedulerTaskRepository;
        this.notificationEmail = notificationEmail;
    }

    public List<Runnable> loadScheduledTasks(){
        List<SchedulerTask> schedulerTasks = schedulerTaskRepository.findAll();
       return  schedulerTasks.stream()
               .map(this::createRunnableTask)
               .collect(Collectors.toList());
    }

    private Runnable createRunnableTask(SchedulerTask schedulerTask){

        return  () -> {
            String email = schedulerTask.getTask().getOwner().getEmail();
            notificationEmail.sendSimpleMessage(email, schedulerTask.getName(), schedulerTask.getText());
        };
    }
}
