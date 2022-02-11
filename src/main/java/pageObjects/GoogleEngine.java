package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sourceCode.genericMethods;

public class GoogleEngine extends genericMethods implements locators {

	public WebDriver driver;
	public GoogleEngine(WebDriver driver)
	{
		this.driver = driver;
	}
	public By search_box=By.xpath("//*[@name='q']");
	public By first_link=By.xpath("(//*[@class=\"yuRUbf\"])[1]/a/h3");
//	String search_box="//*[@name='q']";
//	String first_link="(//*[@class=\"yuRUbf\"])[1]/a/h3";
	public By getter(String a) {
		return search_box;
	}

//
//	public By getter(String a) {
//		System.out.println(GoogleEngine.a);
//		return By.xpath(GoogleEngine.a);
//	}
}

