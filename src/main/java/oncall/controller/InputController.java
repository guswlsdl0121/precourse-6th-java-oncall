package oncall.controller;

import oncall.dto.MonthDayDTO;
import oncall.view.processor.MonthDayProcessor;

public class InputController {
    private InputController() {
    }

    public static MonthDayDTO inputMonthDay() {
        while (true) {
            try {
                return MonthDayProcessor.ask();
            } catch (IllegalArgumentException e) {
                MonthDayProcessor.printError(e);
            }
        }
    }
}
