package com.ryoua.system.task;

import com.ryoua.system.controller.TrafficController;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/16
 **/
@Configuration
public class TrafficTask extends QuartzJobBean {
    @Bean
    public JobDetail trafficSendTask() {
        return JobBuilder.newJob(TrafficTask.class).withIdentity("trafficTask").storeDurably().build();
    }

    @Bean
    public Trigger TrafficQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(trafficSendTask())
                .withIdentity("trafficTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Autowired
    private TrafficController trafficController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        trafficController.sendTrafficInfo();
    }
}
