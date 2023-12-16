package oncall.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import oncall.domain.EmergencyDuty;
import oncall.message.ConsoleMessage;
import oncall.message.MessageHeader;
import oncall.vo.Day;
import oncall.vo.Days;

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

    public static void printRequestWeekDayRequest() {
        System.out.println(ConsoleMessage.REQUEST_WEEK_DAY);
    }

    public static void printRequestHolidayRequest() {
        System.out.println(ConsoleMessage.REQUEST_HOLI_DAY);
    }


    public static void printResult(Days monthDays, List<EmergencyDuty> emergencyDuties) {
        Map<LocalDate, String> dutyMap = emergencyDuties.stream()
                .collect(Collectors.toMap(EmergencyDuty::date, EmergencyDuty::crewName));

        for (Day day : monthDays.days()) {
            String dutyInfo = OutputViewFormatter.formatDay(day) + dutyMap.get(day.date());
            System.out.println(dutyInfo);
        }
    }
}

