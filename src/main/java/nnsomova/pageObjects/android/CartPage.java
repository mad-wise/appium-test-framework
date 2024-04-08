package nnsomova.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import nnsomova.utils.AndroidActions;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement pageTitle;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	private WebElement alertTitle;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productsList;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement acceptBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedBtn;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	public List<WebElement> getListProducts()
	{
		return productsList;
	}
	
	public double getProductsSum()
	{
		double totalSum = 0.0;
		for (WebElement product : productsList) {
			String productPrice = product.getText();
			totalSum += getFormattedAmount(productPrice);
		}
		return totalSum;
	}
	
	public Double getTotalAmountDisplayed()
	{
		Double price = getFormattedAmount(totalAmount.getText());
		return price;
	}
	
	public void acceptTermsAndConditions()
	{
		longPressAction(termsBtn);
		acceptBtn.click();
	}
	
	public void submitOrder()
	{
		proceedBtn.click();
	}
	
	public void setCheckBox()
	{
		checkBox.click();
	}
	
	public void waitCartTitle() {
		waitForElementToAppear(pageTitle, driver,  "Cart");
	}
	
	public String getAlertTitle()
	{
		String conditionsTitle = alertTitle.getText();
		return conditionsTitle;
	}

}
