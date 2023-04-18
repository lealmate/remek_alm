import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Registration - Portio")
@Story("Registration")
public class RegisterTest extends BaseTest{

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
    @Description("Registration then login function test")
    @DisplayName("RegistrationThenLogin")
    public void registrationThenLoginTest(){
        //Register and login with new user
        signingPage.registerThenLogin("kosza", "lajos", "kosza.lajos@citromail.hu", "Just your average Lajos");

        //Assert if login is successful
        Assertions.assertTrue(landingPage.getLogoutButton());
    }
}
