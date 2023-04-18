import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PortioTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Registration of new user")
    @DisplayName("Registration")
    public void registerTest(){
        //Navigate to page
        landingPage.navigate();

        //Accept 'Terms and Conditions'
        landingPage.clickAcceptTermsButton();

        //Select 'Register' tab
        signingPage.clickRegisterFormButton();

        //Fill in required fields
        signingPage.registrationProcess("kosza", "lajos", "kosza.lajos@citromail.hu", "Just your average Lajos");

        //Click 'Register' button
        signingPage.clickRegisterButton();

        //Assert if registration is successful
        Assertions.assertTrue(signingPage.lookForSuccessfulRegistration());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with newly created user")
    @DisplayName("Login with new user")
    public void loginWithNewlyRegisteredUserTest(){
        //Register a new user
        registerTest();

        //Navigate to 'Login' tab
        signingPage.clickLoginFormButton();

        //Fill in required fields
        signingPage.loginProcess("kosza", "lajos");

        //Click 'Login' button
        signingPage.clickLoginButton();

        //Assert if login is successful
        Assertions.assertTrue(landingPage.getLogoutButton());
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login with existing user")
    @DisplayName("Login with existing user")
    public void loginWithExistingUserTest(){
        //Navigate to page
        landingPage.navigate();

        //Accept 'Terms and Conditions'
        landingPage.clickAcceptTermsButton();

        //Fill in required fields
        signingPage.loginProcess("lovasia", "kispal123");

        //Click 'Login' button
        signingPage.clickLoginButton();

        //Assert if login is successful
        Assertions.assertTrue(landingPage.getLogoutButton());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Logout from account")
    @DisplayName("Logout")
    public void logoutTest(){
        //Register and login with new user
        loginWithNewlyRegisteredUserTest();

        //Click 'Logout' button
        landingPage.clickLogoutButton();

        //Assert if logout is successful
        Assertions.assertTrue(signingPage.lookForSuccessfulLogout());
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Editing profile information")
    @DisplayName("Edit profile")
    public void editProfileTest(){
        //Register and login with new user
        loginWithNewlyRegisteredUserTest();

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
        loginWithNewlyRegisteredUserTest();

        //Click 'Delete Profile' button
        landingPage.clickProfileButton();

        //Click 'Delete' button
        profilePage.clickDeleteAccountButton();

        //Click 'Confirm Delete' button
        profilePage.clickConfirmDeleteAccountButton();

        //Assert if profile is deleted
        Assertions.assertTrue(signingPage.lookForSuccessfulLogout());
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Walk through of multi-page blog, comparing article names from file")
    @DisplayName("Article names")
    public void blogArticleTest(){
        //Login with existing user
        loginWithExistingUserTest();

        //Navigate to 'Blog' tab
        landingPage.clickBlogButton();

        //Click 'See all post' button
        landingPage.clickSeeAllPostButton();

        //Get all article titles
        List<String> actualResult = blogPage.getArticleTitles();
        List<String> expectedResult = blogPage.getTitleFromFile();
        screenshot();

        //Assert if article titles are correct
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Saving an image")
    @DisplayName("Save image")
    public void saveImageTest(){
        //Login with existing user
        loginWithExistingUserTest();

        //Save image
        landingPage.saveImage();

        //Assert if image is saved
        Assertions.assertTrue(landingPage.lookForSuccessfulSave());

        //Delete downloaded image
        landingPage.deleteImage();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with multiple users repeatedly from file")
    @DisplayName("Login with multiple users")
    public void loginWithMultipleUsersFromFileTest(){
        //Navigate to page
        landingPage.navigate();

        //Accept 'Terms and Conditions'
        landingPage.clickAcceptTermsButton();

        //Login with multiple users
        for(String key:CREDENTIALS.keySet()){
            signingPage.loginProcess(key, CREDENTIALS.get(key));
            signingPage.clickLoginButton();
            Assertions.assertTrue(landingPage.lookForSuccessfulLogin());
            screenshot();
            landingPage.navigate();
        }
    }
}
