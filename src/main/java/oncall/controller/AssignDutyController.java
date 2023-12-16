package oncall.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.CellRendererPane;
import oncall.domain.EmergencyDuty;
import oncall.domain.EmergencyDutyRepository;
import oncall.dto.EmergencyDudyDTOs;
import oncall.dto.EmergencyDutyDTO;
import oncall.enums.DutyType;
import oncall.message.SpecialCharacter;
import oncall.vo.Day;
import oncall.vo.Days;

public class AssignDutyController {
    private final Deque<String> weekdayCrew;
    private final Deque<String> holidayCrew;
    Map<DutyType, List<String>> dutyMap;

    public AssignDutyController(EmergencyDudyDTOs emergencyDudyDTOs) {
        dutyMap = emergencyDudyDTOs.emergencyDutyDTOs().stream()
                .collect(Collectors.toMap(
                        EmergencyDutyDTO::dutyType,
                        EmergencyDutyDTO::nicknames
                ));

        this.weekdayCrew = new ArrayDeque<>(dutyMap.getOrDefault(DutyType.WEEKDAY, new ArrayList<>()));
        this.holidayCrew = new ArrayDeque<>(dutyMap.getOrDefault(DutyType.HOLIDAY, new ArrayList<>()));
    }

    public void assign(Days monthDays) {
        EmergencyDutyRepository repository = EmergencyDutyRepository.getInstance();

        String lastCrew = SpecialCharacter.EMPTY_STRING.getValue();
        for (Day day : monthDays.days()) {
            String assignedCrew = getNextCrew(day.isHoliday(), lastCrew);
            lastCrew = assignedCrew;

            repository.addDuty(new EmergencyDuty(day.date(), assignedCrew, day.isHoliday()));
        }
    }

    private String getNextCrew(boolean isHoliday, String lastCrew) {
        Deque<String> currentDeque = getCrewDeque(isHoliday);
        Deque<String> oppositeDeque = getOppositeCrewDeque(isHoliday);

        if (currentDeque.isEmpty()) {
            refillDeque(currentDeque, isHoliday ? DutyType.HOLIDAY : DutyType.WEEKDAY);
        }

        String nextCrew = currentDeque.removeFirst();

        if (nextCrew.equals(lastCrew) && !oppositeDeque.isEmpty()) {
            // 연속 근무 방지를 위해 대체 근무자를 사용합니다.
            String alternativeCrew = oppositeDeque.removeFirst();

            // 대체 근무자를 현재 데크의 앞으로 이동
            currentDeque.addFirst(alternativeCrew);

            // 원래 근무자를 바로 다음 근무일에 배치
            currentDeque.addFirst(nextCrew);
            return alternativeCrew;
        }

        return nextCrew;
    }

    private void refillDeque(Deque<String> deque, DutyType dutyType) {
        List<String> crewList = dutyMap.getOrDefault(dutyType, new ArrayList<>());
        deque.addAll(crewList);
    }


    private Deque<String> getCrewDeque(boolean isHoliday) {
        if (isHoliday) {
            return holidayCrew;
        }
        return weekdayCrew;
    }


    private Deque<String> getOppositeCrewDeque(boolean isHoliday) {
        if (isHoliday) {
            return weekdayCrew;
        }
        return holidayCrew;
    }

}
