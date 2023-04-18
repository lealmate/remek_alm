package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    private final String URL = "https://lennertamas.github.io/portio/";
    private final By ACCEPT_TERMS_BUTTON = By.xpath("//*[@id = 'terms-and-conditions-button']");
    private final By LOGOUT_BUTTON = By.xpath("//*[@onclick = 'logout()']");
    private final By PROFILE_BUTTON = By.xpath("//*[@id = 'profile-btn']");
    private final By SEE_ALL_POST_BUTTON = By.xpath("//*[contains(@href, '/blog') and text() = 'See all post']");
    private final By IMAGE = By.xpath("//*[@alt = 'hero-image']");

    public boolean getLogoutButton() {
        return driver.findElement(LOGOUT_BUTTON).isDisplayed();
    }

    public void navigate(){
        driver.navigate().to(URL);
    }

    public void clickAcceptTermsButton(){
        driver.findElement(ACCEPT_TERMS_BUTTON).click();
    }

    public void clickLogoutButton(){
        driver.findElement(LOGOUT_BUTTON).click();
    }

    public void clickProfileButton(){
        driver.findElement(PROFILE_BUTTON).click();
    }

    public void clickSeeAllPostButton(){
        driver.findElement(SEE_ALL_POST_BUTTON).click();
    }

    public void saveImage(){
        String imgSRC = driver.findElement(IMAGE).getAttribute("src");
        try {
            URL imageURL = new URL(imgSRC);
            BufferedImage image = ImageIO.read(imageURL);
            ImageIO.write(image, "png", new File("img.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean lookForSuccessfulSave(){
        File f = new File("img.png");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    public void deleteImage(){
        File f = new File("img.png");
        f.delete();
        System.out.println("Image deleted");
    }

    public boolean lookForSuccessfulLogin(){
        try {
            driver.findElement(LOGOUT_BUTTON);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
