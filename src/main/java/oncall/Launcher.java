package oncall;

import java.util.List;
import oncall.controller.AssignDutyController;
import oncall.controller.InputController;
import oncall.domain.EmergencyDuty;
import oncall.domain.EmergencyDutyRepository;
import oncall.domain.EmergencyDutyScheduler;
import oncall.dto.EmergencyDudyDTOs;
import oncall.dto.MonthDayDTO;
import oncall.view.OutputView;
import oncall.vo.Days;

public class Launcher {
    private Launcher() {
    }

    public static void run() {
        MonthDayDTO monthDayDTO = InputController.inputMonthDay();

        EmergencyDudyDTOs emergencyDudyDTOs = InputController.inputNames();

        Days monthDays = EmergencyDutyScheduler.generateDutyDates(monthDayDTO);

        AssignDutyController assignDutyController = new AssignDutyController(emergencyDudyDTOs);
        assignDutyController.assign(monthDays);

        EmergencyDutyRepository emergencyDutyRepository = EmergencyDutyRepository.getInstance();

        List<EmergencyDuty> emergencyDuties = emergencyDutyRepository.getAssignedDuties();

        OutputView.printResult(monthDays, emergencyDuties);

        emergencyDutyRepository.clearDuties();
    }
}
