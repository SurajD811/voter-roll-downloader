package com.infiniteanalytics.assignment.generics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverCommanLib extends BaseTest {

	public void selectTheOptionOfDropdown(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);

	}

	public void verifypageTitle(String pageName, String expectedTitle) {
		String actualPageTitle = driver.getTitle();
		if (actualPageTitle.equals(expectedTitle)) {
			Reporter.log(" " + pageName + " title is matched", true);
		}

		else {

			Reporter.log(" " + pageName + " title is not matched", true);
		}
	}

	public void selectTheOptionOfDropdown(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);

	}

	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBePresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}
	
	
	// Read captcha Text
	
	public String getCaptchaText(WebElement captchaImg) throws IOException, TesseractException {
        

        // Take a screenshot of the entire page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // Get location and size of CAPTCHA image
        Point location = captchaImg.getLocation();
        int width = captchaImg.getSize().getWidth();
        int height = captchaImg.getSize().getHeight();

        // Crop only the CAPTCHA portion
        BufferedImage captchaImage = fullImg.getSubimage(location.getX(), location.getY(), width, height);

        // Save cropped CAPTCHA image
        File captchaFile = new File("captcha.png");
        ImageIO.write(captchaImage, "png", captchaFile);

        // Set up Tesseract OCR
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(Tesseract_Path);  // Set your Tesseract path
        tesseract.setLanguage("eng");
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789");

        // Perform OCR on CAPTCHA image
        String captchaText = tesseract.doOCR(captchaFile);
        System.out.println("Extracted CAPTCHA Text: " + captchaText.trim());

        return captchaText.trim(); // Return extracted text
    }
	
}


