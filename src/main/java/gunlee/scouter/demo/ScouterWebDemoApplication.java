package gunlee.scouter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class ScouterWebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScouterWebDemoApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(2);
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setQueueCapacity(5);
		return taskExecutor;
	}
}
