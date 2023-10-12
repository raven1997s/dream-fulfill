package com.raven.dreamfulfill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DreamFulfillApplication {

    public static void main(String[] args) {
        //
        SpringApplication.run(DreamFulfillApplication.class, args);
    }

}
