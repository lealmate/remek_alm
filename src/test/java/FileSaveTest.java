import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Epic("Downloads - Portio")
@Story("Save data")
public class FileSaveTest extends BaseTest{


    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Saving an image")
    @DisplayName("Save image")
    public void saveImageTest(){
        //Login with existing user
        signingPage.login("lovasia", "kispal123");

        //Save image
        landingPage.saveImage();

        //Assert if image is saved
        Assertions.assertTrue(landingPage.lookForSuccessfulDownload("img.png"));

        //Delete downloaded image
        landingPage.deleteFile("img.png");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Saving lines of text from site to file")
    @DisplayName("Save text")
    public void saveTextTest() throws IOException {
        //Login with existing user
        signingPage.login("lovasia", "kispal123");

        //Navigate to 'Contact' page
        landingPage.clickHireMeButton();

        //Save text
        contactPage.saveContactInfoToFile();

        //Assert if text is saved
        Assertions.assertTrue(landingPage.lookForSuccessfulDownload("output.txt"));

        //Delete downloaded text
        landingPage.deleteFile("output.txt");
    }
}
