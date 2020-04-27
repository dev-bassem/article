package com.gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.entities.Provider;
import com.gestion.entities.Article;;
public interface ProviderRepository extends JpaRepository<Provider, Long>{

	
	@Query("FROM Article a WHERE a.provider.id = ?1")
	List<Article> findArticlesByProvider(long id);
}
