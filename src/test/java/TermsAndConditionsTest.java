import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Terms and Conditions- Portio")
@Story("Terms and Conditions")
public class TermsAndConditionsTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Accepting terms and conditions")
    @DisplayName("Accepting terms and conditions")
    public void acceptTermsAndConditionsTest(){
        //Navigate to page
        landingPage.navigate();

        //Accept 'Terms and Conditions'
        landingPage.clickAcceptTermsButton();
        screenshot();

        //Assert if 'Terms and Conditions' is accepted
        Assertions.assertTrue(landingPage.isTermsAndConditionsAccepted());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Not accepting terms and conditions")
    @DisplayName("Not accepting terms and conditions")
    public void declineTermsAndConditionsTest(){
        //Navigate to page
        landingPage.navigate();

        //Close 'Terms and Conditions' popup
        landingPage.clickCloseTermsButton();
        screenshot();

        //Assert if 'Terms and Conditions' is declined
        Assertions.assertFalse(landingPage.isTermsAndConditionsAccepted());
    }
}
