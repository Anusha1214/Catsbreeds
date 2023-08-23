package MobileTestFramework.MobileTestFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class BaseClass {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement>  driver;
	
	
	//Code to start Appium Server
	public AppiumDriverLocalService startAppiumServer() {
		
		boolean flag =	checkIfServerIsRunnning(4723);
		if(!flag)
		{
			
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
			return service;

	}
	
	public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException {
		
		//Load GlobalProperty File
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\MobileTestFramework\\MobileTestFramework\\Global.properties");
		Properties prty = new Properties();
		prty.load(fis);
		
		//Fetch the application location
		File f = new File("src");
		File fs = new File(f, (String) prty.get(appName));

		//Initiate the device and the application under test
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.FULL_RESET, "False");
	    dc.setCapability(MobileCapabilityType.NO_RESET, "True");
	    //dc.setCapability("autoGrantPermissions", "true");
	    dc.setCapability("ignoreHiddenApiPolicyError" , true);
		
		//SetUp Connection to Appium Server
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		return driver;

	}
	
	
	//Function to capture screenshot
	public static void getScreenshot(String testName) throws IOException
	{
		//cast the driver to capture screenshot
		File scrfile =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+testName+".png"));
	
	}
    
	}

	

