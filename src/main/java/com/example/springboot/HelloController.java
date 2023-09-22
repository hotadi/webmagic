package com.example.springboot;

import com.example.springboot.service.BCS;
import com.example.springboot.service.UrlProcessor;
import com.example.springboot.service.Utils;
import com.example.springboot.service.Varzesh3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		UrlProcessor.main(new String[]{"bcs.org", "https://www.bcs.org/sitemap.xml" ,"//h3/a"});
//		UrlProcessor.main(new String[]{"varzesh3.com", "https://www.varzesh3.com" ,"//h3/a"});
		//BCS.main(new String[]{});
//		Varzesh3.main(new String[]{});
		return "Greetings from Spring Boot!";
	}

}