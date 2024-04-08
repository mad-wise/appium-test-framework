package nnsomova;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nnsomova.testUtils.AndroidBaseTest;

public class eCommerce_tc_1 extends AndroidBaseTest {
	
	@Test(dataProvider="getData")
	public void FillForm(HashMap<String, String> input) throws InterruptedException
	{
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));	
		formPage.clickSubmit();
	
		String toastMessage = formPage.getToastAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");
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
