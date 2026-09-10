package com.example.audit; // Ajusta según la ubicación del archivo principal

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.audit")
@EnableJpaRepositories(basePackages = "com.example.audit.repository")
@EntityScan(basePackages = "com.example.audit.model")
public class MsPedidos360AuditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPedidos360AuditApplication.class, args);
    }
}