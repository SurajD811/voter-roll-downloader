package com.infiniteanalytics.assignment.generics;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConstant {
	
	protected static WebDriver driver;
	@BeforeSuite
	public void setUp() throws IOException
	{
		
		//File Download to project folder
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.default_directory", Download_File_Path);
		prefs.put("plugins.always_open_pdf_externally", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        
        
		Flib f=new Flib();
		String url = f.readPropertyData(PROP_PATH, "voterRollUrl");	
		WebDriverManager.chromedriver().setup();
	    driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(url);

	}

	public void failed(String methodName)
	{
		try{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(SCREENSHOT_PATH+methodName+".png");
			Files.copy(src, dest);
		}
		catch (Exception e) {
		}
	}

		@AfterSuite
		public void tearDown()
		{
			driver.quit();
		}

}
