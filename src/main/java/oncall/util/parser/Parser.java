package oncall.util.parser;

import java.util.ArrayList;
import java.util.List;
import oncall.message.ErrorMessage;

public class Parser {
    ///TODO [HJ] 메서드 이름 적당히 변경
    public static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    ///TODO [HJ] 여기도 적절한 명칭으로 변경
    public static List<Integer> parseNumbers(String[] inputs) {
        List<Integer> numbers = new ArrayList<>();
        for (String input : inputs) {
            numbers.add(parseNumber(input));
        }
        return numbers;
    }
}
