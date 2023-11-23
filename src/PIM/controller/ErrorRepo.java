package PIM.controller;

import java.util.HashMap;

public class ErrorRepo {
    private static final HashMap<Integer, String> ErrorMap = new HashMap<>();

    static {
        ErrorMap.put(1, "< Error: Command does NOT exist. >\n");
        ErrorMap.put(2, "< Error: Invalid Input option. >\n Please input the number corresponding to the desired option from above.\n");

        ErrorMap.put(3, "< Error: Wrong format. >\n \tRight format: \"new {PIRtype} {PIRname}\"\n");
        ErrorMap.put(4, "< Error: Wrong format. >\n \tRight format: \"modify {PIRtype} {PIRname}\"\n");
        ErrorMap.put(5, "< Error: Wrong format. >\n \tRight format: \"print {PIRtype} {PIRname}\" or \"print -a\" to print all\n");
        ErrorMap.put(6, "< Error: Wrong format. >\n \tRight format: \"del {PIRtype} {PIRname}\"\n");
        ErrorMap.put(7, "< Error: Wrong format. >\n \tRight format: \"search\"\n");
        ErrorMap.put(8, "< Error: Wrong format. >\n \tRight format: \"load\"\n");
        ErrorMap.put(9, "< Error: Wrong format. >\n \tRight format: \"store\"\n");


        ErrorMap.put(10, "< Error: PIR type does NOT exist. >\n");
        ErrorMap.put(11, "< Error: PIR does NOT exist. >\n");
        ErrorMap.put(12, "< Error: PIR already exists. >\n");
        ErrorMap.put(13, "< There is no PIR created now. >\n");

    }

    public static String getError(int errno) {
        return ErrorMap.get(errno);
    }
}