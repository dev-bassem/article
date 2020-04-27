package com.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.entities.Article;
import com.gestion.repository.ArticleRepository;


@Service
public class ArticleService {
    @Autowired
	ArticleRepository repo;
    
    public List<Article> listAll(){
		return repo.findAll();
	}
	public void save(Article article) {
		repo.save(article);
	}
	public Article get(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
