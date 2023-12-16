package oncall.message;

public enum MessageHeader {
    ERROR_HEADER("[ERROR]");

    private final String header;

    MessageHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
