package web.test.HCITests.HCIContactusPage;

import Base.Common;
import Base.SingletonBrowser;
import HCIPo.HCIHomePage.HCIContactUsPO;
import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log
public class HCI_ContactUsTests extends Common {

    @Test(testName = "VerifyContactus")
    public void VerifyContactUs() throws Exception{
        try {
            SingletonBrowser.getInstance().getDriver().get(getProperty("url"));
            HCIContactUsPO.getInstance().enterFirstName(getProperty("firstname"));
            HCIContactUsPO.getInstance().enterLastName(getProperty("lastname"));
            HCIContactUsPO.getInstance().enterEmail(getProperty("email"));
            HCIContactUsPO.getInstance().enterPhoneNumber(getProperty("phoneNumber"));
            HCIContactUsPO.getInstance().enterDrpDwnImA(getProperty("imA"));
            HCIContactUsPO.getInstance().enterInsuranceProvider(getProperty("insuranceProvider"));
            HCIContactUsPO.getInstance().enterQuestionMessage(getProperty("questionMessage"));
            HCIContactUsPO.getInstance().enterHearAboutUs(getProperty("hearAboutUs"));
            HCIContactUsPO.getInstance().clickSubmit();
            //Assert.assertTrue(HCIContactUsPO.getInstance().isSuccessfullMsgDisplayed(), "Thank you for your message. It has been sent.");
            SingletonBrowser.getInstance().getDriver().close();

        }catch (Exception e){
            log.info("Failed : VerifyContactus ");
            throw e;
        }
    }
}
