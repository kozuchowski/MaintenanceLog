package com.maintenecelog.maintenancelog;

import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@SpringBootApplication
public class MaintenanceLogApplication {


    public static void main(String[] args) {
        SpringApplication.run(MaintenanceLogApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner (UserRepository repository, PasswordEncoder encoder) {
//        return args -> {
//            repository.save( new User("mar", "kozuch", "mar",
//                    encoder.encode("bdjs64hkds7hbbcds"), "marek@gmail.com", "000 000 000", "1111111111"));
//
//        };
//    }

}
