package kr.co.tabling.works;

import kr.co.tabling.works.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class WorksApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorksApplication.class, args);
	}

}
