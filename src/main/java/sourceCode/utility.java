package sourceCode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;




public class utility 
{
	// global variables
	public WebDriver driver; // driver 
	public Properties prop;  // properties file object

	// function to get values from property file
	public String get_value_from_property_file(String key) 
	{
		prop= new Properties();
		try
		{
			FileInputStream fis=new FileInputStream("resources\\resource.properties");
			prop.load(fis);
			fis.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return (prop.getProperty(key));
	}
	
	// Loading browser --- by getting value from property file
	public WebDriver initialiseDriver()
		{

			String browserName = get_value_from_property_file("browser");
			if(browserName.equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver_97.exe");
					driver= new ChromeDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
				}
				
			else if (browserName.equalsIgnoreCase("firefox"))
				{
					System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
					driver= new FirefoxDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
				}
			
			else if (browserName.equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", "Drivers//IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();

				} 
			
			else if (browserName.equalsIgnoreCase("edge"))
				{
					System.setProperty("webdriver.edge.driver", "Drivers//MicrosoftWebDriver.exe");
					WebDriver driver = new EdgeDriver();
					driver.manage().window().maximize();
				} 
			
			else 
				{
					try
						{
							throw new Exception("Please provide correct browser/browser name");
						} 
					catch (Exception e) 
						{
							e.printStackTrace();
						}
				}

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
		}

	public void TearDownBrowser()
	{
		driver.quit();
		//driver = null;
	}



}
