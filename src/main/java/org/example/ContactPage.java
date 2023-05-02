package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactPage extends BasePage{
    public ContactPage(WebDriver driver) {
        super(driver);
    }

    //Elements to be located
    private final By CONTACT_INFO = By.xpath("//*[@class = 'section contact__info']/div/div/div");

    //Functions
    public void saveContactInfoToFile() throws IOException {
        List<WebElement> contactInfo = driver.findElements(CONTACT_INFO);
        List<String> info = new ArrayList<>();
        for(WebElement element : contactInfo){
            String header = element.findElement(By.tagName("h4")).getText();
            String content = element.findElement(By.tagName("p")).getText();
            info.add(header + " : " + content);
        }
        FileWriter writer = new FileWriter("output.txt");
        for(String str: info) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
}
