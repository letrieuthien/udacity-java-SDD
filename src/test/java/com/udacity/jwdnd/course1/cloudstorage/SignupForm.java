package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;

public class SignupForm {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "signup-submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-form-container")
    private WebElement formContainer;

    private final WebDriverWait wait;

    public SignupForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 200);
    }


    public void fillForm(String firstName, String lastName, String username, String password) {
        setInputField(firstNameField, firstName);
        setInputField(lastNameField, lastName);
        setInputField(usernameField, username);
        setInputField(passwordField, password);
    }

    public void submit() {
        submitButton.click();
    }

    private void setInputField(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }

    public boolean isSuccess() {
        return !hasErrorMessage();
    }


    private boolean hasErrorMessage() {
        return findErrorMessage().isPresent();
    }

    private Optional<WebElement> findErrorMessage() {
        try {
            return Optional.of(wait.until(ExpectedConditions.visibilityOf(formContainer.findElement(By.id("signup-error-message")))));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}