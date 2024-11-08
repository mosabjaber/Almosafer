import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Parameters {
	
	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://www.almosafer.com/en";
	Random rand = new Random();
	//
	String expectedLang = "en";
	String expectedCurrency = "SAR";
	String expectedNumber = "+966554400000";
	boolean expectedLogo = true;
	String expectedTab = "false";
	
	int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
	String expectedDepature = String.format("%02d", tomorrow);
	
	int afterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
	String expectedReturn = String.format("%02d", afterTomorrow);
	
	String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
	String[] randomEnCountry = { "Dubai", "Jeddah", "Riyadh" };
	String[] randomArCountry = { "دبي", "جدة" };
	int indexRandomArCountry = rand.nextInt(randomArCountry.length);
	int indexRandomEnCountry = rand.nextInt(randomEnCountry.length);
	int randomIndex = rand.nextInt(myWebsites.length);
	
	boolean expectedLoaded = true;
//
	
	public void mySetupWebsite() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(websiteURL);
		WebElement buttonCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		buttonCurrency.click();
	}
	
	public void randomlyEnterTheWebsite() {
		driver.get(myWebsites[randomIndex]);
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
	}
	public void checkLanguageToEnterCity(WebElement HotelSearchBar) throws InterruptedException {
//		if (driver.getCurrentUrl().contains("ar"))
		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
			HotelSearchBar.sendKeys(randomArCountry[indexRandomArCountry]);

		} else {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
			HotelSearchBar.sendKeys(randomEnCountry[indexRandomEnCountry]);

		}
		Thread.sleep(2000);
		
	}
	
	public void enterNumberOfVistorsAfterClickOnTheFirstCity() throws InterruptedException {
		WebElement CitiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		CitiesList.findElements(By.tagName("li")).get(1).click();

		WebElement selectNumOfRoom = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select selector = new Select(selectNumOfRoom);
		int randomRoom = rand.nextInt(2);
		selector.selectByIndex(randomRoom);

		WebElement SearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchButton.click();

		Thread.sleep(20000);
		
	}
	
	public void sortOptionChecker () {
		WebElement Container = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[5]/div"));

		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			// Integer.parseInt() ====> convert to integer
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("SAR ", ""));
//			Or   int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("[^0-9]", ""));

			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("SAR ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("ر.س. ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);

		}
	}
	
	public void ScreenShot() throws IOException, InterruptedException {
		Thread.sleep(2000);

		Date myDate = new Date();
//convert to string because use replace
		String fileName = myDate.toString().replace(":", "-");
//		System.out.println(fileName);
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShot/" + fileName + ".png");
		FileUtils.copyFile(SrcFile, destFile);
	}
	
}
