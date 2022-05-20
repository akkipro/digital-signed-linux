package com.checklist.iteanz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.checklist.iteanz.utility.UtilityService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


@SpringBootApplication
public class CheckListApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(CheckListApplication.class, args);
	}
	}
		
//		while (true) {
//			File directory = new File(System.getProperty("java.io.tmpdir")+"\\temp\\myfiles");
//		
//		 if (directory.isDirectory()) {
//	         String[] files = directory.list();
//	         if (directory.length() > 0) {
//	         if (directory.length() > 0) {

	