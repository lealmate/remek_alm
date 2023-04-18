import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Epic("Login and logout - Portio")
public class LoginTest extends BaseTest{

    @Story("Login")
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with newly created user")
    @DisplayName("Login with new user")
    public void loginWithNewlyRegisteredUserTest(){
        //Register a new user
        signingPage.register("kosza", "lajos", "kosza.lajos@citromail.hu", "Just your average Lajos");

        //Navigate to 'Login' tab
        signingPage.clickLoginFormButton();

        //Fill in required fields
        signingPage.loginProcess("kosza", "lajos");

        //Click 'Login' button
        signingPage.clickLoginButton();

        //Assert if login is successful
        Assertions.assertTrue(landingPage.getLogoutButton());
    }

    @Story("Login")
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

    @Story("Logout")
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

    @Story("Login")
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

    @Story("Login")
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login function test")
    @DisplayName("Login function test")
    public void loginFunctionTest(){
        //Initiate 'login' function
        signingPage.login("lovasia", "kispal123");

        //Assert if login is successful
        Assertions.assertTrue(landingPage.getLogoutButton());
    }
}
