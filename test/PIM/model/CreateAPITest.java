package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAPITest {
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
        assertEquals(0, new CreateAPI().verify(new String[]{"create", PIRTextType, PIRName2ForTesting}));
        assertEquals(10, new CreateAPI().verify(new String[]{"create", UnknownPIRType, PIRNameForTesting}));
        assertEquals(12, new CreateAPI().verify(new String[]{"create", PIRTextType, PIRNameForTesting}));
        deleteAPI.init(new String[]{"delete", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
    }

    @Test
    public void init() {
        assertArrayEquals(new String[]{PIRTextPrimaryKey, null}, new CreateAPI().init(new String[]{"create", PIRTextType, PIRNameForTesting}));
        assertArrayEquals(new String[]{PIREventPrimaryKey, null, null, null}, new CreateAPI().init(new String[]{"create", PIREventType, PIRNameForTesting}));
        assertArrayEquals(new String[]{PIRContactPrimaryKey, null, null, null}, new CreateAPI().init(new String[]{"create", PIRContactType, PIRNameForTesting}));
        assertArrayEquals(new String[]{PIRTaskPrimaryKey, null, null}, new CreateAPI().init(new String[]{"create", PIRTaskType, PIRNameForTesting}));
    }

    @Test
    public void exe() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(PIRTextPrimaryKey, PIRRepo.getPIR(PIRTextPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestDescription, PIRRepo.getPIR(PIRTextPrimaryKey).getInfo()[1]);

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(PIREventPrimaryKey, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestStartingTime, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[1]);
        assertEquals(PIRTestAlarmTime, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[2]);
        assertEquals(PIRTestDescription, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[3]);

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(PIRContactPrimaryKey, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestName, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[1]);
        assertEquals(PIRTestAddress, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[2]);
        assertEquals(PIRTestMobileNumber, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[3]);

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(PIRTaskPrimaryKey, PIRRepo.getPIR(PIRTaskPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestDeadline, PIRRepo.getPIR(PIRTaskPrimaryKey).getInfo()[1]);
        assertEquals(PIRTestDescription, PIRRepo.getPIR(PIRTaskPrimaryKey).getInfo()[2]);

        deleteAPI.init(new String[]{"delete", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey});
    }

    @Test
    public void formatCheck() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, createAPI.formatCheck(1, PIRTestDescription));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(0, createAPI.formatCheck(1, PIRTestStartingTime));
        assertEquals(14, createAPI.formatCheck(1, ""));
        assertEquals(14, createAPI.formatCheck(1, "2023-11-23"));
        assertEquals(14, createAPI.formatCheck(1, "2023-11-23-09"));
        assertEquals(14, createAPI.formatCheck(1, "2023-11-23-09:00:00"));
        assertEquals(0, createAPI.formatCheck(2, PIRTestAlarmTime));
        assertEquals(0, createAPI.formatCheck(3, PIRTestDescription));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(0, createAPI.formatCheck(1, PIRTestName));
        assertEquals(0, createAPI.formatCheck(2, PIRTestAddress));
        assertEquals(0, createAPI.formatCheck(3, PIRTestMobileNumber));
        assertEquals(15, createAPI.formatCheck(3, "abc"));
        assertEquals(15, createAPI.formatCheck(3, "123abc"));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(0, createAPI.formatCheck(1, PIRTestDeadline));
        assertEquals(14, createAPI.formatCheck(1, ""));
        assertEquals(14, createAPI.formatCheck(1, "2023-11-23"));
        assertEquals(14, createAPI.formatCheck(1, "2023-11-23-09"));
        assertEquals(14, createAPI.formatCheck(1, "2023-11-23-09:00:00"));
        assertEquals(0, createAPI.formatCheck(2, PIRTestDescription));

        deleteAPI.init(new String[]{"delete", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey});
    }

    @Test
    public void finalCheck() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, createAPI.finalCheck(new String[]{PIRTextPrimaryKey, PIRTestDescription}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRTextPrimaryKey, null}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRTextPrimaryKey, ""}));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(0, createAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIREventPrimaryKey, null, PIRTestAlarmTime, PIRTestDescription}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestStartingTime, null, PIRTestDescription}));
        assertEquals(0, createAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, null}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIREventPrimaryKey, "", PIRTestAlarmTime, PIRTestDescription}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestStartingTime, "", PIRTestDescription}));
        assertEquals(0, createAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, ""}));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(0, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, null, PIRTestAddress, PIRTestMobileNumber}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestName, null, PIRTestMobileNumber}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, null}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, "", PIRTestAddress, PIRTestMobileNumber}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestName, "", PIRTestMobileNumber}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, ""}));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(0, createAPI.finalCheck(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRTaskPrimaryKey, null, PIRTestDescription}));
        assertEquals(0, createAPI.finalCheck(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, null}));
        assertEquals(-1, createAPI.finalCheck(new String[]{PIRTaskPrimaryKey, "", PIRTestDescription}));
        assertEquals(0, createAPI.finalCheck(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, ""}));

        deleteAPI.init(new String[]{"delete", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIREventType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIREventPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIRContactType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRContactPrimaryKey});
        deleteAPI.init(new String[]{"delete", PIRTaskType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTaskPrimaryKey});
    }


}