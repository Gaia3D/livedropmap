package gaia3d.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class HealthCheckConfiguration implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		log.info("@@@@@@@@ HealthCheckConfiguration configureTasks");
		taskRegistrar.setScheduler(scheduler());
	}
	
	@Bean
	public Executor scheduler() {
		return Executors.newScheduledThreadPool(1);
	}
}
