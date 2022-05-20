package com.checklist.iteanz.service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.checklist.iteanz.CheckListApplication;
import com.checklist.iteanz.utility.UtilityService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class ChecklistService {
	
	
	private  String  inputurl;
	private  String  inputurl1;
	private static String  inputurl2;
	private static String[] uname;
	private static String[] design;
	private static String[] action;
	private static String filePath;
//	private static String  outputurl;
	private static String  username;
	static String outputFile = "";
	static int countPages;
	private static String reqno;
	private static String reqstatus;
	private static String designation;
	private static String actionBy;
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(CheckListApplication.class);
	
	public void printDetails() {

		
		
		
		
		
		
		
		
		
			
			
//			while (true) {
//				File directory = new File(System.getProperty("java.io.tmpdir")+"\\temp\\myfiles");
//			
//			 if (directory.isDirectory()) {
//		         String[] files = directory.list();
//		         if (directory.length() > 0) {
//			 
		    inputurl=null;
		    username=null;
		    designation=null;
			 
			String date;
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			date = simpleDateFormat.format(new Date());
			String expirydate="9999-06-01";
			log.info("today date :: "+date);
			log.info("Expiry date :: "+expirydate);
		    
			try
			{
				Date d1 = simpleDateFormat.parse(date); 
			    Date d2 = simpleDateFormat.parse(expirydate); 
				if(d1.before(d2))
				{
					log.info("Date is not Expired Enter into application");
//					boolean outputSrl = new File(System.getProperty("java.io.tmpdir") + "\\emcure\\output\\").mkdirs();
					boolean outputSrl = new File(System.getProperty("java.io.tmpdir") + "//emcure//output//").mkdirs();
//				    File file = new File(System.getProperty("java.io.tmpdir")+"\\temp\\temp.txt");
				    File file = new File(System.getProperty("java.io.tmpdir")+"//temp//temp.txt");
					log.info("Dynamic Temp Path:" + System.getProperty("java.io.tmpdir"));
				        BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader(file));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							log.error("1001   :: Temp File not found  " + e.getMessage());
							e.printStackTrace();
							System.exit(0); 
						}
				        String line;
				        try {
				        	log.info("Temp file is reading....");
							while ((line = br.readLine()) != null) {
//							        	if (inputurl==null)
//							        	{
//							        		inputurl=line;
//								        }
//							        	else if ( outputurl  == null && inputurl != null){
//								        	  outputurl=line;
//								        }
//							        	else  if (outputurl  != null && username==null){
//								        	 username=line;
//								        }
//							        	else if(username != null && reqno==null) {
//							        		reqno=line;
//							        	}
//							        	else if(reqno != null && reqstatus==null) {
//							        		reqstatus=line;
//							        	}
							        	
							        	if (inputurl==null)
							        	{
							        		inputurl=line;
								        }
							        	else if (username   == null && inputurl != null){
							        		username=line;
								        }
							        	else  if (username  != null && designation ==null){
							        		designation=line;}
							        	
							}
							log.info("inputurl : "+inputurl);
							log.info("username : "+username);
						        uname  = username.split(",");
						        for (int i=0; i<uname.length; i++)
						        	log.info("uname : "+uname[i]);
						        design  = designation.split(",");
//						        action  = actionBy.split(",");
						        
						        file=null;
						        inputurl2=inputurl;
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							log.error("1001   ::Error in reading temp file   " + e.getMessage());
							e.printStackTrace();
							System.exit(0);
						}
				        try {
							br.close();
						} catch (IOException e) {
							log.error("1001   :: Error in close buffered reader  " + e.getMessage());
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(0);
						}
				        try {
				        	boolean returnResult = editpdf();
				            UtilityService utilityServices=new UtilityService();
				 	        if(returnResult) {
				 	        	boolean cutpast = utilityServices.cutAndPast(inputurl2,outputFile);
				 	        	
				 	        	if(cutpast)
				 	        	{
//				 	        		File txtfile = new File(System.getProperty("java.io.tmpdir")+"\\temp\\output.txt");
				 	        		File txtfile = new File(System.getProperty("java.io.tmpdir")+"//temp//output.txt");
					 	        		try {
					 	   				br = new BufferedReader(new FileReader(txtfile));
					 	   			} catch (FileNotFoundException e) {
					 	   				log.error("1001   :: The Output file is not found :" + e.getMessage());
					 	   				e.printStackTrace();
					 	   				System.exit(0);
					 	   			}
					 	        		String line1;
					 	        		while ((line1 = br.readLine()) != null) {
					 	        			 if (!line1.trim().equals("")) {
					 	        			if(line1.equalsIgnoreCase("."))
					 	        			{
					 	        				log.info("Output file is updated to S");
					 	        				String line2=line1.replace(".", "S");
					 	        				 FileWriter myWriter = new FileWriter(txtfile);
					 	        			      myWriter.write(line2);
					 	        			      myWriter.close();
					 	        			}
					 	        		}
					 	        		}
				 	        	}
				 	        }
						} 
				        catch(FileNotFoundException e)
				        {
				        	log.error("1001   :: File Not Found in Input folder : " + e.getMessage());
				        	e.printStackTrace();
				        	System.exit(0);
				        }
				        catch (IOException e) {
							log.error("1001   ::  " + e.getMessage());
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(0);
						} catch (DocumentException e) {
							log.error("1001   ::   " + e.getMessage());
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(0);
						}
//				        System.exit(0);
				}
				else
				{
					log.info("The EXE Date is expired");
					System.out.println("The EXE Date is expired");
					System.exit(0);
				}
			}
			catch(Exception e)
			{
				log.error("Invalid date Please give valid date format");
				e.printStackTrace();
				System.exit(0);
			}
			
		}
		
		public  Boolean editpdf() throws FileNotFoundException,IOException, DocumentException {
	        /* example inspired from "iText in action" (2006), chapter 2 */
			log.info("Enter into Edit pdf Function...");
			log.info("Enter into Edit pdf Function... "+inputurl);
		    for (String file1 : ReturnData(inputurl)) {
		    	
	        	filePath = file1;
				File pdfFile = new File(filePath);
//				outputFile = System.getProperty("java.io.tmpdir") + "\\emcure\\output\\" + pdfFile.getName();
				outputFile = System.getProperty("java.io.tmpdir") + "//emcure//output//" + pdfFile.getName();
				log.info("outputFile name"+outputFile);
				if(inputurl!=null)
	        	{
//					fName = ReturnData(inputurl);
		        	inputurl1=file1;
	        	}
	        	log.info("file1 name"+file1);
		    //}
		    
	        PdfReader reader = new PdfReader(inputurl1); // input PDF
	        PdfStamper stamper = new PdfStamper(reader,
	          new FileOutputStream(outputFile)); // output PDF
	        BaseFont bf = BaseFont.createFont(
	                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
	        Date date = new Date();
	        countPages=reader.getNumberOfPages();
	        String disptext;
	        disptext="Request number -"+" "+reqno+" "+"Printed By -"+" "+username+" "+"Printed on Date and Time -"+" "+dateFormat.format(date);
	        String disptext1;
	        disptext1="Request Status -"+" "+reqstatus;
	        
	        //akshay
	        
	        String t1,t2,t3;
//	        t1="Name :  "+username+"    "+"Date and Time :  "+" "+dateFormat.format(date);

	        //t2="Date and Time :"+" "+dateFormat.format(date);
	        
	        
	        
	        //checklist changes
//	        String t1,t2,t3;
//	        t1=""+reqno+" "+"Printed By -"+" "+username;
//	        t2="Printed on Date and Time -"+" "+dateFormat.format(date);
	       // t3="Request Status -"+" "+reqstatus;
	        log.info("Display text format in pdf: "+disptext+" : "+disptext1);
	        //loop on pages (1-based)
	        for (int i=1; i<=reader.getNumberOfPages(); i++){
	        	/* this code for artwork
	        	 * 
	            PdfContentByte over = stamper.getOverContent(i);
	            over.beginText();
	            over.setFontAndSize(bf, 10);    // set font and size
	            over.setTextMatrix(20,80);   // set x,y position (0,0 is at the bottom left)
	            String text="Pages "+i+" of "+countPages;
		        over.showText(disptext);
		        over.showTextAligned(0, disptext1, 20, 70, 0);
		        over.showTextAligned(0, text, 20, 40, 0);
	            over.endText();
	            */
	        	  PdfContentByte over = stamper.getOverContent(i);
	              over.beginText();
	              over.setFontAndSize(bf, 6);    // set font and size
	              over.setTextMatrix(40,70);   // set x,y position(155,40) (0,0 is at the bottom left)
	             // String text="Pages "+i+" of "+countPages;
	              
	              /* working code
	              
	  	          over.showText(t1);
	  	          over.showTextAligned(0, t2, 155, 30, 0);
	  	        //  over.showTextAligned(0, t3, 155, 20, 0);
	              over.endText();
	              */
//	              int linePos=70;
//	              for (int j=0; j<uname.length; j++) {
////	            	  t1="Name :  "+uname[j]+"    "+"Date and Time :  "+" "+dateFormat.format(date);
////	            	 t1= ""+action[j]+" :  "+uname[j]+"    "+"Designation :  "+design[j]+"    "+"Date and Time :  "+" "+dateFormat.format(date);
//	            	 t1= "Name :  "+uname[j]+"             "+"Designation :  "+design[j]+"              "+"Date/Time :  "+" "+dateFormat.format(date);
//	            	 log.info("t1 : "+uname[j]);
//	            	  over.showTextAligned(0, t1, 40, linePos, 0);
//	            	  linePos-=10;
//			           }
	              String[] u= username.split(",");
	              String s="";
	              for(String ui:u) {
	            	  String ss=ui+"      ";
	            	  s+=ss;
	              }
	              
	              String[] ud= designation.split(",");
	              String sd="";
	              for(String uid:ud) {
	            	  String ssd=uid+"      ";
	            	  sd+=ssd;
	              }
	              
	              
	              
	              
	              
	              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	              DateTimeFormatter dtff = DateTimeFormatter.ofPattern("HH:mm:ss");
	                 LocalDateTime now = LocalDateTime.now();
//	                 System.out.println(dtf.format(now));
//	              System.out.println(dtff.format(now));
	              
	              
	              
//	              String[] ddd= dateFormat.format(date).split(" ");
	              String datep=dtf.format(now);
	              String time =dtff.format(now);
	              
	             
	             
	              
//	              String t4="asdfgg    adfdfgsg    adsfdfsd   fdsfsdf   dsfdsfdsfdsf   gfsdgfsgsg  fgdfgfdsfs";
	             
	              over.showTextAligned(0, "Name", 15, 50, 0);
	              over.showTextAligned(0, "Designation", 15, 40, 0);
	              over.showTextAligned(0, "Date", 15, 30, 0);
	              over.showTextAligned(0, "Time", 15, 20, 0);
	              int x=0;
	              for (int ii=0 ;ii<ud.length;ii++) {
	            	  x+=75;
	            	  over.showTextAligned(0, u[ii], x, 50, 0);
	 	             
		              over.showTextAligned(0, ud[ii], x, 40, 0);
		             
		              over.showTextAligned(0,datep, x, 30, 0);
		              over.showTextAligned(0,time, x, 20, 0);
		              
	              }
	             
	       
	              
	              
	              
//	              over.showText(t1);
//	              
//	  	          over.showTextAligned(0, t1, 155, 60, 0);
//	  	       
//	  	        over.showTextAligned(0, t1, 155, 50, 0);
//	  	      over.showTextAligned(0, t1, 155, 40, 0);
//	  	    over.showTextAligned(0, t1, 155, 30, 0);
//	  	  over.showTextAligned(0, t1, 155, 20, 0); 
//	  	over.showTextAligned(0, t1, 155, 10, 0);
//	      	  	over.showTextAligned(0, t1, 155, 10, 0);
	  	        
	  	        //  over.showTextAligned(0, t3, 155, 20, 0);
	              over.endText();
	              
	              
	              
	              
	              
	        
	        }
	        log.info("Edit pdf is completed");
	        stamper.close();}
			return true;
		    }

		public   List<String> ReturnData(String inputurl) {
			log.info("inputt url"+inputurl);
			
		    	Path folderPath = Paths.get(inputurl);
		        List<String> filesName = new ArrayList<String>();
		        List<String> fileNames = new ArrayList<>();
		        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
		            for (Path path : directoryStream) {
		                fileNames.add(path.toString());
		                log.info(path.toString());
		            }
		        } catch (IOException ex) {
		            log.error("Error reading files");
		            ex.printStackTrace();
		            System.exit(0);
		        }
		        for (String file : fileNames) {
		        	log.info(file);
					filesName.add(file);
		        }
		    	return fileNames;
		    }

		public int add(int i, int i2) {
			// TODO Auto-generated method stub
			return i+i2;
		}
	}

	
