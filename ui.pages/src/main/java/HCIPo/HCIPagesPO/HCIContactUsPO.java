package HCIPo.HCIPagesPO;

import Base.BasePO;
import Base.Log;
import Base.SingletonBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class HCIContactUsPO extends BasePO {

    private static HCIContactUsPO hciContactUsPO;
    private SingletonBrowser singleton = SingletonBrowser.getInstance();

    private By successMessageSpinnerLocator = By.xpath("//span[@class='wpcf7-spinner']");
    private HCIContactUsPO() {
        super();
    }

    public static final HCIContactUsPO getInstance() {
        if (hciContactUsPO == null) {
            hciContactUsPO = new HCIContactUsPO();
        }
        return hciContactUsPO;
    }
    public void enterFirstName(String firstname) throws Exception {
        WebElement uiFirstname = singleton.getDriver().findElement(By.xpath("//input[@name='first-name']"));
        try {
            Log.info("## HCIContactUsPo | enterFirstName() ## " + this.getClass().getName());
            if (firstname.trim().length() != 0) {
                uiSendKeys(uiFirstname, firstname);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterFirstName()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);

    }

    public void enterLastName(String lastname) throws Exception {
        WebElement uiLastname = singleton.getDriver().findElement(By.xpath("//input[@name='last-name']"));
        try {
            Log.info("## HCIContactUsPo | enterLastName() ## " + this.getClass().getName());
            if (lastname.trim().length() != 0) {
                uiSendKeys(uiLastname, lastname);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterLastName()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);

    }

    public void enterEmail(String email) throws Exception {
        WebElement uiEmail = singleton.getDriver().findElement(By.xpath("//input[@name='email-address']"));
        try {
            Log.info("## HCIContactUsPo | enterEmail() ## " + this.getClass().getName());
            if (email.trim().length() != 0) {
                uiSendKeys(uiEmail, email);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterEmail()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);

    }

    public void enterPhoneNumber(String phoneNumber) throws Exception {
        WebElement uiPhoneNumber = singleton.getDriver().findElement(By.xpath("//input[@name='phone-number']"));
        try {
            Log.info("## HCIContactUsPo | enterPhoneNumber() ## " + this.getClass().getName());
            if (phoneNumber.trim().length() != 0) {
                uiSendKeys(uiPhoneNumber, phoneNumber);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterPhoneNumber()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);
    }

    public void enterRandomDrpDwnImA() throws Exception {
        WebElement uiDrpDwnImA = singleton.getDriver().findElement(By.xpath("//select[@name='i-am-a']"));
        Select dropdownImASelect = new Select(uiDrpDwnImA);
        List<WebElement> dropdownList = dropdownImASelect.getOptions();
        try {
            Log.info("## HCIContactUsPo | enterDrpDwnImA() ## " + this.getClass().getName());
            int dropdownValueCount = dropdownList.size();
            Random num = new Random();
            int index = num.nextInt(dropdownValueCount);
            dropdownImASelect.selectByIndex(index);

            System.out.println("random :" + index + "," + uiDrpDwnImA.getAttribute("value"));

        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterDrpDwnImA()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);
}
    public void enterInsuranceProvider(String insuranceProvider) throws Exception {
        WebElement uiInsuranceProvider = singleton.getDriver().findElement(By.xpath("//input[@name='insurance-provider']"));
        try {
            Log.info("## HCIContactUsPo | enterInsuranceProvider() ## " + this.getClass().getName());
            if (insuranceProvider.trim().length() != 0) {
                uiSendKeys(uiInsuranceProvider, insuranceProvider);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterInsuranceProvider()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);

    }

    public void enterQuestionMessage(String questionMessage) throws Exception {
        WebElement uiQuestionMessage = singleton.getDriver().findElement(By.xpath("//textarea[@name='message']"));
        try {
            Log.info("## HCIContactUsPo | enterQuestionMessage() ## " + this.getClass().getName());
            if (questionMessage.trim().length() != 0) {
                uiSendKeys(uiQuestionMessage, questionMessage);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterQuestionMessage()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);

    }

    public void enterHearAboutUs(String hearAboutUs) throws Exception {
        WebElement uiHearAboutUs = singleton.getDriver().findElement(By.xpath("//textarea[@name='how-hear-us']"));
        try {
            Log.info("## HCIContactUsPo | enterHearAboutUs() ## " + this.getClass().getName());
            if (hearAboutUs.trim().length() != 0) {
                uiSendKeys(uiHearAboutUs, hearAboutUs);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : enterHearAboutUs()" + e.getLocalizedMessage());
        }
        Sleep.run(1000);
    }

    public void clickSubmit() throws Exception {
        WebElement uiSubmit = singleton.getDriver().findElement(By.xpath("//input[@id='form-submit']"));
        try {
            Log.info("## HCIContactUsPo | clickSubmit() ## " + this.getClass().getName());
            uiClick(uiSubmit);
            //waitForLoad(15000);
            Thread.sleep(5000);
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : clickSubmit()" + e.getLocalizedMessage());
        }
    }

    public String successfullMsgDisplayed() throws Exception {
        //waitForLoad(15000);
        WebElement successMessage = singleton.getDriver().findElement(By.xpath("//div[contains(text(),'Thank you for your message. It has been sent.')]"));
        String text = successMessage.getText();
        System.out.println("sucess msg :" + text);
        return text;
    }
    public void waitTillSpinnerDisable() throws Exception {
        WebDriverWait wait = new WebDriverWait(singleton.getDriver(), 60);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(successMessageSpinnerLocator));
    }
}
