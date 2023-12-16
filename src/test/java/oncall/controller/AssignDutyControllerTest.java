package oncall.controller;

import oncall.controller.AssignDutyController;
import oncall.domain.EmergencyDuty;
import oncall.domain.EmergencyDutyRepository;
import oncall.dto.EmergencyDudyDTOs;
import oncall.dto.EmergencyDutyDTO;
import oncall.enums.DutyType;
import oncall.vo.Day;
import oncall.vo.Days;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AssignDutyControllerTest {

    private EmergencyDudyDTOs emergencyDudyDTOs;
    private Days testDays;

    @BeforeEach
    public void setup() {
        emergencyDudyDTOs = new EmergencyDudyDTOs(Arrays.asList(
                new EmergencyDutyDTO(DutyType.WEEKDAY, Arrays.asList("현진", "효림", "포비")),
                new EmergencyDutyDTO(DutyType.HOLIDAY, Arrays.asList("왼손", "철수", "영희"))
        ));

        testDays = new Days(Arrays.asList(
                new Day(LocalDate.of(2023, 1, 1), true), // 일요일, 휴일
                new Day(LocalDate.of(2023, 1, 2), false), // 월요일, 평일
                new Day(LocalDate.of(2023, 1, 3), false), // 화요일, 평일
                new Day(LocalDate.of(2023, 1, 4), false), // 수요일, 평일
                new Day(LocalDate.of(2023, 1, 5), true)  // 목요일, 휴일
                // 추가 날짜 및 휴일 여부
        ));
    }

    @Test
    public void testAssign() {
        AssignDutyController controller = new AssignDutyController(emergencyDudyDTOs);
        controller.assign(testDays);

        EmergencyDutyRepository repository = EmergencyDutyRepository.getInstance();
        List<EmergencyDuty> assignedDuties = repository.getAssignedDuties();

        assertEquals(5, assignedDuties.size()); // 5일간의 근무가 배정되었는지 확인
        assertEquals("왼손", assignedDuties.get(0).crewName()); // 첫 번째 날짜는 휴일이므로 Dave가 배정
        assertEquals("현진", assignedDuties.get(1).crewName()); // 두 번째 날짜는 평일이므로 Alice가 배정
        // 나머지 날짜에 대한 검증
    }
}
