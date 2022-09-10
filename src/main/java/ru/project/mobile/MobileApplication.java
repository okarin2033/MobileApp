package ru.project.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.UserRepo;

@SpringBootApplication
public class MobileApplication {


	public static void main(String[] args) {
		SpringApplication.run(MobileApplication.class, args);
	}

}
