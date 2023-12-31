package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModifyAPITest {
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
    private final String PIRTestNewDescription = "New Test.";
    private final String PIRTestStartingTime = "2023-11-23-09:00";
    private final String PIRTestNewStartingTime = "2023-11-23-10:00";
    private final String PIRTestAlarmTime = "2023-11-23-10:00";
    private final String PIRTestNewAlarmTime = "2023-11-23-11:00";
    private final String PIRTestName = "Test User";
    private final String PIRTestNewName = "New Test User";
    private final String PIRTestAddress = "Test Address";
    private final String PIRTestNewAddress = "New Test Address";
    private final String PIRTestMobileNumber = "12345678";
    private final String PIRTestNewMobileNumber = "87654321";
    private final String PIRTestDeadline = "2023-11-23-11:00";
    private final String PIRTestNewDeadline = "2023-11-23-12:00";
    CreateAPI createAPI = new CreateAPI();
    ModifyAPI modifyAPI = new ModifyAPI();
    DeleteAPI deleteAPI = new DeleteAPI();


    @Test
    public void verify() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, new ModifyAPI().verify(new String[]{"modify", PIRTextType, PIRNameForTesting}));
        assertEquals(10, new ModifyAPI().verify(new String[]{"modify", UnknownPIRType, PIRNameForTesting}));
        assertEquals(11, new ModifyAPI().verify(new String[]{"modify", PIRTextType, PIRName2ForTesting}));

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
    }

    @Test
    public void init() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTextPrimaryKey, PIRTestDescription}, new ModifyAPI().init(new String[]{"modify", PIRTextType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertArrayEquals(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription}, new ModifyAPI().init(new String[]{"modify", PIREventType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertArrayEquals(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber}, new ModifyAPI().init(new String[]{"modify", PIRContactType, PIRNameForTesting}));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertArrayEquals(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription}, new ModifyAPI().init(new String[]{"modify", PIRTaskType, PIRNameForTesting}));

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
        modifyAPI.init(new String[]{"modify", PIRTextType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestNewDescription});
        assertEquals(PIRTextPrimaryKey, PIRRepo.getPIR(PIRTextPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestNewDescription, PIRRepo.getPIR(PIRTextPrimaryKey).getInfo()[1]);

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIREventType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, PIRTestNewAlarmTime, PIRTestNewDescription});
        assertEquals(PIREventPrimaryKey, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestNewStartingTime, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[1]);
        assertEquals(PIRTestNewAlarmTime, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[2]);
        assertEquals(PIRTestNewDescription, PIRRepo.getPIR(PIREventPrimaryKey).getInfo()[3]);

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        modifyAPI.init(new String[]{"modify", PIRContactType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestNewName, PIRTestNewAddress, PIRTestNewMobileNumber});
        assertEquals(PIRContactPrimaryKey, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestNewName, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[1]);
        assertEquals(PIRTestNewAddress, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[2]);
        assertEquals(PIRTestNewMobileNumber, PIRRepo.getPIR(PIRContactPrimaryKey).getInfo()[3]);

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIRTaskType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestNewDeadline, PIRTestNewDescription});
        assertEquals(PIRTaskPrimaryKey, PIRRepo.getPIR(PIRTaskPrimaryKey).getInfo()[0]);
        assertEquals(PIRTestNewDeadline, PIRRepo.getPIR(PIRTaskPrimaryKey).getInfo()[1]);
        assertEquals(PIRTestNewDescription, PIRRepo.getPIR(PIRTaskPrimaryKey).getInfo()[2]);

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
    public void formatCheck() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIRTextType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestNewDescription});
        assertEquals(0, modifyAPI.formatCheck(1, PIRTestDescription));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIREventType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, PIRTestNewAlarmTime, PIRTestNewDescription});
        assertEquals(0, modifyAPI.formatCheck(1, PIRTestStartingTime));
        assertEquals(14, modifyAPI.formatCheck(1, ""));
        assertEquals(14, modifyAPI.formatCheck(1, "2023-11-23"));
        assertEquals(14, modifyAPI.formatCheck(1, "2023-11-23-09"));
        assertEquals(14, modifyAPI.formatCheck(1, "2023-11-23-09:00:00"));
        assertEquals(0, modifyAPI.formatCheck(2, PIRTestAlarmTime));
        assertEquals(0, modifyAPI.formatCheck(3, PIRTestDescription));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        modifyAPI.init(new String[]{"modify", PIRContactType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestNewName, PIRTestNewAddress, PIRTestNewMobileNumber});
        assertEquals(0, modifyAPI.formatCheck(1, PIRTestName));
        assertEquals(0, modifyAPI.formatCheck(2, PIRTestAddress));
        assertEquals(0, modifyAPI.formatCheck(3, PIRTestMobileNumber));
        assertEquals(15, modifyAPI.formatCheck(3, "abc"));
        assertEquals(15, modifyAPI.formatCheck(3, "123abc"));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIRTaskType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestNewDeadline, PIRTestNewDescription});
        assertEquals(0, modifyAPI.formatCheck(1, PIRTestDeadline));
        assertEquals(14, modifyAPI.formatCheck(1, ""));
        assertEquals(14, modifyAPI.formatCheck(1, "2023-11-23"));
        assertEquals(14, modifyAPI.formatCheck(1, "2023-11-23-09"));
        assertEquals(14, modifyAPI.formatCheck(1, "2023-11-23-09:00:00"));
        assertEquals(0, modifyAPI.formatCheck(2, PIRTestDescription));

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
    public void finalCheck() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIRTextType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestNewDescription});
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIRTextPrimaryKey, PIRTestNewDescription}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRTextPrimaryKey, null}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRTextPrimaryKey, ""}));

        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIREventType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, PIRTestNewAlarmTime, PIRTestNewDescription});
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, PIRTestNewAlarmTime, PIRTestNewDescription}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, null, PIRTestNewAlarmTime, PIRTestNewDescription}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, null, PIRTestNewDescription}));
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, PIRTestNewAlarmTime, null}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, "", PIRTestNewAlarmTime, PIRTestNewDescription}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, "", PIRTestNewDescription}));
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIREventPrimaryKey, PIRTestNewStartingTime, PIRTestNewAlarmTime, ""}));

        createAPI.init(new String[]{"create", PIRContactType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        modifyAPI.init(new String[]{"modify", PIRContactType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRContactPrimaryKey, PIRTestNewName, PIRTestNewAddress, PIRTestNewMobileNumber});
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestNewName, PIRTestNewAddress, PIRTestNewMobileNumber}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, null, PIRTestNewAddress, PIRTestNewMobileNumber}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestNewName, null, PIRTestNewMobileNumber}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestNewName, PIRTestNewAddress, null}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, "", PIRTestNewAddress, PIRTestNewMobileNumber}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestNewName, "", PIRTestNewMobileNumber}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRContactPrimaryKey, PIRTestNewName, PIRTestNewAddress, ""}));

        createAPI.init(new String[]{"create", PIRTaskType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        modifyAPI.init(new String[]{"modify", PIRTaskType, PIRNameForTesting});
        modifyAPI.exe(new String[]{PIRTaskPrimaryKey, PIRTestNewDeadline, PIRTestNewDescription});
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIRTaskPrimaryKey, PIRTestNewDeadline, PIRTestNewDescription}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRTaskPrimaryKey, null, PIRTestNewDescription}));
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIRTaskPrimaryKey, PIRTestNewDeadline, null}));
        assertEquals(-1, modifyAPI.finalCheck(new String[]{PIRTaskPrimaryKey, "", PIRTestNewDescription}));
        assertEquals(0, modifyAPI.finalCheck(new String[]{PIRTaskPrimaryKey, PIRTestNewDeadline, ""}));

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