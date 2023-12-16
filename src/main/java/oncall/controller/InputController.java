package oncall.controller;

import oncall.dto.EmergencyDudyDTOs;
import oncall.dto.MonthDayDTO;
import oncall.view.processor.MonthDayProcessor;
import oncall.view.processor.NamesInputProcessor;

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

    public static EmergencyDudyDTOs inputNames() {
        while (true) {
            try {
                return NamesInputProcessor.ask();
            } catch (IllegalArgumentException e) {
                NamesInputProcessor.printError(e);
            }
        }
    }
}
