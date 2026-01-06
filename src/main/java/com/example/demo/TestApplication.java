package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);

		Tea tea1 = new Tea( "Красный");
		System.out.println("ID: "+ tea1.getId() + " Чай: = " +tea1.getName());
		Tea tea2 = new Tea("1234","Черный");
		tea2.setName();
		System.out.println("ID: "+ tea2.getId() + " Чай: = " +tea2.getName());
	}
}