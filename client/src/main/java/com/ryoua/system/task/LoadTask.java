package com.ryoua.system.task;

import com.ryoua.system.controller.LoadController;
import com.ryoua.system.controller.TrafficController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/19
 **/
@Configuration
public class LoadTask extends QuartzJobBean {
    @Bean
    public JobDetail loadSendTask() {
        return JobBuilder.newJob(LoadTask.class).withIdentity("loadTask").storeDurably().build();
    }

    @Bean
    public Trigger LoadQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(loadSendTask())
                .withIdentity("loadTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Autowired
    private LoadController loadController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        loadController.sendLoadInfo();
    }
}
