package gridmize;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Grid<T extends HeaderInterface> {

	private By rowSelector;
	private By columnSelector;
	private By tableSelector;
	private By theadSelector;
	private By tbodySelector;
	private By headerSelector;
	private Class<T> headerEnum;

	public Grid(Class<T> headerEnum, By tableSelector, By theadSelector, By tbodySelector, By rowSelector,
			By headerSelector, By columnSelector) {
		this.headerEnum = headerEnum;
		this.tableSelector = tableSelector;
		this.theadSelector = theadSelector;
		this.tbodySelector = tbodySelector;
		this.rowSelector = rowSelector;
		this.headerSelector = headerSelector;
		this.columnSelector = columnSelector;
	}
	
	public Grid(Class<T> headerEnum, By tableSelector, By rowSelector, By columnSelector) {
		this(headerEnum, tableSelector,  
				By.cssSelector("thead"), 
				By.cssSelector("tbody"),
				rowSelector,
				By.cssSelector("th"),
				columnSelector);
				
	}

	public Grid(Class<T> headerEnum) {
		this(headerEnum, By.cssSelector("table"), 
				By.cssSelector("tr"),
				By.cssSelector("td"));
	}
	
	/**
	 * Alias for {@code verifyHeaderNames} with ignoreCase = true.
	 */
	public void verifyHeaderNames() {
		verifyHeaderNames(true);
	}
	
	/**
	 * Verifies if the names on T from {@code getColumnName} are the same of the
	 * elements displayed on the page.
	 */
	public void verifyHeaderNames(boolean ignoreCase) {
		List<WebElement> headerElements = Driver.get().findElement(tableSelector)
				.findElement(theadSelector)
				.findElements(headerSelector);
		
		new GridValidator<T>(headerEnum).validateHeaderWithWebElements(headerElements, ignoreCase);
	}

	/**
	 * Returns a list of all rows displayed on the grid.
	 * 
	 * @return {@link List}<{@link Row}{@code <T>>}
	 */
	public List<Row<T>> getRows() {
		List<WebElement> webElementRows = Driver.get().findElement(tableSelector)
				.findElement(tbodySelector)
				.findElements(rowSelector);
		
		List<Row<T>> rows = new ArrayList<>();
		for (WebElement row : webElementRows) {
			rows.add(new Row<T>(row, columnSelector, headerEnum));
		}
		
		return rows;
	}

	/**
	 * Returns all elements of the specified column.
	 * 
	 * @param header
	 * @return @return {@link List}<{@link Cell}{@code <T>>}
	 */
	public List<Cell<T>> getColumn(T header) {
		List<Row<T>> rows = getRows();
		List<Cell<T>> cells = new ArrayList<>();
		for (Row<T> row : rows) {
			cells.add(row.getColumn(header));
		}
		return cells;
	}
}
