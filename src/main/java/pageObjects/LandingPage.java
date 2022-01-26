package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import sourceCode.genericMethods;

public class LandingPage extends genericMethods {

	public WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public By search_box=By.xpath("//*[@name='q']");

}
