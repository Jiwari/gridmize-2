package gridmize;

import org.openqa.selenium.WebElement;

public class Cell<T extends HeaderInterface> {

	protected WebElement webElementColumn;
	private T headerColumn;

	public Cell(WebElement webElementColumn, T headerColumn) {
		this.webElementColumn = webElementColumn;
		this.headerColumn = headerColumn;
	}
	
	public WebElement getWebElement() {
		if (headerColumn.getActionSelector() == null) {
			return webElementColumn;
		} else {
			return webElementColumn.findElement(headerColumn.getActionSelector());
		}
	}

	public T getHeaderColumn() {
		return headerColumn;
	}
}
