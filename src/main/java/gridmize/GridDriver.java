package gridmize;

import org.openqa.selenium.WebDriver;

public class GridDriver {

	private static WebDriver webDriver;

	public static void set(WebDriver driver) {
		GridDriver.webDriver = driver;
	}
	
	public static WebDriver get() {
		return webDriver;
	}
}
