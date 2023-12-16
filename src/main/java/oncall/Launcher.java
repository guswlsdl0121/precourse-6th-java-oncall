package oncall;

import oncall.controller.InputController;
import oncall.dto.MonthDayDTO;

public class Launcher {
    private Launcher() {
    }

    public static void run() {
        MonthDayDTO monthDayDTO = InputController.inputMonthDay();
        System.out.println(monthDayDTO);
    }
}
