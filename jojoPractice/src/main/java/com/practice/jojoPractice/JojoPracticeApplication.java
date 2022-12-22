package com.practice.jojoPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class JojoPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JojoPracticeApplication.class, args);
	}

	// 웹소켓을 사용하기 위해 엔드포인트 설정
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
