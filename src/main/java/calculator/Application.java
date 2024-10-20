package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        String str;

        System.out.print("덧셈할 문자열을 입력해 주세요: ");
        str = Console.readLine();

        try {
            int result = Arr.add(str);
            System.out.println("결과: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 값을 입력하였습니다: " + e.getMessage());
            throw e;
        }
    }
}

class Arr {
    public static int add(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        String[] tokens = splitInputString(str);

        return calculateSum(tokens);
    }

    private static String[] splitInputString(String text) {
        String delimiter = ",|:";
        if (text.startsWith("//")) {
            int idx = text.indexOf("\n");
            if (idx == -1) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
            }
            String customDelimiter = text.substring(2, idx);
            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다.");
            }
            text = text.substring(idx + 1);
            delimiter += "|" + Pattern.quote(customDelimiter);
        }

        return text.split(delimiter);
    }

    private static int calculateSum(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                try {
                    int value = Integer.parseInt(token);
                    if (value < 0) {
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                    }
                    sum += value;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("잘못된 값을 입력하였습니다.");
                }
            }
        }
        return sum;
    }
}