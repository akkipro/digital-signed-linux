package com.checklist.iteanz.utility;

import java.io.File;
import java.util.List;


public class UtilityService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(UtilityService.class);
	
	
	@SuppressWarnings("resource")
//	public Boolean cutAndPast(String input, String output)
	public Boolean cutAndPast(String input, String output){
			log.info("Enter into Utility Service Function;;;;..."+input);
			boolean deleted = false;
			File file = new File(input);
			
//			for(String fname : input)
//			{
//				 file = new File(fname);
//				file.delete();
//			}
			
			for (File subfile : file.listFiles()) {
				subfile.delete();
				log.info("File deleted from input folder successfully"+subfile);
				deleted = true;
				}
			
			
			
			
//			if (file.delete()) {
//				log.info("File deleted from input folder successfully");
//				deleted = true;
//			} else {
//				log.info("Failed to delete the file");
//				System.out.println("Failed to delete file from input folder");
//			}
			if (deleted) {
				return true;
			} else {
				return false;
			}
	}
}
