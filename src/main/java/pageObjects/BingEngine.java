package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sourceCode.genericMethods;

public class BingEngine extends genericMethods implements locators{

	public WebDriver driver;
	public BingEngine(WebDriver driver)
	{
		this.driver = driver;
	}
	public By search_box = By.xpath("//*[@aria-label='Enter your search term']");

	public By first_link=By.xpath("(//h2)[1]/a[contains(@href,'https:')]");

	public By getter(String a) {
		return search_box;
	}
}
