package oncall.view;

import oncall.message.ConsoleMessage;
import oncall.message.MessageHeader;

public class OutputView {
    public static final String ERROR_HEADER = MessageHeader.ERROR_HEADER.getHeader();

    private OutputView() {
    }

    public static void printError(String message) {
        System.out.println(ERROR_HEADER + message);
    }

    public static void printRequestMonthDay() {
        System.out.println(ConsoleMessage.REQUEST_MONTH_DAY);
    }
}
