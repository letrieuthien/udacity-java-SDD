package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginForm {

    @FindBy(id = "login-form-container")
    private WebElement formContainer;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "login-submit-button")
    private WebElement submitButton;

    public LoginForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void submit() {
        submitButton.click();
    }

    public void setUsername(String username) {
        setField(usernameField, username);
    }

    public void setPassword(String password) {
        setField(passwordField, password);
    }

    public boolean isSuccess () {
        return !hasErrorMessage();
    }

    private boolean hasErrorMessage() {
        boolean hasError = false;
        try {
            hasError = !formContainer.findElements(By.id("invalid-credentials-message")).isEmpty();

        } catch (Exception ignored) {}

        return hasError;
    }

    private void setField(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }
}