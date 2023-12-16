package oncall.util.validator;

import oncall.dto.EmergencyDutyDTO;

import java.util.HashSet;
import java.util.Set;

public class NamesValidator {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_MEMBERS = 5;
    private static final int MAX_TOTAL_MEMBERS = 35;

    public static void validate(EmergencyDutyDTO weekDayDTO, EmergencyDutyDTO holiDayDTO) {
        validateNameLength(weekDayDTO);
        validateNameLength(holiDayDTO);

        validateMinMembers(weekDayDTO);
        validateMinMembers(holiDayDTO);

        validateWeekdayMembers(weekDayDTO);
        validateMaxHolidayMembers(holiDayDTO);

        validateNoDuplicatesWithinType(weekDayDTO);
        validateNoDuplicatesWithinType(holiDayDTO);
    }

    private static void validateNameLength(EmergencyDutyDTO dto) {
        for (String name : dto.nicknames()) {
            if (name.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException("이름의 길이는 최대 5자를 넘을 수 없습니다: " + name);
            }
        }
    }

    private static void validateMinMembers(EmergencyDutyDTO dto) {
        if (dto.nicknames().size() < MIN_MEMBERS) {
            throw new IllegalArgumentException("근무 순번에는 최소 5명이 포함되어야 합니다: " + dto.dutyType());
        }
    }

    private static void validateWeekdayMembers(EmergencyDutyDTO weekDayDTO) {
        int totalMembers = weekDayDTO.nicknames().size();

        if (totalMembers > MAX_TOTAL_MEMBERS) {
            throw new IllegalArgumentException("총 근무 인원은 최대 35명을 넘을 수 없습니다.");
        }
    }

    private static void validateMaxHolidayMembers(EmergencyDutyDTO holidayDTO) {
        int totalMembers = holidayDTO.nicknames().size();

        if (totalMembers > MAX_TOTAL_MEMBERS) {
            throw new IllegalArgumentException("총 근무 인원은 최대 35명을 넘을 수 없습니다.");
        }
    }


    private static void validateNoDuplicatesWithinType(EmergencyDutyDTO dto) {
        Set<String> namesSet = new HashSet<>();
        for (String name : dto.nicknames()) {
            if (!namesSet.add(name)) {
                throw new IllegalArgumentException("동일한 타입 내에서 중복된 이름이 있습니다: " + name);
            }
        }
    }
}
