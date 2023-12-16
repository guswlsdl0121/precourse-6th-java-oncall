package oncall.message;

public enum UnitWord {
    WON("원"),
    PIECES("개");

    private final String value;

    UnitWord(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
