package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UnauthorizedAccessTestSuite {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	@BeforeAll
	static void setupChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
	}

	@BeforeEach
	public void initializeDriver() {
		driver = new ChromeDriver();
	}

	@AfterEach
	public void cleanupDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void verifyRedirectForUnauthorizedAccess() {
		driver.get("http://localhost:" + port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	void accessLoginPageAsUnauthorizedUser() {
		driver.get("http://localhost:" + port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	void accessSignupPageAsUnauthorizedUser() {
		driver.get("http://localhost:" + port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}
}
