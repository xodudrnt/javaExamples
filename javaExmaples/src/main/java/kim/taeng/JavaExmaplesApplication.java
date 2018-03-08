package kim.taeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kim.taeng.service.threading.ExcutorExampleService;

@SpringBootApplication
public class JavaExmaplesApplication implements CommandLineRunner{

	@Autowired
	ExcutorExampleService excutorExampleService;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaExmaplesApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Main Thread Started");
		// executor 를 통한 thread 생성자 - 소비자 예제
		excutorExampleService.exmapleMain(2, 4);
	}

}
