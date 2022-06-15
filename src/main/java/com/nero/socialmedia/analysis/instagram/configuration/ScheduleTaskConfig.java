package com.nero.socialmedia.analysis.instagram.configuration;

import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleTaskConfig {
    Logger log = CustomLoggerFactory.getLogger(ScheduleTaskConfig.class);

    @Profile("dev & schedule-ten-seconds")
    @Scheduled(cron = "0/10 * * ? * *")
    public void tenSecondsAnalyzeInstagramAccountsJob() {
        log.info("By Ten Seconds analyze Instagram Accounts Job");
    }

    @Profile("dev & schedule-minutely")
    @Scheduled(cron = "0 * * ? * *")
    public void minutelyAnalyzeInstagramAccountsJob() {
        log.info("Minutely analyze Instagram Accounts Job");
    }

    @Scheduled(cron = "0 0 * ? * *")
    public void hourlyAnalyzeInstagramAccountsJob() {
        log.info("Hourly analyze Instagram Accounts Job");
    }

    @Scheduled(cron = "0 0 0 ? * *")
    public void dailyAnalyzeInstagramAccountsJob() {
        log.info("Daily analyze Instagram Accounts Job");
    }

    @Scheduled(cron = "0 0 0 ? * SUN")
    public void weeklyAnalyzeInstagramAccountsJob() {
        log.info("Weekly analyze Instagram Accounts Job");
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void monthlyAnalyzeInstagramAccountsJob() {
        log.info("Monthly analyze Instagram Accounts Job");
    }
}
