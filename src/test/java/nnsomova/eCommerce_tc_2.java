package nnsomova;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nnsomova.pageObjects.android.CartPage;
import nnsomova.pageObjects.android.ProductCatalogue;
import nnsomova.testUtils.AndroidBaseTest;

public class eCommerce_tc_2 extends AndroidBaseTest {

	@Test(dataProvider="getData", groups = {"Smoke"})
	public void FillForm(HashMap<String, String> input) throws InterruptedException 
	{
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));	
		ProductCatalogue productCatalogue = formPage.submitForm();
		
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.clickCartButton();	
		
		cartPage.waitCartTitle();
		double totalSum = cartPage.getProductsSum();
		double totalSmountDisplayed = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSmountDisplayed, totalSum);
		cartPage.acceptTermsAndConditions();
		cartPage.setCheckBox();
		cartPage.submitOrder();		
	}

	@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
		formPage.setActivity();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		 List<HashMap<String, String>> data = getJSONData("eCommerce.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
}
