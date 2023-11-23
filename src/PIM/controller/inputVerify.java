package PIM.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class inputVerify
{
    public static boolean optionVerify(String optionStr, int maxVal)
    {
        if (optionStr.length() != 1)
            return false;
        int option = optionStr.charAt(0) - '0';
        if (option < 0 || option > maxVal)
            return false;

        return true;
    }

    public static boolean dateVerify(String dateStr)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        try {
            LocalDateTime.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
