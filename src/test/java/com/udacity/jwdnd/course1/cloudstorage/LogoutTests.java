package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LogoutTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private LoginForm login;
	private SignupForm signup;

	@BeforeAll
	static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		login = new LoginForm(driver);
		signup = new SignupForm(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null)
			driver.quit();
	}

	@Test
	public void logoutWithSuccess() {
		driver.get("http://localhost:" + this.port + "/signup");

		signup.fillForm("Nguyen Van",
				"An",
				"nguyenvanan",
				"nva@123");

		signup.submit();
		Assertions.assertTrue(signup.isSuccess());

		driver.get("http://localhost:" + this.port + "/login");

		login.setUsername("nguyenvanan");
		login.setPassword("nva@123");
		login.submit();
		Assertions.assertTrue(login.isSuccess());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/logout");
		Assertions.assertNotSame("Home", driver.getTitle());
	}

}
