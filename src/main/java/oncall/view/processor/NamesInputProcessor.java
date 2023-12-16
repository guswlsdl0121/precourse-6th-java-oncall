package oncall.view.processor;

import java.util.List;
import oncall.dto.EmergencyDudyDTOs;
import oncall.dto.EmergencyDutyDTO;
import oncall.util.validator.NamesValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class NamesInputProcessor {
    private NamesInputProcessor() {
    }

    public static EmergencyDudyDTOs ask() {
        OutputView.printRequestWeekDayRequest();
        EmergencyDutyDTO weekDayDTO = InputView.readWeekDayNames();

        OutputView.printRequestHolidayRequest();
        EmergencyDutyDTO holiDayDTO = InputView.readHoliDayNames();

        NamesValidator.validate(weekDayDTO);
        NamesValidator.validate(holiDayDTO);

        return new EmergencyDudyDTOs(List.of(weekDayDTO, holiDayDTO));
    }

    public static void printError(IllegalArgumentException e) {
        OutputView.printError(e.getMessage());
    }
}
