import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CompareScreenshot {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofDays(10));
		driver.manage().window().maximize();
		
		
	}
	@Test
	public void compareImage() throws IOException
	{
		//to compare screenshot we need add ashot maven dependency
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
//		String expectedImagePath = "C:\\Users\\User\\Desktop\\expectedScreenshot.png";
//		String actualImagePath = "C:\\Users\\User\\Desktop\\actualScreenshot.png";
		
		
		
		//to convert string to file type 
//		File expectedImageFile = new File(expectedImagePath);
//		File actualImageFile = new File(actualImagePath);
	
		//to read the image we use BufferedImage class
//		BufferedImage expectedImage = ImageIO.read(expectedImageFile);
		//BufferedImage actualImage = ImageIO.read(actualImageFile);
		
		BufferedImage expectedImageFile = ImageIO.read(new File("C:\\Users\\User\\Desktop\\OrangeHRMLOGO.png"));
		WebElement logoImageElement = driver.findElement(By.xpath("//*[@class=\"navbar-brand nav-logo\"]/img"));
		Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver,logoImageElement);
		BufferedImage actualImage = logoImageScreenshot.getImage();
		
		//to compare the image we use makeDiff method which is present in ImageDiffer class
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImageFile, actualImage);
		if(diff.hasDiff()==true)
		{
		  System.out.println("Images are not same");
		}
		else
		{
			System.out.println("Images are same");
		}
	}
	
	@Test
	public void partialScreenshot() throws IOException
	{
		//full screenshot
		 File ts = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 Files.copy(ts, new File("./screenshot/fullwindow.png"));
		 
		 //partial screenshot
		 WebElement demoElement = driver.findElement(By.xpath("(//*[contains(text(),'Book a Free Demo')])[2]"));
		 File demoImage = demoElement.getScreenshotAs(OutputType.FILE);
		 Files.copy(demoImage, new File("./screenshot/bookDemo.png"));
		 
	     WebElement logoImageElement = driver.findElement(By.xpath("//*[@class=\"navbar-brand nav-logo\"]/img"));
	     File logoImage = logoImageElement.getScreenshotAs(OutputType.FILE);
	     Files.copy(logoImage, new File("./screenshot/logo.png"));
		 
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}

}
