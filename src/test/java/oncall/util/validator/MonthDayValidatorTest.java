package oncall.util.validator;

import oncall.dto.MonthDayDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonthDayValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 13, 100})
    void validateInvalidMonths(int month) {
        MonthDayDTO dto = new MonthDayDTO(month, DayOfWeek.MONDAY);
        assertThrows(IllegalArgumentException.class, () -> MonthDayValidator.validate(dto));
    }

    @Test
    void validateNullDayOfWeek() {
        MonthDayDTO dto = new MonthDayDTO(1, null);
        assertThrows(IllegalArgumentException.class, () -> MonthDayValidator.validate(dto));
    }

    private static Stream<Arguments> provideInvalidFebruaryDates() {
        return Stream.of(
                Arguments.of(new MonthDayDTO(2, DayOfWeek.of(30))),
                Arguments.of(new MonthDayDTO(2, DayOfWeek.of(31)))
        );
    }
}
