package PIM.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchAPI implements API
{

    @Override
    public int verify(String[] cmd)
    {
        return 0;
    }

    @Override
    public String[] init(String[] para)
    {
        return new String[0];
    }

    @Override
    public void exe(String[] para)
    {
        ConditionEvaluator evaluator = new ConditionEvaluator();
        //boolean result = evaluator.evaluateExpression("10:00 < 12:00 && (12:00 > 11:00 || !13:00 < 14:00)");

        List<String[]> PIRSet = PIRRepo.RepoImage();
        for (String curPIR: PIRSet)
        {

        }

    }

    private static class ConditionEvaluator
    {
        private static final Pattern TOKEN_PATTERN = Pattern.compile(
                "\"[^\"]*\"|\\d{4}-\\d{2}-\\d{2}-\\d{2}:\\d{2}|\\d{2}:\\d{2}|<=?|>=?|!=|=|\\|\\||&&|!|\\(|\\)"
        );

        // Method to evaluate the entire expression
        public boolean evaluateExpression(String expression)
        {
            // Parse the expression into components (time comparisons and logical operators)
            List<String> tokens = parseExpression(expression);
            // Evaluate the parsed tokens
            return evaluateTokens(tokens, 0, tokens.size() - 1);
        }

        // Recursive method to evaluate tokens considering parentheses
        private boolean evaluateTokens(List<String> tokens, int start, int end)
        {
            Stack<Boolean> values = new Stack<>();
            Stack<String> ops = new Stack<>();

            for (int i = start; i <= end; i++) {
                String token = tokens.get(i);
                if (token.equals("(")) {
                    // Find the corresponding closing parenthesis and evaluate the sub-expression
                    int j = findClosing(tokens, i);
                    boolean val = evaluateTokens(tokens, i + 1, j - 1);
                    values.push(val);
                    i = j;
                } else if (token.equals(")")) {
                    // Should not reach here if findClosing() works correctly
                } else if (isOperator(token)) {
                    // Push operators to the ops stack
                    ops.push(token);
                } else {
                    // Evaluate time comparison and push result to the values stack
                    boolean result = evaluateTimeComparison(token);
                    values.push(result);
                }

                // Apply the operator at the top of the ops stack to the top two values in the values stack
                while (!ops.isEmpty() && !Objects.equals(ops.peek(), "(")) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
            }

            // The values stack should now contain the result of the expression
            return values.pop();
        }

        // Helper method to evaluate a single time comparison
        private boolean evaluateTimeComparison(String comparison) {
            // Split the comparison into parts
            String[] parts = comparison.split(" ");
            if (parts.length == 3) {
                // Determine the format of the dates/times being compared
                LocalDateTime firstDateTime = parseDateTime(parts[0]);
                LocalDateTime secondDateTime = parseDateTime(parts[2]);

                return switch (parts[1]) {
                    case "<" -> firstDateTime.isBefore(secondDateTime);
                    case ">" -> firstDateTime.isAfter(secondDateTime);
                    case "=" -> firstDateTime.isEqual(secondDateTime);
                    // Add other cases for "!=" etc.
                    default -> throw new IllegalArgumentException("Invalid comparison operator");
                };
            } else {
                throw new IllegalArgumentException("Invalid time comparison format");
            }
        }

        private LocalDateTime parseDateTime(String dateTimeStr) {
            // Try parsing as full date-time first
            try {
                return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
            } catch (DateTimeParseException e) {
                // Not a full date-time, try parsing as time-only
                try {
                    LocalTime time = LocalTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                    // If only time is provided, use the current date for comparison
                    return LocalDateTime.of(LocalDate.now(), time);
                } catch (DateTimeParseException ex) {
                    throw new IllegalArgumentException("Invalid date/time format");
                }
            }
        }

        // Helper method to apply a logical operator
        private boolean applyOp(String op, boolean b, boolean a) {
            return switch (op) {
                case "&&" -> a && b; // Logical AND
                case "||" -> a || b; // Logical OR
                case "!" -> !b;     // Logical NOT
                default -> false;
            };
        }

        // Helper method to find the closing parenthesis for a sub-expression
        private int findClosing(List<String> tokens, int openPos) {
            int closePos = openPos;
            int counter = 1;
            while (counter > 0) {
                String token = tokens.get(++closePos);
                if (token.equals("(")) counter++;
                if (token.equals(")")) counter--;
            }
            return closePos;
        }

        // Helper method to parse the expression into tokens
        public List<String> parseExpression(String expression) {
            List<String> tokens = new ArrayList<>();
            Matcher matcher = TOKEN_PATTERN.matcher(expression);
            while (matcher.find()) {
                tokens.add(matcher.group());
            }
            return tokens;
        }

        // Helper method to determine if a string is an operator
        private boolean isOperator(String token) {
            return token.equals("&&") || token.equals("||") || token.equals("!");
        }
    }


}
