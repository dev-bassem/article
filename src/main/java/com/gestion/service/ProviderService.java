package com.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.entities.Article;
import com.gestion.entities.Provider;
import com.gestion.repository.ProviderRepository;

@Service
public class ProviderService {
	
	@Autowired
	ProviderRepository repo;
	

    public List<Provider> listAll(){
		return repo.findAll();
	}
	public void save(Provider provider) {
		repo.save(provider);
	}
	public Provider get(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public List<Article> ArticlesProvider(long id) {
		return repo.findArticlesByProvider(id);
	}
	

}
