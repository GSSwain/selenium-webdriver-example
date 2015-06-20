package com.girija.learning.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.girija.learning.selenium.util.PropertyConfigurations;
/**
 * This code is taken from the Selenium IDE -> export option 
 * 
 * You need to have the Chrome Driver
 * Get the same @ https://sites.google.com/a/chromium.org/chromedriver/downloads
 * More on chrome driver @ https://sites.google.com/a/chromium.org/chromedriver/home
 * */
public class PageNavigationWithChromeDriver {
	private WebDriver driver;
	private String baseUrl;			

	@Before
	public void setUp() throws Exception {
		// sets the path to Chrome Driver
		System.setProperty("webdriver.chrome.driver", PropertyConfigurations.getString("chrome.driver.path"));
		driver = new ChromeDriver();
		baseUrl = "http://docs.seleniumhq.org/"; //$NON-NLS-1$
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void testSeleniumWebSiteNavigation() throws Exception {

		driver.get(baseUrl);
		assertEquals("Selenium - Web Browser Automation", driver.getTitle()); //$NON-NLS-1$

		driver.findElement(By.linkText("Projects")).click(); //$NON-NLS-1$
		assertEquals("Selenium Projects", driver.getTitle()); //$NON-NLS-1$
		assertTrue(isElementPresent(By.linkText("Selenium WebDriver"))); //$NON-NLS-1$
		assertTrue(isElementPresent(By.linkText("Selenium Grid"))); //$NON-NLS-1$
		assertEquals(
				"Selenium IDE", driver.findElement(By.xpath("//div[@id='mainContent']/div/h3[3]")).getText()); //$NON-NLS-1$ //$NON-NLS-2$
		assertTrue(isElementPresent(By.linkText("Selenium Remote Control"))); //$NON-NLS-1$

		driver.findElement(By.linkText("Download")).click(); //$NON-NLS-1$
		assertEquals("Downloads", driver.getTitle()); //$NON-NLS-1$
		assertEquals(
				"Downloads", driver.findElement(By.cssSelector("h2")).getText()); //$NON-NLS-1$ //$NON-NLS-2$

		driver.findElement(By.linkText("Documentation")).click(); //$NON-NLS-1$
		assertEquals(
				"Selenium Documentation — Selenium Documentation", driver.getTitle()); //$NON-NLS-1$
		assertEquals(
				"Selenium Documentation", driver.findElement(By.cssSelector("#selenium-documentation > h1")).getText()); //$NON-NLS-1$ //$NON-NLS-2$

		driver.findElement(By.linkText("Support")).click(); //$NON-NLS-1$
		assertEquals("Getting Help", driver.getTitle()); //$NON-NLS-1$

		driver.findElement(By.linkText("About")).click(); //$NON-NLS-1$
		assertEquals("About Selenium", driver.getTitle()); //$NON-NLS-1$
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
