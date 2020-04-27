package com.gestion.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.entities.Article;
import com.gestion.entities.Provider;
import com.gestion.service.ArticleService;
import com.gestion.service.ProviderService;

@Controller
@RequestMapping("/article/")
public class ArticleController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

	@Autowired
	ArticleService service;
	@Autowired
	ProviderService providerservice;

	@RequestMapping("all")
	public String viewHomeArticlePage(Model model) {
		List<Article> articles = service.listAll();
		model.addAttribute("articles", articles);
		return "Article/index";

	}

	@RequestMapping("new")
	public String showNewArticlePage(Model model) {
		Article article = new Article();
		model.addAttribute("providers", providerservice.listAll());
		model.addAttribute("article", article);
		return "Article/new";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("article") Article article,
			@RequestParam(name = "providerId", required = false) Long p, @RequestParam("files") MultipartFile[] files , 
			@RequestParam("file2") MultipartFile[] file2) {

		System.out.println(article.toString());
		Provider provider = providerservice.get(p);
		 article.setProvider(provider);
		
		StringBuilder fileName = new StringBuilder();
		 StringBuilder fileName2 = new StringBuilder();
		MultipartFile file = files[0];
		MultipartFile filepr = file2[0];
		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		Path fileNameAndPath2 = Paths.get(uploadDirectory, filepr.getOriginalFilename());

		fileName.append(file.getOriginalFilename());
		fileName2.append(filepr.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
			Files.write(fileNameAndPath2, filepr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 article.setPicture(fileName.toString());
		 article.setPictureprofile(fileName2.toString());
		 service.save(article);
		return "redirect:all";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProviderPage(@PathVariable(name = "id") Long id, Model model) {
		ModelAndView mav = new ModelAndView("Article/edit");
		Article article = service.get(id);
		mav.addObject("providers", providerservice.listAll());
		// model.addAttribute("providers", providerservice.listAll());
		mav.addObject("article", article);
		return mav;
	}

	@GetMapping("delete/{id}")
	public String deleteArticle(@PathVariable("id") long id, Model model) {
		service.delete(id);
		model.addAttribute("articles", service.listAll());
		return "redirect:/article/all";
	}

	@GetMapping("show/{id}")
	public String showArticleDetails(@PathVariable("id") long id, Model model) {
		Article article = service.get(id);
		model.addAttribute("article", article);
		return "Article/show";
	}

	

	@PostMapping("add/{id}")
	public String updateArticle(@PathVariable("id") long id, @Valid Article
	article, BindingResult result,
	Model model, @RequestParam(name = "providerId", required = false) Long
	p) {
	if (result.hasErrors()) {
	article.setId(id);
	return "article/updateArticle";
	}
	Provider provider = providerservice.get(p);
	article.setProvider(provider);
	service.save(article);
	model.addAttribute("articles", service.listAll());
	return "redirect:/article/all";
	}

}
