package oncall.message;

public enum SpecialCharacter {
    SPACE(" "),
    COMMA(","),
    EMPTY_STRING(""),
    WHITESPACE_REGEX("\\s+"),
    DASH("-"),
    NEW_LINE(System.lineSeparator());

    private final String value;

    SpecialCharacter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
