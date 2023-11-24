package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteAPITest {
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
    DeleteAPI deleteAPI = new DeleteAPI();
    @Test
    public void verify() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, new DeleteAPI().verify(new String[]{"delete", PIRTextType, PIRNameForTesting}));
        assertEquals(10, new DeleteAPI().verify(new String[]{"delete", UnknownPIRType, PIRNameForTesting}));
        assertEquals(11, new DeleteAPI().verify(new String[]{"delete", PIRTextType, PIRName2ForTesting}));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
    }

    @Test
    public void init() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTextPrimaryKey, PIRTestDescription}, new DeleteAPI().init(new String[]{"delete", PIRTextType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertArrayEquals(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription}, new DeleteAPI().init(new String[]{"delete", PIREventType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertArrayEquals(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber}, new DeleteAPI().init(new String[]{"delete", PIRContactType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription}, new DeleteAPI().init(new String[]{"delete", PIRTaskType, PIRNameForTesting}));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        deleteAPI.init(new String[]{"del", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        deleteAPI.init(new String[]{"del", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        deleteAPI.init(new String[]{"del", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
    }

    @Test
    public void exe() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, new DeleteAPI().verify(new String[]{"del", PIRTextType, PIRNameForTesting}));
        deleteAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(11, new DeleteAPI().verify(new String[]{"del", PIRTextType, PIRNameForTesting}));
    }
}