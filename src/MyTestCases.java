import static org.testng.Assert.assertEquals;

import java.time.Duration;

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
String actualNumber=driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
String expectedNumber="+966554400000";

org.testng.Assert.assertEquals(actualNumber, expectedNumber);

	}
	
	@Test(priority = 4)
	public void checkQitafLogo() {
		boolean ActualResult = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean ExpectedResult = true;

		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);
	}
}
