package oncall.util;

import java.text.NumberFormat;
import java.util.Locale;
import oncall.message.SpecialCharacter;

public class StringUtil {
    private static final String DELEMETER = SpecialCharacter.COMMA.getValue();
    private static final String WHITESPACE_REGEX = SpecialCharacter.WHITESPACE_REGEX.getValue();
    private static final String EMPTY_STRING = SpecialCharacter.EMPTY_STRING.getValue();

    //공백 제거
    public static String removeWhitespace(String input) {
        if (input == null) {
            return SpecialCharacter.EMPTY_STRING.getValue();
        }
        return input.replaceAll(WHITESPACE_REGEX, EMPTY_STRING);
    }

    //"," 로 문자 구분
    public static String[] splitByComma(String input) {
        if (input == null) {
            return new String[0];
        }
        return input.split(DELEMETER);
    }

    //천의 단위로 끊기
    public static String formatNumberWithComma(int number) {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(number);
    }

    //수익률 예시
    public static String formatProfitAsPercent(double profit) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.getDefault());
        percentFormat.setMinimumFractionDigits(0); // 최소 소수점 자릿수
        percentFormat.setMaximumFractionDigits(2); // 최대 소수점 자릿수
        percentFormat.setGroupingUsed(true); // 천 단위 구분자 사용

        return percentFormat.format(profit);
    }
}


