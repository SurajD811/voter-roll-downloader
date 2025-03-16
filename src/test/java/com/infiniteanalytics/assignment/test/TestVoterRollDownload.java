package com.infiniteanalytics.assignment.test;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.infiniteanalytics.assignment.generics.BaseTest;
import com.infiniteanalytics.assignment.generics.RetryAnalyzer;
import com.infiniteanalytics.assignment.generics.TestListener;
import com.infiniteanalytics.assignment.pages.VoterRollDownload;

import net.sourceforge.tess4j.TesseractException;

@Listeners(TestListener.class)
public class TestVoterRollDownload extends BaseTest{
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testVoterRollDownload() throws InterruptedException, IOException, TesseractException, AWTException {
		VoterRollDownload vrd=new VoterRollDownload(driver);
		vrd.downloadVoterRoll();
	}
}
