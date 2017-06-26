package gridmize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import gridmize.exceptions.GridmizeException;
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
     * @return A list of cells that represent the column of this row.
     */
    public List<Cell<T>> getColumns() {
        List<WebElement> webElementColumns = webElementRow.findElements(columnSelector);
        List<Cell<T>> cell = new ArrayList<>();

        for (T headerColumn : headerEnums) {
            cell.add(new Cell<T>(webElementColumns.get(headerColumn.getColumnIndex()), headerColumn));
        }

        return cell;
    }

    /**
     * Returns a specific column of this row.
     *
     * @param header Column header
     * @return The requested column of this row.
     */
    public Cell<T> getColumn(T header) {
        List<Cell<T>> cells = getColumns();
        return cells.stream().filter(cell -> verifyHeader(cell, header))
                .findFirst()
                .orElseThrow(() -> new GridmizeException("Não foi encontrado um header com o nome "
                        .concat(header.getColumnName())));
    }

    public static <T extends HeaderInterface> boolean verifyHeader(Cell<T> cell, T header) {
        return cell.getHeaderColumn().getColumnName().equals(header.getColumnName());
    }
}
