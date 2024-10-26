package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginTests {

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
		login 		= new LoginForm(driver);
		signup 		= new SignupForm(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null)
			driver.quit();
	}

	private boolean tryLogin(String username, String password) {
		driver.get("http://localhost:" + this.port + "/login");

		login.setUsername(username);
		login.setPassword(password);
		login.submit();
		return login.isSuccess();
	}

	private boolean tryCreateUser(String firstName, String lastName, String username, String password) {
		driver.get("http://localhost:" + this.port + "/signup");

		signup.fillForm(firstName, lastName, username, password);
		signup.submit();

		return signup.isSuccess();
	}

	@Test
	public void nonexistentUserLoginFailure() {
		Assertions.assertFalse(tryLogin("nguyenvanan", "nva@123"));
	}

	@Test
	public void loginWithSuccess() {
		Assertions.assertTrue(
			tryCreateUser(
			"Nguyen Van",
			"An",
			"nguyenvanan",
			"nva@123"
			)
		);

		Assertions.assertTrue(tryLogin("nguyenvanan", "nva@123"));
		Assertions.assertEquals("Home", driver.getTitle());
	}

}
