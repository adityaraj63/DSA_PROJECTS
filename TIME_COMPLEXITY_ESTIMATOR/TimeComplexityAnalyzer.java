package TIME_COMPLEXITY_ESTIMATOR;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeComplexityAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your java code (end with 'exit'):");

        StringBuilder code = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("exit"))
                break;
            code.append(line).append("\n");
        }

        String codeText = code.toString().toLowerCase();

        int forCount = countOccurrences(codeText, "for");
        int whileCount = countOccurrences(codeText, "while");

        if (forCount > 1 || whileCount > 1 || (forCount > 0 && whileCount > 0)) {
            System.out.println("🧠 Estimated Time Complexity: O(n^2)");
        } else if (forCount == 1 || whileCount == 1) {
            System.out.println("🧠 Estimated Time Complexity: O(n)");

        } else if (containsLogNLoop(codeText)) {
            System.out.println("🧠 Estimated Time Complexity: O(log n)");

        } else if (codeText.contains("recursion")) {
            System.out.println(
                    "🧠 Estimated Time Complexity: Recursive - Analyze further (e.g., O(2^n), O(n log n), etc.)");

        } else {
            System.out.println("🧠 Estimated Time Complexity: O(1) or Unknown");
        }
        sc.close();
    }

    static int countOccurrences(String text, String word) {
        int count = 0, index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }

    static boolean containsLogNLoop(String code) {
        Pattern pattern = Pattern.compile("for\\s*\\(.*;.*;.*\\*=.*\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(code);
        return matcher.find();
    }
}
