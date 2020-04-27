package com.gestion;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gestion.controller.ArticleController;

@SpringBootApplication
public class GestionArticleApplication {

	public static void main(String[] args) {
		new File(ArticleController.uploadDirectory).mkdir();
		SpringApplication.run(GestionArticleApplication.class, args);
	}

}
