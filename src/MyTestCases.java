import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters{

	@BeforeTest
	public void mysetup() {
		mySetupWebsite();
	}

	@Test(priority = 1)
	public void checkDefaultLanguage() throws IOException, InterruptedException  {
		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(actualLang, expectedLang);
		ScreenShot();
		
	}

	@Test(priority = 2)
	public void checkDefaultCurrency() throws IOException, InterruptedException {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		Assert.assertEquals(actualCurrency, expectedCurrency);
		ScreenShot();

	}

	@Test(priority = 3)
	public void CheckContactNumber() throws IOException, InterruptedException {
		String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();

		Assert.assertEquals(actualNumber, expectedNumber);
		ScreenShot();

	}

	@Test(priority = 4)
	public void checkQitafLogo() throws IOException, InterruptedException {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean actualLogo = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();

		Assert.assertEquals(actualLogo, expectedLogo);
		ScreenShot();

	}

	@Test(priority = 5)
	public void checkHotelTabIsNotSelected() throws IOException, InterruptedException {

		// boolean actualTab=
		// driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("class").contains("active");

		String actualTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		
		Assert.assertEquals(actualTab, expectedTab);
		ScreenShot();
	}

	@Test(priority = 6)
	public void checkDepatureDate() throws IOException, InterruptedException {
//		LocalDate time=LocalDate.now();
//		System.out.println(time);
//		int today = LocalDate.now().getDayOfMonth();
//		System.out.println(today);
		String actualDepature = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
//		this code is invalid in 0 to 9 
//		String expectedDepature = Integer.toString(tomorrow);
		
		Assert.assertEquals(actualDepature, expectedDepature);
		ScreenShot();
	}

	@Test(priority = 7)
	public void checkReturnDate() throws IOException, InterruptedException {

		String actualReturn = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
	
		Assert.assertEquals(actualReturn, expectedReturn);
		ScreenShot();

	}

	@Test(priority = 8,enabled=true)
	public void randomlyChangeTheLanguage() throws InterruptedException, IOException {
//test number 3,4,5,6
		
		randomlyEnterTheWebsite();
		
		WebElement HotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		
		checkLanguageToEnterCity(HotelSearchBar);

		enterNumberOfVistorsAfterClickOnTheFirstCity();
		ScreenShot();

	}

	@Test(priority = 9)
	public void checkThatThePageIsFullyLoaded() throws IOException, InterruptedException {
		WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		boolean actualLoaded = SearchResult.getText().contains("found") || SearchResult.getText().contains("مكان");

		Assert.assertEquals(actualLoaded, expectedLoaded);
		ScreenShot();
	}

	@Test(priority = 10)
	public void CheckTheSortOption() throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement LowestPriceButton = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		LowestPriceButton.click();
		Thread.sleep(2000);

		sortOptionChecker ();
		ScreenShot();

	}

}
