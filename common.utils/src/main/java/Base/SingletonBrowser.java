package Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class SingletonBrowser {
    private  static WebDriver driver;
    private  static String browser;
    private  static Properties prop = new Properties();
    private  static SingletonBrowser obj = null;
    private  static String env;

    private SingletonBrowser() {}

    public   static final SingletonBrowser getInstance() {
        if (obj == null) {
            obj = new SingletonBrowser();
        }
        return obj;
    }

    public WebDriver setDriver() {
        try{
            //DOMConfigurator.configure("log4j.xml");
            setEnv();
            loadProp();
            setBrowser();
            if (driver == null) {
                //Log.info("Driver is null");

                System.out.println("Selected Browser is "+browser);
                if (browser.equals("Firefox")) {
                    driver = new FirefoxDriver();
                } else if (browser.equals("Chrome")) {

                    //New method to create Chrome driver to avoid browser pop up
                    ChromeOptions chOption = new ChromeOptions();
                    chOption.addArguments("--disable-extensions");
                    chOption.addArguments("test-type");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    chOption.setExperimentalOption("prefs", prefs);
                    System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
                    driver = new ChromeDriver(chOption);
                    System.out.println("diver open");

                    /*
                    ChromeOptions chOption = new ChromeOptions();
                    chOption.addArguments("--disable-extensions");
                    chOption.addArguments("test-type");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    chOption.setExperimentalOption("prefs", prefs);
                    chOption.addArguments("--headless");
                    System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
                    driver = new ChromeDriver(chOption);*/

                }
                else if (browser.equals("IE")) {
                    System.setProperty("webdriver.ie.driver",
                            "src/test/resources/Driver/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                } else if (browser.equals("IE11")) {
                    System.setProperty("webdriver.ie.driver",
                            "src/test/resources/Driver/IEDriverServer11.exe");
                    driver = new InternetExplorerDriver();
                }
            } else {
            }
            driver.manage().window().maximize();

        }catch(Exception e)
        {
            System.out.println("Unable to Execute SetDriver"+e.toString());
        }
        return driver;
    }

    public WebDriver getDriver() {
        return setDriver();
    }

    public static void setDriver(WebDriver driver) {
        SingletonBrowser.driver = driver;
    }

    public void loadProp() {

        try {
            if (getEnv().equals("Prod")) {
                getProp().load(new FileInputStream("src/test/resources/TestData/data_HCI.properties"));
                //log("Property file Loaded.");
            }

        } catch (FileNotFoundException e) {
            //Log.error("Error File not found");
            e.printStackTrace();
        } catch (IOException e) {
            // Log.error("Error IO exception");
            e.printStackTrace();
        }
    }
    public void setProp(String key,String value) {
        prop.setProperty(key, value);
    }

    public Properties getProp() {
        return prop;
    }

    public String getProp(String parameter) {
        return prop.getProperty(parameter);
    }

    public Properties setProp(){
        return prop;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv() {
        env=System.getProperty("env");
        //env = "Prod";
    }

    public void setBrowser() {
        browser=System.getProperty("browser");
        //browser="Chrome";
    }

    public String getBrowser() {
        return browser;
    }

    public void getscreenshot() {
        File scrFile = ((TakesScreenshot) getDriver())
                .getScreenshotAs(OutputType.FILE);
        String fileName = getRandomCurrentDateTime()
                + "screenshot.png";
        try {
            FileUtils.copyFile(scrFile, new File("target/ScreenShot/"
                    + fileName));
        } catch (IOException e) {
            //Log.error(" IOException: Get Screenshot()");
        }

    }

    public String getRandomCurrentDateTime() {
        SimpleDateFormat formDate = new SimpleDateFormat("ddHHmmss");
        String strDate = formDate.format(new Date());
        return strDate;

    }

}