package gridmize;

import org.openqa.selenium.WebDriver;

public class Driver {

	private static WebDriver webDriver;

	public static void set(WebDriver driver) {
		Driver.webDriver = driver;
	}
	
	public static WebDriver get() {
		return webDriver;
	}
}
