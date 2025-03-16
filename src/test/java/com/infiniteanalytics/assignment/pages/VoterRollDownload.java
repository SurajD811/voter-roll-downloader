package com.infiniteanalytics.assignment.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import com.infiniteanalytics.assignment.generics.BaseTest;
import com.infiniteanalytics.assignment.generics.WebDriverCommanLib;

import net.sourceforge.tess4j.TesseractException;

public class VoterRollDownload extends BaseTest {

	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlDist")
	private WebElement district_Name_Dropdown;
	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlAC")
	private WebElement assembly_Constituency_Dropdown;
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnlogin")
	private WebElement get_Polling_Station_Button;
	@FindBy(xpath = "//td[5]//a[contains(@id,'ctl00')]")
	private WebElement view_English_Link;
	@FindBy(id = "Image2")
	private WebElement captcha_Image;
	@FindBy(id = "txtVerificationCode")
	private WebElement verification_Code_InputBox;
	@FindBy(id = "btnSubmit")
	private WebElement submit_Button;
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlDist']//option[@selected='selected']")
	private WebElement selected_DropdownValue_District_Name_Dropdown;
	@FindBy(xpath = "//select[@id='ctl00_ContentPlaceHolder1_ddlAC']//option[@selected='selected']")
	private WebElement selected_DropdownValue_Assembly_Constituency_Dropdown;
	@FindBy(id="lblCaptchaMessage")
	private WebElement errorMessage;
	@FindBy(id="icon")private WebElement downloadButton;

	public VoterRollDownload(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public void downloadVoterRoll() throws InterruptedException, IOException, TesseractException, AWTException {
		WebDriverCommanLib wcl = new WebDriverCommanLib();
		wcl.waitForElementToBeVisible(district_Name_Dropdown);

		String TitleOFThePage = driver.getTitle();
		String mainWindowHandle=driver.getWindowHandle();

		if (TitleOFThePage.contains("Electoral Rolls")) {
			Reporter.log("Url Navigation Successfull landed on Electoral Rolls Page",true);
		}

		wcl.selectTheOptionOfDropdown(district_Name_Dropdown, 1);
		String selectedValueDistrictNameDropdown = selected_DropdownValue_District_Name_Dropdown.getText();

		if (selectedValueDistrictNameDropdown.equals("1-Kumaram Bheem Asifabad")) {
			Reporter.log("Successfully Selected value from Ditrict Name Dropdown",true);
		}

		wcl.selectTheOptionOfDropdown(assembly_Constituency_Dropdown, 1);

		get_Polling_Station_Button.click();
		wcl.waitForElementToBeVisible(view_English_Link);

		if (view_English_Link.isDisplayed()) {
			Reporter.log("Polling Details Chart Displayed Successfully",true);
		}

		view_English_Link.click();

		Thread.sleep(2000);

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			if (!handle.equals(mainWindowHandle)) {
				System.out.println("Switched");
		        driver.switchTo().window(handle);
		        driver.manage().window().maximize();
		        break;
		    }
		}

		int maxAttempts = 5;  // Number of retries
		int attempt = 0;
		String verificationCode = "";

		while (attempt < maxAttempts) {
		    // Get CAPTCHA text
		    verificationCode = wcl.getCaptchaText(captcha_Image);
		    System.out.println("Attempt " + (attempt + 1) + " - Verification Code: " + verificationCode);
		    
		    // Check if verificationCode contains only numbers
		    if (verificationCode.matches("\\d+")) {
		        // Valid numeric code
		        verification_Code_InputBox.clear();
		        verification_Code_InputBox.sendKeys(verificationCode);
		        submit_Button.click();
		        Thread.sleep(5000);
		        break;  
		    } else {
		        // Invalid code, refresh page
		        System.out.println("CAPTCHA not numeric, refreshing to get new code...");
		        driver.navigate().refresh();
		        Thread.sleep(2000);  // Wait after refresh
		        wcl.waitForElementToBeVisible(captcha_Image);  // Wait for CAPTCHA to reappear
		    }
		    
		    attempt++;
		}
		
		

		JavascriptExecutor js = (JavascriptExecutor) driver;
			boolean isDownloading = (Boolean) js.executeScript("return document.readyState").equals("loading");

			if (isDownloading) {
				System.out.println("Download started!");
			} else {
				System.out.println("No download detected.");
			}
		
	}

}
