import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidatePDFFile {
	WebDriver driver;
	File file = new File("C:\\Users\\User\\Downloads\\vinitham2_QA (1).pdf");
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
//		driver.get(url);
	}
	@Test
	public void pdfReaderTest() throws IOException
	{
//		URL pdfUrl = new URL(url);
//		InputStream ip = pdfUrl.openStream();
//		BufferedInputStream bf = new BufferedInputStream(ip);
		 PDDocument pdDocument = Loader.loadPDF(file);
		
		//Page content text
		PDFTextStripper pdfStrip = new PDFTextStripper();
		String pdfText = pdfStrip.getText(pdDocument);
		System.out.println(pdfText);
		Assert.assertTrue(pdfText.contains("VINITHA M"));
		
		//page count
		int pageCount = pdDocument.getNumberOfPages();
		System.out.println(pageCount);
		Assert.assertEquals(pageCount, 1);
		
		//set the page number and get the text
		//pdfStrip.setStartPage(1);
		//System.out.println(pdfStrip.getText(pdDocument));
		
		
	
		
	}

}
