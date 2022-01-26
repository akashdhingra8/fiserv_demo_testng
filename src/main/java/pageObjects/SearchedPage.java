package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sourceCode.genericMethods;

public class SearchedPage extends genericMethods {

	public WebDriver driver;
	public SearchedPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public By first_link=By.xpath("(//*[@class=\"yuRUbf\"])[1]/a/h3");

}
