import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class FileDownload {
	
	@Test
	public void downloadSeleniumJars() throws InterruptedException
	{
		ChromeOptions option = new ChromeOptions();
		HashMap<String,Object>chromePrefs = new HashMap<String,Object>();
		chromePrefs.put("profile.default_content_settings.popups",0);
		
		String downloadFilePath = System.getProperty("user.dir");
		chromePrefs.put("download.default_directory",downloadFilePath);
		
		option.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(option);
		driver.get("https://www.selenium.dev/downloads/");
		driver.manage().window().maximize();
		WebElement javaFile = driver.findElement(By.xpath("(//*[contains(text(),'Java')])[1]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",javaFile);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[contains(text(),'4.19.0 (March 27, 2024)')])[3]")).click();
		
	}

}
