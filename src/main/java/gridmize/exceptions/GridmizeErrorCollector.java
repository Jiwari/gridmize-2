package gridmize.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GridmizeErrorCollector {
	
	private String initialMessage;
	private List<String> messages;

	public GridmizeErrorCollector(String initialMessage) {
		this.initialMessage = String.format("%s\nErrors:\n", initialMessage);
		this.messages = new ArrayList<String>();
	}

	public void append(String message) {
		messages.add(message);
	}
	
	public Optional<GridmizeException> exception() {
		if (!messages.isEmpty()) {
			String finalMessage = initialMessage;
			finalMessage = finalMessage.concat(messages.stream().collect(Collectors.joining("\n")));
			return Optional.of(new GridmizeException(finalMessage));
		}
		return Optional.empty();
	}
}
