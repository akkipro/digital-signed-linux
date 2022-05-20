package com.checklist.iteanz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.checklist.iteanz.service.ChecklistService;
@CrossOrigin(origins = "*")
@RestController
public class ChecklistController {
	
//	ChecklistService checklistService= new ChecklistService();
	@Autowired
	ChecklistService checklistService;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/printpdf")
	public String pdfPrint(){
		 checklistService.printDetails();
		 String r= "done";
		return r;
	}
	@GetMapping("/gh/{i}")
	public int dfg(@PathVariable int i){
		return checklistService.add(4,i);
		
		
	}

}
