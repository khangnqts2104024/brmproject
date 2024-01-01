package com.example.brmproject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrmprojectApplication {
//confi modelMapper
    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();

    }

    //config passwork encoder.



    //
    public static void main(String[] args) {
        SpringApplication.run(BrmprojectApplication.class, args);
    }

}
