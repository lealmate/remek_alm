import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Profile settings - Portio")
@Story("Profile editing and deleting")
public class ProfileTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Editing profile information")
    @DisplayName("Edit profile")
    public void editProfileTest(){
        //Register and login with new user
        loginTest.loginWithNewlyRegisteredUserTest();

        //Click 'Edit Profile' button
        landingPage.clickProfileButton();

        //Fill in required fields
        profilePage.editProfile("jozsi", "Below average student", "11111111111");

        //Click 'Save' button
        profilePage.clickSaveProfileButton();

        //Assert if profile is edited
        Assertions.assertTrue(profilePage.lookForSuccessfulEdit());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Deleting newly created profile")
    @DisplayName("Delete profile")
    public void deleteProfileTest(){
        //Register and login with new user
        loginTest.loginWithNewlyRegisteredUserTest();

        //Click 'Edit Profile' button
        landingPage.clickProfileButton();

        //Click 'Delete' button
        profilePage.clickDeleteAccountButton();

        //Click 'Confirm Delete' button
        profilePage.clickConfirmDeleteAccountButton();

        //Assert if profile is deleted
        Assertions.assertTrue(signingPage.lookForSuccessfulLogout());
    }
}
