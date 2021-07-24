package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Config {
	
	WebDriver driver; 
	 String browser= "chrome";
	 String url;
	 
	 @BeforeClass
	 public void readConfig() {
		 
		 Properties prop = new Properties();
		 
		 try {
			 //InputStream //BufferReader //FileReader//Scanner
			 InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			 prop.load(input);
			 browser = prop.getProperty(browser);
			 System.out.println("Browser used: "  + browser);
			 url = prop.getProperty("url");
			 	 
		 }
		 catch (IOException e) {
			 e.printStackTrace();	 
		 }
	 }
		
		@BeforeMethod
		public void init() {
			
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webDriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
				driver = new FirefoxDriver();
			}

			driver.get("url");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			}

		@Test
		public void loginTest(By DASHBOARD_FIELD_LOCATOR, By SIGNIN_ELEMENT) {
			
			//Element Library
			By USERNAME_ELEMENT = By.xpath("//input[@id='username']");
			By PASSWORD_ELEMENT = By.xpath("//input[@id='password']");
			By SIGNIN_ELEMENT1 = By.xpath("/html/body/div/div/div/form/div[3]/button");
			By DASHBOARD_FIELD_LOCATOR1 = By.xpath("//h2[contains(text(),'Dashboard')]");
			
			
			//Login Data
			String loginId = "demo@techfios.com";
			String password = "abc123";
		
			driver.findElement(USERNAME_ELEMENT).sendKeys("loginId");
			driver.findElement(PASSWORD_ELEMENT).sendKeys("password");
			driver.findElement(SIGNIN_ELEMENT1).click();
		
			
			//waitForElement(driver, 3, DASHBOARD_FIELD_LOCATOR).getText();
			
			
			String dashboardValidationText = driver.findElement(DASHBOARD_FIELD_LOCATOR).getText();
			Assert.assertEquals(driver.findElement(DASHBOARD_FIELD_LOCATOR1).getText(), "Dashboard","Dashboard page not found!");
			
		}
		
		
		public void waitForElement(WebDriver driver2, int i, By dashboardButtonLocator) {
			
			
		}

		@Test
		public void negetiveLoginTest1() {
			driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
			driver.findElement(By.id("password")).sendKeys("abc1234");
			driver.findElement(By.name("login")).click();
		}
		
		@AfterMethod
		public void tearDown() {
			
			//close the window
			driver.close();
			
			//kills the process we started
			//driver.quit();
		}
	}



