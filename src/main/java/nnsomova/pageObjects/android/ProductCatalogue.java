package nnsomova.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import nnsomova.utils.AndroidActions;

public class ProductCatalogue extends AndroidActions {

	AndroidDriver driver;

	public ProductCatalogue(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"ADD TO CART\"]")
	public List<WebElement> addToCartBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartBtn;
	
	public void addItemToCartByIndex(int index)
	{
		addToCartBtn.get(index).click();
	}
	
	public CartPage clickCartButton() throws InterruptedException
	{
		cartBtn.click();
		Thread.sleep(2000);
		return new CartPage(driver);
	}
}
