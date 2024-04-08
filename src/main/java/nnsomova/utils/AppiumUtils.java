package nnsomova.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	
	public Double getFormattedAmount(String amount) {
		Double formattedAmount = Double.parseDouble(amount.replace("$", "").trim());
		return formattedAmount;
	}
	
	public List<HashMap<String, String>> getJSONData(String jsonFilePath) throws IOException {
	    // Convert JSON file content to JSON string
	    String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\nnsomova\\testData\\" + jsonFilePath), StandardCharsets.UTF_8);
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
	    return data;
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) 
	{
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\vikko\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js")).withIPAddress(ipAddress)
                .usingPort(port)
                .build();
		service.start();
        return service;
	}
	
	public void stopAppiumServer()
	{
		service.stop();
	}
	
	public void waitForElementToAppear(WebElement element, AppiumDriver driver, String text)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(element, "text", text));
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver)
	{
	    File source = driver.getScreenshotAs(OutputType.FILE);
	    String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	    try {
	        FileUtils.copyFile(source, new File(destinationFile));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return destinationFile;
	}


}
