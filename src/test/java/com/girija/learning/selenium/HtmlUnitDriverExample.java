package com.girija.learning.selenium;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;



/**
 * This example uses the HtmlUnitDriver
 * 
 * The pros and cons from https://code.google.com/p/selenium/wiki/HtmlUnitDriver
 * 
 * This is currently the fastest and most lightweight implementation of WebDriver. As the name suggests, this is based on HtmlUnit.
 *	Pros
 *
 *   	Fastest implementation of WebDriver
 *   	A pure Java solution and so it is platform independent.
 *   	Supports Javascript 
 *
 *	Cons
 *
 *    	Emulates other browser's JS behaviour
 * 
 * */
public class HtmlUnitDriverExample {
	private WebDriver driver;
	private String baseUrl;

	@BeforeEach
	public void setUp() throws Exception {
		// here we emulate Chrome as the browser
		driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		baseUrl = "https://gsswain.com"; //$NON-NLS-1$
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void testExperienceSection() throws Exception {
		driver.get(baseUrl);
		WebElement experience = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/div[1]/section[1]/header/h5"));
		assertThat(experience, notNullValue());
		assertThat(experience.getText(), equalTo("Experience"));
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

}
