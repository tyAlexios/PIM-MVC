package PIM.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrintAPITest {
    private final String PIRNameForTesting = "test";
    private final String PIRName2ForTesting = "test2";
    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String UnknownPIRType = "unknown";
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + PIRNameForTesting;
    private final String PIREventPrimaryKey = '[' + PIREventType + ']' + PIRNameForTesting;
    private final String PIRContactPrimaryKey = '[' + PIRContactType + ']' + PIRNameForTesting;
    private final String PIRTaskPrimaryKey = '[' + PIRTaskType + ']' + PIRNameForTesting;
    private final String PIRTestDescription = "Test.";
    private final String PIRTestStartingTime = "2023-11-23-09:00";
    private final String PIRTestAlarmTime = "2023-11-23-10:00";
    private final String PIRTestName = "Test User";
    private final String PIRTestAddress = "Test Address";
    private final String PIRTestMobileNumber = "12345678";
    private final String PIRTestDeadline = "2023-11-23-11:00";
    CreateAPI createAPI = new CreateAPI();
    PrintAPI printAPI = new PrintAPI();
    DeleteAPI deleteAPI = new DeleteAPI();

    private final String PrintTextFormat = """
            
            <Data of %s>
            [Notes]
               %s
            
            """;
    private final String PrintTaskFormat = """

            <Data of %s>
            [Deadline]
                %s
            [Description]
                %s
            
            """;
    private final String PrintEventFormat = """
            
            <Data of %s>
            [Starting Time]
                %s
            [Alarm Time]
                %s
            [Description]
                %s
            
            """;
    private final String PrintContactFormat = """
            
            <Data of %s>
            [Names]
                 %s
            [Addresses]
                %s
            [Mobile Numbers]
                %s
            
            """;

    @Test
    public void verify() {
        assertEquals(13, printAPI.verify(new String[]{"print", "-a"}));
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, printAPI.verify(new String[]{"print", "-a"}));
        assertEquals(0, printAPI.verify(new String[]{"print", PIRTextType, PIRNameForTesting}));
        assertEquals(11, printAPI.verify(new String[]{"print", UnknownPIRType, PIRNameForTesting}));
        assertEquals(11, printAPI.verify(new String[]{"print", PIRTextType, PIRName2ForTesting}));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
    }

    @Test
    public void init() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTextPrimaryKey, PIRTestDescription}, printAPI.init(new String[]{"print", PIRTextType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertArrayEquals(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription}, printAPI.init(new String[]{"print", PIREventType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertArrayEquals(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber}, printAPI.init(new String[]{"print", PIRContactType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription}, printAPI.init(new String[]{"print", PIRTaskType, PIRNameForTesting}));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        deleteAPI.init(new String[]{"del", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey});
        deleteAPI.init(new String[]{"del", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey});
        deleteAPI.init(new String[]{"del", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey});
    }

    @Test
    public void exe() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        printAPI.init(new String[]{"print", PIRTextType, PIRNameForTesting});
        printAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintTextFormat, PIRTextPrimaryKey, PIRTestDescription));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        printAPI.init(new String[]{"print", PIREventType, PIRNameForTesting});
        printAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintEventFormat, PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        printAPI.init(new String[]{"print", PIRContactType, PIRNameForTesting});
        printAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintContactFormat, PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        printAPI.init(new String[]{"print", PIRTaskType, PIRNameForTesting});
        printAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintTaskFormat, PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        deleteAPI.init(new String[]{"del", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey});
        deleteAPI.init(new String[]{"del", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey});
        deleteAPI.init(new String[]{"del", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey});
    }

    @Test
    public void flushPrintCache() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        printAPI.init(new String[]{"print", PIRTextType, PIRNameForTesting});
        printAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintTextFormat, PIRTextPrimaryKey, PIRTestDescription));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        printAPI.init(new String[]{"print", PIREventType, PIRNameForTesting});
        printAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintEventFormat, PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        printAPI.init(new String[]{"print", PIRContactType, PIRNameForTesting});
        printAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintContactFormat, PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        printAPI.init(new String[]{"print", PIRTaskType, PIRNameForTesting});
        printAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(printAPI.flushPrintCache(), String.format(PrintTaskFormat, PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        deleteAPI.init(new String[]{"del", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey});
        deleteAPI.init(new String[]{"del", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey});
        deleteAPI.init(new String[]{"del", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey});
    }
}