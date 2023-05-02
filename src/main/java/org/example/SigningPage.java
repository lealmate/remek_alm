package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigningPage extends BasePage{

    public SigningPage(WebDriver driver) {
        super(driver);
    }

    //Elements to be located
    private final By LOGIN_FORM_BUTTON = By.xpath("//*[@style = 'display: block;']//*[@id = 'login-form-button']");
    private final By REGISTER_FORM_BUTTON = By.xpath("//*[@id = 'register-form-button']");
    private final By USERNAME = By.xpath("//*[@placeholder = 'Username']");
    private final By PASSWORD = By.xpath("//*[@placeholder = 'Password']");
    private final By LOGIN_BUTTON = By.xpath("//*[text() = 'Login' and @onclick = 'myFunction()']");
    private final By REGISTER_BUTTON = By.xpath("//*[text() = 'Register' and @onclick = 'registerUser()']");
    private final By USERNAME_REGISTER = By.xpath("//*[@id = 'register-username']");
    private final By PASSWORD_REGISTER = By.xpath("//*[@id = 'register-password']");
    private final By EMAIL_REGISTER = By.xpath("//*[@id = 'register-email']");
    private final By DESCRIPTION_REGISTER = By.xpath("//*[@id = 'register-description']");
    private final By SUCCESSFUL_REGISTRATION = By.xpath("//*[text() = 'User registered!']");

    //Functions
    public void fillUsername(String username){
        driver.findElement(USERNAME).sendKeys(username);
    }

    public void fillPassword(String password){
        driver.findElement(PASSWORD).sendKeys(password);
    }

    public void fillUsernameRegister(String username){
        driver.findElement(USERNAME_REGISTER).sendKeys(username);
    }

    public void fillPasswordRegister(String password){
        driver.findElement(PASSWORD_REGISTER).sendKeys(password);
    }

    public void fillEmailRegister(String email){
        driver.findElement(EMAIL_REGISTER).sendKeys(email);
    }

    public void fillDescriptionRegister(String description){
        driver.findElement(DESCRIPTION_REGISTER).sendKeys(description);
    }

    public void clickLoginFormButton(){
        driver.findElement(LOGIN_FORM_BUTTON).click();
    }

    public void clickRegisterFormButton(){
        driver.findElement(REGISTER_FORM_BUTTON).click();
    }

    public void clickLoginButton(){
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clickRegisterButton(){
        driver.findElement(REGISTER_BUTTON).click();
    }

    public void registrationProcess(String username, String password, String email, String description){
        fillUsernameRegister(username);
        fillPasswordRegister(password);
        fillEmailRegister(email);
        fillDescriptionRegister(description);
    }

    public void loginProcess(String username, String password){
        fillUsername(username);
        fillPassword(password);
    }

    public boolean lookForSuccessfulRegistration(){
        try {
            driver.findElement(SUCCESSFUL_REGISTRATION);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean lookForSuccessfulLogout(){
        try {
            driver.findElement(LOGIN_BUTTON);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void login(String username, String password){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.navigate();
        landingPage.clickAcceptTermsButton();
        loginProcess(username, password);
        clickLoginButton();
    }

    public void register(String username, String password, String email, String description){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.navigate();
        landingPage.clickAcceptTermsButton();
        clickRegisterFormButton();
        registrationProcess(username, password, email, description);
        clickRegisterButton();
    }

    public void registerThenLogin(String username, String password, String email, String description){
        register(username, password, email, description);
        clickLoginFormButton();
        loginProcess("kosza", "lajos");
        clickLoginButton();
    }
}