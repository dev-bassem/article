package com.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
