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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.girija.learning.selenium.util.PropertyConfigurations;
/**
 * This code is taken from the Selenium IDE -> export option 
 * 
 * You need to have the Internet Explorer Driver Server
 * Get the same from the downloads page of Selenium @ http://www.seleniumhq.org/download/
 * 
 * */
public class PageNavigationWithInternetExplorerDriver {
	private WebDriver driver;
	private String baseUrl;			

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		// ignores Protected mode settings for different zones like internet, local intranet etc..
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// ignores the zoom setting set in IE
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		// sets the path to IEDriver
		System.setProperty("webdriver.ie.driver", PropertyConfigurations.getString("ie.driver.path"));
		driver = new InternetExplorerDriver(capabilities);
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
