package mk.finki.ukim.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan

class LabApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

}
