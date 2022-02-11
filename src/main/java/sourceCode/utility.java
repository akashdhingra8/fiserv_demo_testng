package sourceCode;

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
	public String get_value_from_property_file(String file_name,String key)
	{
		prop= new Properties();
		try
		{
			String path = runnerConfiguration.project_path + "/resources/" +file_name+".properties";
			FileInputStream fis=new FileInputStream(path);
			prop.load(fis);
			fis.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return (prop.getProperty(key));
	}

	// function to get command line variable
	public String get_command_line_env_variable() throws IOException {
//		java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
//		java.util.Properties properties = new Properties();
//		properties.load(inputStream);
		System.out.println(System.getProperty("url_engine"));
		String url_engine = System.getProperty("url_engine");
//		String url_engine = properties.getProperty("url_engine");
		System.out.println(url_engine);
		return (url_engine);
	}
	
	// Loading browser --- by getting value from property file
	public WebDriver initialiseDriver()
		{

			String browserName = get_value_from_property_file("resource","browser");
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
			driver.manage().deleteAllCookies();

			return driver;
		}

	public void TearDownBrowser()
	{
		driver.quit();
		//driver = null;
	}



}
