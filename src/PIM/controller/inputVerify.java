package PIM.controller;

abstract class inputVerify
{
    public static boolean optionVerify(String optionStr, int maxVal)
    {
        if (optionStr.length() != 1)
            return true;
        int option = optionStr.charAt(0) - '0';
        if (option < 0 || option > maxVal)
            return true;

        return false;
    }

}
