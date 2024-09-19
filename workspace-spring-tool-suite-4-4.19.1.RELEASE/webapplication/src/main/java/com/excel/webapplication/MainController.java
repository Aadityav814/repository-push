package com.excel.webapplication;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.excel.webapplication.model.Formdata;

import jakarta.validation.Valid;




@Controller
public class MainController {
	
	@GetMapping("/home")
public String  home(Model model) {
	System.out.println("this is my home page ");
	model.addAttribute("formData",new Formdata());
	
	return "home";
}
	@PostMapping("/prosess")
	public String prosessData( @Valid@ModelAttribute ("formData")Formdata formData, BindingResult result) {
		
		if(result.hasErrors()) {
			
			System.out.println(result);
			return "home";
		}else {
		System.out.println(formData.getEmail());
		return "success";
		}
	}
	

}
