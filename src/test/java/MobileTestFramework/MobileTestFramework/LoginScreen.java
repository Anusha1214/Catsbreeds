package MobileTestFramework.MobileTestFramework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginScreen {
	

	public LoginScreen(AppiumDriver driver)
	{
		//Initialize the pageobjects and AppiumFieldDecorator will allow the objects to be used on any OS
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Login']")
	public WebElement headerObj;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='1']")
	public WebElement usernameObj;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='2']")
	public WebElement passwordObj;
	
	@AndroidFindBy(xpath="//android.widget.Button[@index='1']")
	public WebElement btnLoginObj;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cats Breeds']")
	public WebElement catsBreedsObj;
	
	
	
}
