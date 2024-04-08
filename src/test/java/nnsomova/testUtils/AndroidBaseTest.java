package nnsomova.testUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import nnsomova.pageObjects.android.FormPage;
import nnsomova.utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	
	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws MalformedURLException
	{
		Properties props = new Properties();
		try {	   
		    FileInputStream propsFile = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\nnsomova\\resources\\data.properties");
		    props.load(propsFile);
		    propsFile.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		service = startAppiumServer(props.getProperty("ipAddress"), Integer.parseInt(props.getProperty("port")));
        
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(props.getProperty("AndroidDeviceName")); //emulator
        options.setApp(props.getProperty("appPath"));
        options.setChromedriverExecutable(props.getProperty("chromeDriverPath")); // Path to Chromedriver
        
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() 
	{
		driver.quit();
//		service.stop();
	}
}
