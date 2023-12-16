package oncall.util.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;
import oncall.dto.EmergencyDutyDTO;
import oncall.enums.DutyType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NamesValidatorTest {

    @ParameterizedTest
    @MethodSource("provideInvalidEmergencyDutyDTOs")
    void validateInvalidEmergencyDutyDTOs(EmergencyDutyDTO weekDayDTO, EmergencyDutyDTO holiDayDTO) {
        assertThrows(IllegalArgumentException.class, () -> NamesValidator.validate(weekDayDTO, holiDayDTO));
    }

    private static Stream<Arguments> provideInvalidEmergencyDutyDTOs() {
        return Stream.of(
                Arguments.of(new EmergencyDutyDTO(DutyType.WEEKDAY, List.of("긴이름긴이름", "도밥", "고니")), new EmergencyDutyDTO(DutyType.HOLIDAY, List.of("수아", "루루"))),
                Arguments.of(new EmergencyDutyDTO(DutyType.WEEKDAY, List.of("준팍", "도밥", "고니")), new EmergencyDutyDTO(DutyType.HOLIDAY, List.of("수아", "루루"))),
                Arguments.of(new EmergencyDutyDTO(DutyType.WEEKDAY, generateListOfNames()), new EmergencyDutyDTO(DutyType.HOLIDAY, List.of("수아"))),
                Arguments.of(new EmergencyDutyDTO(DutyType.WEEKDAY, List.of("준팍", "준팍", "도밥")), new EmergencyDutyDTO(DutyType.HOLIDAY, List.of("수아", "루루")))
        );
    }

    private static List<String> generateListOfNames() {
        return Stream.generate(() -> "사원")
                .limit(36)
                .toList();
    }
}
