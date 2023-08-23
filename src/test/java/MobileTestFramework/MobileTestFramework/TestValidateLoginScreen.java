package MobileTestFramework.MobileTestFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class TestValidateLoginScreen extends BaseClass{
	
	@BeforeTest
	//To stop appium server for successfull execution
	public void killAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);	
	}
	
	@Test( enabled=true )
	public void homeScreenValidation() throws IOException {
		
		service = startAppiumServer();
		
		//Fetch the appName from GlobalProperties file
		AndroidDriver<AndroidElement> driver = Capabilities("MobileApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		//Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\MobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
		
		
		LoginScreen ls = new LoginScreen(driver);
	
		
		
		Assert.assertEquals(true, ls.headerObj.isDisplayed(),"Header Login object not found");
		Assert.assertEquals(true, ls.usernameObj.isDisplayed(),"Username object not found");
		Assert.assertEquals(true, ls.passwordObj.isDisplayed(),"Password object not found");
		Assert.assertEquals(true,  ls.btnLoginObj.isDisplayed(),"Login object not found");
		
		ls.usernameObj.sendKeys((String) prty.get("Username"));
		ls.passwordObj.sendKeys((String) prty.get("Password"));
	
		
		//Validate that click on Login button navigates to Cats List screen.
		ls.btnLoginObj.click();
		Assert.assertEquals(true,  ls.catsBreedsObj.isDisplayed(),"Cats Breeds Screen not found");
		
		//Stop Appium Server
		service.stop();
		
	}
}
