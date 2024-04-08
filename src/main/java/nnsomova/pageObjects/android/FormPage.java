package nnsomova.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import nnsomova.utils.AndroidActions;

public class FormPage extends AndroidActions {
	
	AndroidDriver driver;

	public FormPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countryDropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitButton;
	
	//driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name")
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private WebElement errorMessage;
	
	public void setActivity()
	{
		// Screen HomePage
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));

	}
	
	public void setNameField(String text)
	{
		try {
			nameField.sendKeys(text);
			driver.hideKeyboard();
		} catch (StaleElementReferenceException e) {
	        System.out.println("Element became stale, refinding and retrying...");
	        setNameField(text);
	    }
	}
	
	public void setGender(String gender) 
	{
	    try {
	        if (gender.contains("female")) {
	            WebElement femaleOption = driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"));
	            femaleOption.click();
	        } else {
	            WebElement maleOption = driver.findElement(By.id("com.androidsample.generalstore:id/radioMale"));
	            maleOption.click();
	        }
	    } catch (StaleElementReferenceException e) {
	        System.out.println("Element became stale, refinding and retrying...");
	        setGender(gender);
	    }
	}
	
	public void setCountrySelection(String country) 
	{
		try {
			countryDropdown.click();
			scrollToText(country);
			driver.findElement(By.xpath("//android.widget.TextView[@text=\""+country+"\"]")).click();
		} catch (StaleElementReferenceException e) {
	        System.out.println("Element became stale, refinding and retrying...");
	        setCountrySelection(country);
	    }
	}
	
	public ProductCatalogue submitForm()
	{
		submitButton.click();
		return new ProductCatalogue(driver);
	}
	
	public void clickSubmit()
	{
		submitButton.click();
	}
	
	public String getToastAttribute(String attributeName)
	{
		String attrName = errorMessage.getAttribute(attributeName);
		return attrName;
	}
	
}
