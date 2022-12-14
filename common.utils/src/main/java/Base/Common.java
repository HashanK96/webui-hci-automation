package Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Common {
    SingletonBrowser singletonClass = SingletonBrowser.getInstance();
    private String value = null;
    private String cssSizes42pxH1 = "//h1[@class='elementor-heading-title elementor-size-default']";
    private String cssSizes45pxH2 = "//h2[@class='elementor-heading-title elementor-size-default']";
    private String cssSizes35pxH2 = "//h2[@class='elementor-heading-title elementor-size-default']";
    private String cssSizes20pxPG = "//div[@class='elementor-text-editor elementor-clearfix']";
    private String cssSizes20pxLinkButton = "//span[@class='elementor-icon-list-text']";
    private String cssSizes14pxButton = "//span[@class='elementor-button-text']";
    private String cssFontSizes14pxNavBar = "//span[@class='ubermenu-target-title ubermenu-target-text']";
    private String cssSizes30pxH2 = "//h2[@class='elementor-heading-title elementor-size-default']";

    //Common method save the screenshot in d drive with name "screenshot.png"
    public void getscreenshot(String nameStr) {
        File scrFile = ((TakesScreenshot) singletonClass.getDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = nameStr + "_" + getRandomCurrentDateTime() + "screenshot.png";
        try {
            FileUtils.copyFile(scrFile, new File("target/ScreenShot/" + fileName));
        } catch (IOException e) {
            Log.error(" IOException: Get Screenshot()");
        }
        Log.info("ScreenShot :" + fileName);
    }

    public void loggerErr(String step, Exception e) {
        Log.error(step + "\n" + e);
        getscreenshot(step);
    }

    //Common method to get current date which is use to save screen shot
    public String getRandomCurrentDateTime() {
        SimpleDateFormat formDate = new SimpleDateFormat("ddHHmmss");
        String strDate = formDate.format(new Date());
        return strDate;
    }

    //Common method for get the properties from the property file as text
    public String getProperty(String text)
    {
        //value = null;
        try {
            System.err.println("Text................ "+ text);
            value = SingletonBrowser.getInstance().getProp().getProperty(text);
            System.out.println("Get Property As "+ value);

        } catch (Exception e) {
            System.out.println("Unable to  get Text :"+e.toString());
        }
        return value;
    }

    //This method is a common method for set the properties
    public String setProperty(String property,String text)
    {
        value = null;
        try {
            SingletonBrowser.getInstance().getProp().setProperty(property, text);
            System.out.println("Set Property As "+value);
        } catch (Exception e) {
            System.out.println("Unable to  get Text :"+e.toString());
        }
        return value;
    }

    //Common methods for wait
    public void sleepSetup() {
        int sleepVar = 0;

    }

    public void sleep(int sleepVar, String xpath) throws Exception{

        int timeInSeconds	=	(sleepVar*Integer.parseInt(SingletonBrowser.getInstance().getProp("SleepSetup")))/1000;
        WebDriverWait wait	=	new WebDriverWait(SingletonBrowser.getInstance().getDriver(), timeInSeconds);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

    }

    public void sleep(int sleepVar) throws Exception{
        int i	=	1;
        while (i <= 3) {

            try {
                Boolean tt =SingletonBrowser.getInstance().getDriver().findElement(By.xpath("//*[@id='myNavbar']/ul[2]/li[1]/a")).isDisplayed();
                if (tt==true) {
                    break;
                } else {
                    Thread.sleep(sleepVar*Integer.parseInt(SingletonBrowser.getInstance().getProp("SleepSetup")));
                }
                i++;
            } catch (NoSuchElementException e) {
                break;
            }
        }
        System.out.println("i = "+i);
        if(i == 4){
            System.err.println("Loading Element Not Found.");
        }

    }
    //Replace text value inside the path
    public String replaceValues(String one, String elementName) {
        return elementName.replaceAll("\\{1}", one);
    }

    public String replaceValues(String one,String two,String elementName) {
        return elementName.replaceAll("\\{1}", one).replaceAll("\\{2}", two);
    }

    public String replaceValues(String one,String two,String three,String elementName) {
        return elementName.replaceAll("\\{1}", one).replaceAll("\\{2}", two).replaceAll("\\{3}", three);
    }

    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static void navigateToNewTab(int tabIndex) throws Exception {
        ArrayList<String> tabs2 = new ArrayList<String>(SingletonBrowser.getInstance().getDriver().getWindowHandles());
        SingletonBrowser.getInstance().getDriver().switchTo().window(tabs2.get(tabIndex));
    }

    public static void pageRefresh() throws Exception {
        SingletonBrowser.getInstance().getDriver().navigate().refresh();
    }

    public static void pageScroll(String x) throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) SingletonBrowser.getInstance().getDriver();
            js.executeScript(x);
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : VerifyHomePagePlayVideo1()" + e.getLocalizedMessage());
        }
    }

    public static void pageNavigation(String PageUrl) throws Exception {
        SingletonBrowser.getInstance().getDriver().navigate().to(PageUrl);
    }

    public static void implicitlyWait(int second) throws Exception {
        try {
            SingletonBrowser.getInstance().getDriver().manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : implicitlyWait(int second)" + e.getLocalizedMessage());
        }
    }

    public String getCssValueFontSize_42pxH1() throws Exception {
        try {
            String fontSizes42pxH1 = singletonClass.getDriver().findElement(By.xpath(cssSizes42pxH1)).getCssValue("font-size");
            System.out.println("42pxH1 font-size :" + fontSizes42pxH1);
            return fontSizes42pxH1;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_42pxH1()" + e.getLocalizedMessage());
        }
    }

    public String getCssValueFontSizes_35pxH2() throws Exception {
        try {
            String fontSizes35pxH2 = singletonClass.getDriver().findElement(By.xpath(cssSizes35pxH2)).getCssValue("font-size");
            System.out.println("35pxH2 font-size :" + fontSizes35pxH2);
            return fontSizes35pxH2;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSizes_35pxH2()" + e.getLocalizedMessage());
        }
    }

    public String getCssValueFontSize_20pxPG() throws Exception {
        try {
            String fontSizes20pxPG = singletonClass.getDriver().findElement(By.xpath(cssSizes20pxPG)).getCssValue("font-size");
            System.out.println("20pxPG font-size :" + fontSizes20pxPG);
            return fontSizes20pxPG;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_20pxPG()" + e.getLocalizedMessage());
        }
    }

    public String getCssValueFontSize_20pxLinkButton() throws Exception {
        try {
            String fontSizes20pxLinkButton = singletonClass.getDriver().findElement(By.xpath(cssSizes20pxLinkButton)).getCssValue("font-size");
            System.out.println("20pxLinkButton font-size :" + fontSizes20pxLinkButton);
            return fontSizes20pxLinkButton;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_20pxLinkButton()" + e.getLocalizedMessage());
        }
    }

    public String getCssValueFontSize_14pxButton() throws Exception {
        try {
            String fontSizes14pxButton = singletonClass.getDriver().findElement(By.xpath(cssSizes14pxButton)).getCssValue("font-size");
            System.out.println("14pxButton font-size :" + fontSizes14pxButton);
            return fontSizes14pxButton;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_14pxButton()" + e.getLocalizedMessage());
        }
    }

    public String getCssValueFontSize_14pxNavBar() throws Exception {
        try {
            String fontSizes14pxNavBar = singletonClass.getDriver().findElement(By.xpath(cssFontSizes14pxNavBar)).getCssValue("font-size");
            System.out.println("14pxNavBar font-size :" + fontSizes14pxNavBar);
            return fontSizes14pxNavBar;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_14pxNavBar()" + e.getLocalizedMessage());
        }
    }
    public String getCssValueFontSize_45pxH2() throws Exception {
        try {
            String fontSizes45pxH2 = singletonClass.getDriver().findElement(By.xpath(cssSizes45pxH2)).getCssValue("font-size");
            System.out.println("45pxH2 font-size :" + fontSizes45pxH2);
            return fontSizes45pxH2;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_45pxH2()" + e.getLocalizedMessage());
        }
    }
    public String getCssValueFontSize_30pxH2() throws Exception {
        try {
            String fontSizes30pxH2 = singletonClass.getDriver().findElement(By.xpath(cssSizes30pxH2)).getCssValue("font-size");
            System.out.println("30pxH2 font-size :" + fontSizes30pxH2);
            return fontSizes30pxH2;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : getCssValueFontSize_30pxH2()" + e.getLocalizedMessage());
        }
    }

    public void backToTop() throws Exception {
        try {
            WebElement backToTop = singletonClass.getDriver().findElement(By.xpath("//a[@title ='Back to top']"));
            backToTop.click();
            Thread.sleep(5000);
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : backToTop()" + e.getLocalizedMessage());
        }
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement webElement, int timeOutInSecond) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return element;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : waitTillElementToBeClickable()" + e.getLocalizedMessage());
        }
    }
    public static WebElement waitForVisibilityOf(WebDriver driver, WebElement webElement, int timeOutInSecond) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
            WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
            return element;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : waitTillVisibilityOfElementLocated()" + e.getLocalizedMessage());
        }
    }

    public static WebElement waitTillVisibilityOfElementLocated(WebDriver driver, By locator, int timeOutInSecond) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element;
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : waitTillVisibilityOfElementLocated()" + e.getLocalizedMessage());
        }
    }
    public static void pageScrollingMiddleByWebElement(By locator) throws Exception {
        try {
            JavascriptExecutor je = (JavascriptExecutor) SingletonBrowser.getInstance().getDriver();
            WebElement element = SingletonBrowser.getInstance().getDriver().findElement(locator);
            je.executeScript("arguments[0].scrollIntoView(true);",element);
        } catch (NoSuchElementException e) {
            throw new Exception("Failed : VerifyHomePagePlayVideo1()" + e.getLocalizedMessage());
        }
    }


}
