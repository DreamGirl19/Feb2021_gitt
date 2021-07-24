package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {
	
	WebDriver driver;
	 
	 String browser ="chrome";
	 
	 @BeforeClass
	 public void readConfig() {
		 
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("./src/main/java/config/config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	 }
		
		@BeforeMethod
		public void init() {
		
			//control over multiple browser at the same time
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver = new ChromeDriver();	
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe");
				driver = new FirefoxDriver();	
			}
			

			driver.get("https://www.techfios.com/billing/?ng=login/");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			}


		@SuppressWarnings("unused")
		@Test(priority=1)
		public void loginTest() {

			//this is Element Library
			By USERNAME_ELEMENT = By.xpath("//input[@id='username']");
			By PASSWORD_ELEMENT = By.xpath("//input[@id='password']");
			By SIGNIN_ELEMENT = By.xpath("/html/body/div/div/div/form/div[3]/button");
			By DASHBOARD_FIELD_LOCATOR = By.xpath("//h2[contains(text(),'Dashboard')]");
			
			
			String userName = "demo@techfios.com";
			String password = "abc123";
			
			driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
			driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
			driver.findElement(SIGNIN_ELEMENT).click();
			
			Assert.assertEquals(driver.findElement(DASHBOARD_FIELD_LOCATOR).getText(), "Dashboard", "Dashboard page not found!!!");
			
			By CUSTOMER_ELEMENT = By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]");
			By ADD_CUSTOMER_ELEMENT = By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a");
			
		
			long timeInSeconds = 2;
				WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
				wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CUSTOMER_ELEMENT));

			
			By ADDCUSTOMER_ELEMENT = By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div/div[1]/h5");

			boolean pageTitleDisplayStatus;
			try {
				WebElement ADDCUSTOMER_ELEMENT1 = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div/div[1]/h5"));
				pageTitleDisplayStatus = true;
			} 
			catch (Exception e) {
				pageTitleDisplayStatus = false;
				e.printStackTrace();
			}
		
			Assert.assertTrue(pageTitleDisplayStatus, "Addcustomer page is not available!");

			driver.findElement(CUSTOMER_ELEMENT).click();
			driver.findElement(ADDCUSTOMER_ELEMENT).click();

			By FULLNAME_ELEMENT = By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[1]/label");
			By COMPANY_DROPDOWN_ELEMENT = By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[2]/label");
			By EMAIL_ELEMENT = By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[3]/label");
			By PHONE_ELEMENT = By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[4]/label");
			By CITY_ELEMENT = By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[6]/label");
			By STATE_REGION_ELEMENT = By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[7]/label");
			By ZIP_ELEMENT = By.xpath("//input[@id='zip']");
			By SAVE_BUTTON = By.xpath("//button[@class='btn btn-primary']");
			By LIST_CONTACTS_ELEMENT = By.xpath("//a[contains(text(),'List Contacts')]");
			
			//Login Data (TestData or MockData)
			
			String fullName = "DreamGirl";
			String companyName = "Google";
			String email = "Dream@techfios.com";
			String phone = "214 453 5543";
			String city = "Frisco";
			String stateRegion = "Texas";
			String zipCode = "75035";
			String listContact = "DreamGirl";
		
			//Generate Random Number
			Random rnd = new Random();
			int randomNum = rnd.nextInt(999);
			
			//Fill out add customers form
			driver.findElement(FULLNAME_ELEMENT).sendKeys(fullName +randomNum);
			driver.findElement(COMPANY_DROPDOWN_ELEMENT).sendKeys(companyName);
			driver.findElement(EMAIL_ELEMENT).sendKeys(randomNum + email);
			driver.findElement(PHONE_ELEMENT).sendKeys(phone);
			driver.findElement(CITY_ELEMENT).sendKeys(city);
			driver.findElement(STATE_REGION_ELEMENT).sendKeys(stateRegion);
			driver.findElement(ZIP_ELEMENT).sendKeys(zipCode);
			driver.findElement(SAVE_BUTTON).click();
			driver.findElement(LIST_CONTACTS_ELEMENT).sendKeys(listContact);
		
			}	

	

		@Test(priority=2)
		public void negetiveLoginTest() {
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
	




