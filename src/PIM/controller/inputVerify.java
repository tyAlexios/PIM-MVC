package PIM.controller;

public class inputVerify
{
    public static boolean optionVerify(String optionStr, int maxVal)
    {
        if (optionStr.length() != 1)
        {
            PIM.output(ErrorRepo.getError(2));
            return false;
        }
        int option = optionStr.charAt(0) - '0';
        if (option < 0 || option > maxVal)
        {
            PIM.output(ErrorRepo.getError(2));
            return false;
        }
        return true;
    }
}
