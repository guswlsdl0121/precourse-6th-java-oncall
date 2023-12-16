package oncall.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import oncall.domain.EmergencyDuty;
import oncall.domain.EmergencyDutyRepository;
import oncall.dto.EmergencyDudyDTOs;
import oncall.dto.EmergencyDutyDTO;
import oncall.enums.DutyType;
import oncall.message.SpecialCharacter;
import oncall.vo.Day;
import oncall.vo.Days;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class AssignDutyController {
    private final Queue<String> weekdayCrew;
    private final Queue<String> holidayCrew;

    public AssignDutyController(EmergencyDudyDTOs emergencyDudyDTOs) {
        Map<DutyType, List<String>> dutyMap = emergencyDudyDTOs.emergencyDutyDTOs().stream()
                .collect(Collectors.toMap(
                        EmergencyDutyDTO::dutyType,
                        EmergencyDutyDTO::nicknames
                ));

        this.weekdayCrew = new LinkedList<>(dutyMap.getOrDefault(DutyType.WEEKDAY, new ArrayList<>()));
        this.holidayCrew = new LinkedList<>(dutyMap.getOrDefault(DutyType.HOLIDAY, new ArrayList<>()));
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
        Queue<String> crewQueue = isHoliday ? holidayCrew : weekdayCrew;

        if (!crewQueue.isEmpty() && crewQueue.peek().equals(lastCrew)) {
            // 현재 근무자와 다음 근무자 교체
            String nextCrew = crewQueue.poll();
            crewQueue.add(nextCrew);
        }
        return crewQueue.poll();
    }
}
