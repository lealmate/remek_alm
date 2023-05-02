package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogPage extends BasePage {

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    //Elements to be located
    private final By ARTICLES = By.xpath("//*[@class = 'blog-page__item']");
    private final By NEXT_PAGE_BUTTON = By.xpath("//*[@rel = 'next']");

    //Functions
    public void clickNextPageButton() {
        driver.findElement(NEXT_PAGE_BUTTON).click();
    }

    public boolean isNextPageButtonDisplayed() {
        try {
            return driver.findElement(NEXT_PAGE_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getArticleTitles() {
        List<WebElement> articles = driver.findElements(ARTICLES);
        List<String> titles = new ArrayList<>();
        while (isNextPageButtonDisplayed()) {
            gatheringTitlesOnOnePage(articles, titles);
            clickNextPageButton();
        }
        List<WebElement> articlesOnLastPage = driver.findElements(ARTICLES);
        gatheringTitlesOnOnePage(articlesOnLastPage, titles);
        return titles;
    }

    public void gatheringTitlesOnOnePage(List<WebElement> articles, List<String> titles) {
        for (WebElement article : articles) {
            String title = article.findElement(By.xpath(".//h5/a")).getText();
            titles.add(title);
        }
    }

    public List<String> getTitleFromFile() {
        List<String> titles = new ArrayList<>();
        try {
            File file = new File("src/main/resources/titles.txt");
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                titles.add(scn.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return titles;
    }
}
