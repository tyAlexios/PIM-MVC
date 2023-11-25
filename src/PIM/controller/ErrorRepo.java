package PIM.controller;

import java.util.HashMap;

public class ErrorRepo {
    private static final HashMap<Integer, String> ErrorMap = new HashMap<>();

    static {
        ErrorMap.put(1, "< Error: Command does NOT exist. >\n");
        ErrorMap.put(2, "< Error: Invalid Input option. >\n Please input the number corresponding to the desired option from above.\n");

        ErrorMap.put(3, "< Error: Wrong format. >\n \tRight format: \"create {PIRtype} {PIRname}\"\n");
        ErrorMap.put(4, "< Error: Wrong format. >\n \tRight format: \"modify {PIRtype} {PIRname}\"\n");
        ErrorMap.put(5, "< Error: Wrong format. >\n \tRight format: \"print {PIRtype} {PIRname}\" or \"print -a\" to print all\n");
        ErrorMap.put(6, "< Error: Wrong format. >\n \tRight format: \"del {PIRtype} {PIRname}\"\n");
        ErrorMap.put(7, "< Error: Wrong format. >\n \tRight format: \"search {expression}\" or \"search -{PIRtype}\" or \"search -{PIRtype} {expression}\"\n");
        ErrorMap.put(8, "< Error: Wrong format. >\n \tRight format: \"load {path/to/fileName.pim}\"\n");
        ErrorMap.put(9, "< Error: Wrong format. >\n \tRight format: \"store {PIMname.pim}\" or \"store {PIMname.pim} {saveDir}\"\n");

        ErrorMap.put(10, "< Error: PIR type does NOT exist. >\n");
        ErrorMap.put(11, "< Error: PIR does NOT exist. >\n");
        ErrorMap.put(12, "< Error: PIR already exists. >\n");
        ErrorMap.put(13, "< There is no PIR created now. >\n");

        ErrorMap.put(14, "< Error: Wrong date format. >\n \tRight format: yyyy-MM-dd-HH:mm\n");
        ErrorMap.put(15, "< Error: Wrong mobile number format. >\n \tMobile number can only contain digits\n");

        ErrorMap.put(16, "< Error: Wrong expression. >\n \tPlease check user manual for more details\n");
        ErrorMap.put(17, "< Error: Wrong search type. >\n \tPIR type does NOT exist\n");

        ErrorMap.put(18, "< Error: File must have a \".pim\" suffix. >\n");
        ErrorMap.put(19, "< Error: The directory does not exist or it is not a directory. >\n");
        ErrorMap.put(20, "< Error: The file already exists. >\n");

        ErrorMap.put(21, "< Error: The file does not exists. >\n");
        ErrorMap.put(22, "< Nothing to load: The file is empty. >\n");

        ErrorMap.put(23, "< Error: Wrong format. >\n \tRight format: \"man {command}\"\n");


    }

    public static String getError(int errno) {
        return ErrorMap.get(errno);
    }
}