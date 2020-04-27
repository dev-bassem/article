package com.gestion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.entities.Article;
import com.gestion.entities.Provider;
import com.gestion.service.ProviderService;






@Controller
@RequestMapping("/provider/")
public class ProviderController {
	
	@Autowired
	ProviderService service;
	
	@RequestMapping("all")
	public String viewHomeProviderPage(Model model) {
		
		List<Provider> providers = service.listAll();
		model.addAttribute("providers", providers);
		return "Provider/index";
	
	}
	

	@RequestMapping("new")
	public String showNewProviderPage(Model model) {
		Provider provider = new Provider();
		model.addAttribute("provider", provider);
		return "Provider/new";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("provider") Provider provider) {
		service.save(provider);
		return "redirect:all";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProviderPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("Provider/edit");
		Provider provider=service.get(id);
		mav.addObject("provider",provider);
		return mav;
	}
	@RequestMapping(value="/delete/{id}")
	public String deleteProvider(@PathVariable(name = "id") Long id, Model model) {
		service.delete(id);
		return "redirect:/provider/all";
		
	}
	
	@GetMapping("show/{id}")
	public String showProvider(@PathVariable("id") long id, Model model) {
	Provider provider = service.get(id);
					List<Article> articles = service.ArticlesProvider(id);
					for (Article a : articles)
					System.out.println("Article = " + a.getLabel());
					model.addAttribute("articles", articles);
					model.addAttribute("provider", provider);
					return "Provider/showProvider";
					}
	
	

}
