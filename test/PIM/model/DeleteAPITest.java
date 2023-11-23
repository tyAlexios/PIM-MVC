package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteAPITest {
    private final String TestPIRName = "test";
    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String UnknownPIRType = "unknown";
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + TestPIRName;
    private final String PIREventPrimaryKey = '[' + PIREventType + ']' + TestPIRName;
    private final String PIRContactPrimaryKey = '[' + PIRContactType + ']' + TestPIRName;
    private final String PIRTaskPrimaryKey = '[' + PIRTaskType + ']' + TestPIRName;
    private final String PIRTestDescription = "Test.";
    private final String PIRTestStartingTime = "2023-11-23-09:00";
    private final String PIRTestAlarmTime = "2023-11-23-10:00";
    private final String PIRTestName = "Test User";
    private final String PIRTestAddress = "Test Address";
    private final String PIRTestMobileNumber = "12345678";
    private final String PIRTestDeadline = "2023-11-23-11:00";
    @Test
    public void verify() {
        CreateAPI createAPI = new CreateAPI();
        createAPI.init(new String[]{"create", PIRTextType, TestPIRName});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, new DeleteAPI().verify(new String[]{"delete", PIRTextType, TestPIRName}));
        assertEquals(10, new DeleteAPI().verify(new String[]{"delete", UnknownPIRType, TestPIRName}));
        assertEquals(11, new DeleteAPI().verify(new String[]{"delete", PIRTextType, TestPIRName}));
    }

    @Test
    public void init() {
        CreateAPI createAPI = new CreateAPI();
        createAPI.init(new String[]{"create", PIRTextType, TestPIRName});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTextPrimaryKey, PIRTestDescription}, new DeleteAPI().init(new String[]{"delete", PIRTextType, TestPIRName}));

        createAPI.init(new String[]{"create", PIREventType, TestPIRName});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertArrayEquals(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription}, new DeleteAPI().init(new String[]{"delete", PIREventType, TestPIRName}));

        createAPI.init(new String[]{"create", PIRContactType, TestPIRName});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertArrayEquals(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber}, new DeleteAPI().init(new String[]{"delete", PIRContactType, TestPIRName}));

        createAPI.init(new String[]{"create", PIRTaskType, TestPIRName});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription}, new DeleteAPI().init(new String[]{"delete", PIRTaskType, TestPIRName}));
    }

    @Test
    public void exe() {
        CreateAPI createAPI = new CreateAPI();
        createAPI.init(new String[]{"create", PIRTextType, TestPIRName});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, new DeleteAPI().exe(new String[]{PIRTextPrimaryKey, PIRTestDescription}));
    }
}