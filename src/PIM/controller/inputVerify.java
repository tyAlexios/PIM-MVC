package PIM.controller;

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
}
