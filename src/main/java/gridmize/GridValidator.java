package gridmize;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebElement;

import gridmize.exceptions.GridmizeErrorCollector;
import gridmize.exceptions.GridmizeException;

public class GridValidator<T extends HeaderInterface> {
	
	private List<T> headerEnums;

	protected GridValidator(Class<T> headerEnums) {
		this.headerEnums = Arrays.asList(headerEnums.getEnumConstants());
	}

	public void verifyElementsSizeWithColumns(List<WebElement> webElementColumns) {
		if (webElementColumns.size() != getHighestColumnIndex()) {
			throw new GridmizeException(
					"List of elements size and columns size are different. Either your Enum is built with the wrong index sizes, or your selector for columns is returning the wrong number of elements");
		}
	}
	
	public void validateHeaderWithWebElements(List<WebElement> headerElements, boolean ignoreCase) {
		verifyElementsSizeWithColumns(headerElements);
		final String message = "The header defined on the enum is different from the one located on the page!";
		GridmizeErrorCollector errorCollector = new GridmizeErrorCollector(message);
		
		for (T headerEnum : headerEnums) {
			String headerExpected = headerEnum.getColumnName();
			String headerActual = headerElements.get(headerEnum.getColumnIndex()).getText();
			boolean isCorrect = ignoreCase ? headerExpected.equalsIgnoreCase(headerActual)
					: headerExpected.equals(headerActual);
			if (!isCorrect) {
				String formattedMessage = String.format("%s\nExpected (%s): %s\nActual name found on page: %s",
						message,
						headerExpected.getClass().getName(), 
						headerExpected, 
						headerActual);
				errorCollector.append(formattedMessage);
			}
		}
		
		if (errorCollector.exception().isPresent()) {
			throw errorCollector.exception().get();
		}
	}

	private int getHighestColumnIndex() {
		Optional<Integer> highest = headerEnums
			.stream().map(T::getColumnIndex)
			.sorted((a, b) -> b - a)
			.findFirst();
		return highest.get() + 1;
	}
}
