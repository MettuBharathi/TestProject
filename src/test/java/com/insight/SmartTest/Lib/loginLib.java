 package com.insight.SmartTest.Lib;

 import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.insight.SmartTest.Pages.LoginPage;

 public class loginLib extends LoginPage {

        public void enterUserName(String userName) throws Throwable{
            type(txtLoginName,userName,"UserName");
        }
        
        public void enterPassword(String Password) throws Throwable{
            type(txtPassword,Password,"Password");
   

        } 
        
        public void clickOnLoginButton() throws Throwable{
            click(btnSubmit,"Login Button");
        }

        public void clickOnPasswordfield() throws Throwable{
            click(txtPassword,"Password Field");
            
        }
        public void loginIntoSmartApplication(String userName,String Password) throws Throwable{
            enterUserName(userName);       
            enterPassword(Password);
            clickOnLoginButton();
        }

 }