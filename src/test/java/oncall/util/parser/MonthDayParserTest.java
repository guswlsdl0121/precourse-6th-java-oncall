package oncall.util.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MonthDayParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"1,불", "2,컴", "3,프"})
    void parseInvalidDayOfWeek(String input) {
        assertThrows(IllegalArgumentException.class, () -> MonthDayParser.parser(input));
    }
}
