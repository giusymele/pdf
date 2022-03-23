package com.pdf.ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EntityScan({"com.pdf.ex.entity"})
//@EnableJpaRepositories(basePackages = "com.pdf.ex.repository")
@SpringBootApplication
public class PdfApplication {

    public static void main(String[] args) {
//        SpringApplicationBuilder builder = new SpringApplicationBuilder(PdfApplication.class);
//        builder.headless(false);
//        builder.run(args);
//        MainFrame mf = new MainFrame();
//        mf.setVisible(false);

	SpringApplication.run(PdfApplication.class, args);
    }

}
