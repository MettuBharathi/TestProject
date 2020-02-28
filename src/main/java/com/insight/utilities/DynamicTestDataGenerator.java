package com.insight.utilities;

import org.apache.log4j.Logger;

import com.github.javafaker.Faker;
import com.insight.accelerators.ActionEngine;

public class DynamicTestDataGenerator {
	
	public static final Faker faker = new Faker();
	public static final  Logger LOG = Logger.getLogger(ActionEngine.class);
	
	//METHOD TO GENERATE DYNAMIC TESTDATA FOR  FIRSTNAME
		public static String generateRandomFirstName(){
			String firstname = null;
			try{
			 firstname = faker.name().firstName();
			}catch(Exception e){
				LOG.info(e.getMessage());			
			}
			return firstname;
			
		}
		
		//METHOD TO GENERATE DYNAMIC TESTDATA FOR  LASTNAME
		public static String generateRandomLastName(){
			String lastname = null;
			try{
			 lastname = faker.name().lastName();
			}catch(Exception e){
				LOG.info(e.getMessage());			
			}
			return lastname;
		}
		
		//METHOD TO GENERATE DYNAMIC TESTDATA FOR  PASSWORD
			public static String generateRandomPassword(){
				String pwd = null;
				try{
				 pwd = faker.internet().password(8, 16, true, true);
				}catch(Exception e){
					LOG.info(e.getMessage());			
				}
				return pwd;
			}
		//METHOD TO GENERATE DYNAMIC TESTDATA FOR  EMAIL
		public static String generateRandomEmail(){
			String email = null;
			try{
			 email = faker.internet().emailAddress();
			}catch(Exception e){
				LOG.info(e.getMessage());			
			}
			return email;
		}
		//METHOD TO GENERATE DYNAMIC TESTDATA FOR  COMPANY
		public static String generateRandomCompany(){
			String companyname = null;
			try{
			 companyname = faker.company().name();
			}catch(Exception e){
				LOG.info(e.getMessage());			
			}
			return companyname;
		}
		
		//METHOD TO GENERATE DYNAMIC TESTDATA FOR  CELL NUMBER
		public static String generateRandomCellNumber(){
			String cellnumber = null;
			try{
			 cellnumber = faker.phoneNumber().cellPhone();
			}catch(Exception e){
				LOG.info(e.getMessage());			
			}
			return cellnumber;
		}

		//METHOD TO GENERATE DYNAMIC TESTDATA FOR  PHONE NUMBER
		public static String generateRandomPhoneNumber(){
			String phonenumber = null;
			try{
			 phonenumber = faker.phoneNumber().phoneNumber();	
			}catch(Exception e){
				LOG.info(e.getMessage());			
			}
			return phonenumber;
		}

}