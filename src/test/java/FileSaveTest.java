import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Downloads - Portio")
@Story("Save data")
public class FileSaveTest extends BaseTest{


    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Saving an image")
    @DisplayName("Save image")
    public void saveImageTest(){
        //Login with existing user
        loginTest.loginWithExistingUserTest();

        //Save image
        landingPage.saveImage();

        //Assert if image is saved
        Assertions.assertTrue(landingPage.lookForSuccessfulDownload("img.png"));

        //Delete downloaded image
        landingPage.deleteFile("img.png");
    }

}
