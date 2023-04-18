package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    private final By HOME_BUTTON = By.xpath("//*[@href='landing.html']");
    private final By ABOUT_BUTTON = By.xpath("//*[contains(@href, '#about')]");
    private final By SERVICE_BUTTON = By.xpath("//*[contains(@href, '#service')]");
    private final By WORK_BUTTON = By.xpath("//*[contains(@href, '#portfolio')]");
    private final By RESUME_BUTTON = By.xpath("//*[contains(@href, '#resume')]");
    private final By SKILLS_BUTTON = By.xpath("//*[contains(@href, '#skills')]");
    private final By BLOG_BUTTON = By.xpath("//*[contains(@href, '#blog')]");
    private final By CONTACT_BUTTON = By.xpath("//*[contains(@href, '#contact')]");
    private final By HIRE_ME_BUTTON = By.xpath("//*[contains(text(), 'Hire Me')]");

    public void clickHomeButton(){
        driver.findElement(HOME_BUTTON).click();
    }

    public void clickAboutButton(){
        driver.findElement(ABOUT_BUTTON).click();
    }

    public void clickServiceButton(){
        driver.findElement(SERVICE_BUTTON).click();
    }

    public void clickWorkButton(){
        driver.findElement(WORK_BUTTON).click();
    }

    public void clickResumeButton(){
        driver.findElement(RESUME_BUTTON).click();
    }

    public void clickSkillsButton(){
        driver.findElement(SKILLS_BUTTON).click();
    }

    public void clickBlogButton(){
        driver.findElement(BLOG_BUTTON).click();
    }

    public void clickContactButton(){
        driver.findElement(CONTACT_BUTTON).click();
    }

    public void clickHireMeButton(){
        driver.findElement(HIRE_ME_BUTTON).click();
    }
}
