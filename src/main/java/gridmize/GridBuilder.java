package gridmize;

import org.openqa.selenium.By;

/**
 * Created by wermuth on 4/15/17.
 */
public class GridBuilder<T extends HeaderInterface> {

    private Grid<T> gridInstance;

    public <T extends HeaderInterface> GridBuilder(Class<T> headerEnum) {
        gridInstance = new Grid(headerEnum);
    }

    public Grid<T> build() {
        return gridInstance;
    }

    public GridBuilder withRowSelector(By rowSelector) {
        gridInstance.setRowSelector(rowSelector);
        return this;
    }

    public GridBuilder withColumnSelector(By columnSelector) {
        gridInstance.setColumnSelector(columnSelector);
        return this;
    }

    public GridBuilder withTableSelector(By tableSelector) {
        gridInstance.setTableSelector(tableSelector);
        return this;
    }

    public GridBuilder withTheadSelector(By theadSelector) {
        gridInstance.setTheadSelector(theadSelector);
        return this;
    }

    public GridBuilder withTbodySelector(By tbodySelector) {
        gridInstance.setTbodySelector(tbodySelector);
        return this;
    }

    public GridBuilder withHeaderSelector(By headerSelector) {
        gridInstance.setHeaderSelector(headerSelector);
        return this;
    }
}
