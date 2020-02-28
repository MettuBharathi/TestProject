package com.insight.SmartTest.Pages;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class LoginPage extends ActionEngine
{
	
	public static By txtLoginName = By.cssSelector("input[name='userName']");

	public static By txtPassword = By.cssSelector("input[name='password']" );
	public static By btnSubmit = By.cssSelector("button[type='submit']");


}
