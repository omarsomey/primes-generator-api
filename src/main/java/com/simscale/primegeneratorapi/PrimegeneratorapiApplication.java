package com.simscale.primegeneratorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import java.sql.*;

@EnableRetry
@SpringBootApplication
public class PrimegeneratorapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimegeneratorapiApplication.class, args);
	}
}


