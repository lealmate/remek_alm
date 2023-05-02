import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Epic("Blog - Portio")
@Story("Articles")
public class BlogTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Walk through of multi-page blog, comparing article names from file")
    @DisplayName("Article names")
    public void blogArticleTest(){
        //Login with existing user
        signingPage.login("lovasia", "kispal123");

        //Navigate to 'Blog' tab when visible
        screenshot();
        wait.until(ExpectedConditions.elementToBeClickable(landingPage.getBlogButton()));
        landingPage.clickBlogButton();

        //Click 'See all post' button
        wait.until(ExpectedConditions.elementToBeClickable(landingPage.getSeeAllPostButton()));
        landingPage.clickSeeAllPostButton();

        //Get all article titles
        List<String> actualResult = blogPage.getArticleTitles();
        List<String> expectedResult = blogPage.getTitleFromFile();
        screenshot();

        //Assert if article titles are correct
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
