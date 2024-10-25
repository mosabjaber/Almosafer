import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://www.almosafer.com/en";
	Random rand = new Random();

	@BeforeTest
	public void mysetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(websiteURL);
		WebElement buttonCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		buttonCurrency.click();
	}

	@Test(priority = 1)
	public void checkDefaultLanguage() {
		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		String expectedLang = "en";
		org.testng.Assert.assertEquals(actualLang, expectedLang);
	}

	@Test(priority = 2)
	public void checkDefaultCurrency() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		String expectedCurrency = "SAR";

		org.testng.Assert.assertEquals(actualCurrency, expectedCurrency);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String expectedNumber = "+966554400000";

		org.testng.Assert.assertEquals(actualNumber, expectedNumber);

	}

	@Test(priority = 4)
	public void checkQitafLogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean actualResult = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();
		boolean expectedResult = true;

		org.testng.Assert.assertEquals(actualResult, expectedResult);
	}

	@Test(priority = 5)
	public void checkHotelTabIsNotSelected() {

		// boolean actualTab=
		// driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("class").contains("active");

		String actualTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		String expectedTab = "false";
		org.testng.Assert.assertEquals(actualTab, expectedTab);

	}

	@Test(priority = 6)
	public void checkDepatureDate() {
//		LocalDate time=LocalDate.now();
//		System.out.println(time);
//		int today = LocalDate.now().getDayOfMonth();
//		System.out.println(today);
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
		String actualDepature = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String expectedDepature = Integer.toString(tomorrow);
		org.testng.Assert.assertEquals(actualDepature, expectedDepature);

	}

	@Test(priority = 7)
	public void checkReturnDate() {

		int afterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
		String actualReturn = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String expectedReturn = Integer.toString(afterTomorrow);
		org.testng.Assert.assertEquals(actualReturn, expectedReturn);

	}

	@Test(priority = 8)
	public void randomlyChangeTheLanguage() throws InterruptedException {

		String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
		int randomIndex = rand.nextInt(myWebsites.length);
		driver.get(myWebsites[randomIndex]);
		
//		if (driver.getCurrentUrl().contains("ar"))
		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";

			org.testng.Assert.assertEquals(ActualLaguage, ExpectedLanguage);
		} else {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";

			org.testng.Assert.assertEquals(ActualLaguage, ExpectedLanguage);
		}
	}

}
