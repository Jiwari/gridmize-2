package gridmize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Row<T extends HeaderInterface> {

	private WebElement webElementRow;
	private By columnSelector;
	private T[] headerEnums;

	public Row(WebElement webElementRow, By columnSelector, Class<T> headerEnums) {
		this.webElementRow = webElementRow;
		this.columnSelector = columnSelector;
		this.headerEnums = headerEnums.getEnumConstants();
	}

	/**
	 * Returns a list that represents the columns of this row.
	 * 
	 * @return
	 */
	public List<Cell<T>> getColumns() {
		List<WebElement> webElementColumns = webElementRow.findElements(columnSelector);
		List<Cell<T>> cell = new ArrayList<>();

		// Size validation
		verifyElementsSizeWithColumns(webElementColumns);

		for (T headerColumn : headerEnums) {
			cell.add(new Cell<T>(webElementColumns.get(headerColumn.getColumnIndex()), headerColumn));
		}

		return cell;
	}

	private void verifyElementsSizeWithColumns(List<WebElement> webElementColumns) {
		if (webElementColumns.size() != getHighestColumnIndex()) {
			throw new RuntimeException(
					"List of elements size and columns size are different. Either your Enum is built with the wrong index sizes, or your selector for columns is returning the wrong number of elements");
		}
	}

	private int getHighestColumnIndex() {
		int highest = 0;
		for (T item : headerEnums) {
			if (highest < item.getColumnIndex()) {
				highest = item.getColumnIndex();
			}
		}
		return ++highest;
	}

	/**
	 * Returns a specific column of this row.
	 * 
	 * @param header
	 * @return {@link Cell}<T>
	 */
	public Cell<T> getColumn(T header) {
		List<Cell<T>> cells = getColumns();
		return cells.get(header.getColumnIndex());
	}
}
