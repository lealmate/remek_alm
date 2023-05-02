package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    //Elements to be located
    private final By NAME = By.xpath("//*[@id = 'name']");
    private final By BIO = By.xpath("//*[@id = 'bio']");
    private final By PHONE_NUMBER = By.xpath("//*[@id = 'phone-number']");
    private final By SAVE_PROFILE_BUTTON = By.xpath("//*[@onclick = 'editUser()']");
    private final By DELETE_ACCOUNT_BUTTON = By.xpath("//*[@onclick = 'showRealDeleteAccBtn()']");
    private final By CONFIRM_DELETE_ACCOUNT_BUTTON = By.xpath("//*[@onclick = 'deleteAccount()']");
    private final By SUCCESSFUL_EDIT_MESSAGE = By.xpath("//*[@id = 'edit-alert' and @style='display: block;']");

    //Functions
    public void clickSaveProfileButton(){
        driver.findElement(SAVE_PROFILE_BUTTON).click();
    }

    public void clickDeleteAccountButton(){
        driver.findElement(DELETE_ACCOUNT_BUTTON).click();
    }

    public void clickConfirmDeleteAccountButton(){
        driver.findElement(CONFIRM_DELETE_ACCOUNT_BUTTON).click();
    }

    public void setName(String name){
        driver.findElement(NAME).sendKeys(name);
    }

    public void setBio(String bio){
        driver.findElement(BIO).sendKeys(bio);
    }

    public void setPhoneNumber(String phoneNumber){
        driver.findElement(PHONE_NUMBER).sendKeys(phoneNumber);
    }

    public void editProfile(String name, String bio, String phoneNumber){
        setName(name);
        setBio(bio);
        setPhoneNumber(phoneNumber);
    }

    public boolean lookForSuccessfulEdit(){
        try {
            driver.findElement(SUCCESSFUL_EDIT_MESSAGE);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
