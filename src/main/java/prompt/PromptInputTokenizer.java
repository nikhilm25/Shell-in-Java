package prompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PromptInputTokenizer {

    private final String input;
    private StringBuilder builder;
    private boolean isInsideSingleQuotes;
    private boolean isInsideDoubleQuotes;
    private boolean isAfterBackslash;

    private final Set<Character> charactersToIgnore = Set.of('\'', '\"', ' ');
    private final Set<Character> specialCharacters = Set.of('\"', '$', '\\');

    public PromptInputTokenizer(String input) {
        this.input = input;
        this.builder = new StringBuilder();
        this.isInsideSingleQuotes = false;
        this.isInsideDoubleQuotes = false;
        this.isAfterBackslash = false;
    }

    public List<String> tokenize() {
        List<String> keywords = new ArrayList<>();

        for (int charIndex = 0; charIndex < input.length(); charIndex++) {
            char character = input.charAt(charIndex);

            if (character == '\'' && !isInsideDoubleQuotes && !isAfterBackslash) {
                isInsideSingleQuotes = !isInsideSingleQuotes;
            } else if (character == '"' && !isInsideSingleQuotes && !isAfterBackslash) {
                isInsideDoubleQuotes = !isInsideDoubleQuotes;
            } else if (isAfterBackslash) {
                builder.append(character);
                isAfterBackslash = false;
            } else if (character == '\\') {
                handleBackslashCharacter(character, charIndex);
            } else if (isInsideSingleQuotes || isInsideDoubleQuotes) {
                builder.append(character);
            } else if (!charactersToIgnore.contains(character)) {
                builder.append(character);
            } else if (!builder.isEmpty()) {
                keywords.add(builder.toString());
                builder = new StringBuilder();
            }
        }

        if (!builder.isEmpty())
            keywords.add(builder.toString());

        return keywords;
    }

    private void handleBackslashCharacter(char character, int charIndex) {
        isAfterBackslash = true;
        int nextIndex = charIndex + 1;

        if (shouldAddBackslash(nextIndex))
            builder.append(character);
    }

    private boolean shouldAddBackslash(int nextIndex) {
        return isInsideSingleQuotes
                || (isInsideDoubleQuotes
                && nextIndex < input.length()
                && !specialCharacters.contains(input.charAt(nextIndex)));
    }
}
