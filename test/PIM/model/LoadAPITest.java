package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoadAPITest {
    private final String PIRNameForTesting = "test";
    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String PIRTaskPrimaryKey = '[' + PIRTaskType + ']' + PIRNameForTesting;
    private final String PIRContactPrimaryKey = '[' + PIRContactType + ']' + PIRNameForTesting;
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + PIRNameForTesting;
    private final String PIREventPrimaryKey = '[' + PIREventType + ']' + PIRNameForTesting;
    private final String PIRTestDescription = "Test.";
    private final String PIRTestStartingTime = "2023-11-23-09:00";
    private final String PIRTestAlarmTime = "2023-11-23-10:00";
    private final String PIRTestPath = System.getProperty("user.dir");
    private final String PIRTestFileName = "test.pim";
    private final String PIRTestFileName2 = "test2.pim";
    private final String PIRTestFileName3 = "test3.pim";
    private final String PIRTestFilePath = PIRTestPath + '/' + PIRTestFileName;
    private final String PIRTestFilePath2 = PIRTestPath + '/' + PIRTestFileName2;
    private final String PIRTestFilePath3 = PIRTestPath + '/' + PIRTestFileName3;
    CreateAPI createAPI = new CreateAPI();
    StoreAPI storeAPI = new StoreAPI();
    LoadAPI loadAPI = new LoadAPI();
    DeleteAPI deleteAPI = new DeleteAPI();


    @Test
    public void verify() {
        try {
            assertEquals(21, loadAPI.verify(new String[]{"load", PIRTestFilePath}));
        } catch (Exception e) {
            e.printStackTrace();
        }
        storeAPI.init(new String[]{"store", PIRTestFileName2, PIRTestPath});
        try {
            storeAPI.exe(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertEquals(22, loadAPI.verify(new String[]{"load", PIRTestFilePath2}));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void exe() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        createAPI.init(new String[]{"create", PIREventType, PIRNameForTesting});
        createAPI.exe(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        storeAPI.init(new String[]{"store", PIRTestFileName3, PIRTestPath});
        try {
            storeAPI.exe(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteAPI.init(new String[]{"delete", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
        try {
            loadAPI.verify(new String[]{"load", PIRTestFilePath3});
            loadAPI.init(new String[]{"load", PIRTestFilePath3});
            loadAPI.setOverwriteKeys(PIREventPrimaryKey);
            loadAPI.exe(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, loadAPI.getConflictPIRs().size());
        assertEquals(1, loadAPI.getOriginalPIRs().size());
        assertEquals(PIREventPrimaryKey, loadAPI.getConflictPIRs().get(0)[0]);
        assertEquals(PIRTestStartingTime, loadAPI.getConflictPIRs().get(0)[1]);
        assertEquals(PIRTestAlarmTime, loadAPI.getConflictPIRs().get(0)[2]);
        assertEquals(PIRTestDescription, loadAPI.getConflictPIRs().get(0)[3]);
    }

    @Test
    public void setOverwriteKeys() {
        try {
            loadAPI.init(new String[]{"load", PIRTestFilePath3});
            loadAPI.exe(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadAPI.setOverwriteKeys(PIRTextPrimaryKey);
        loadAPI.setOverwriteKeys(PIREventPrimaryKey);
        assertEquals(2, loadAPI.getOriginalPIRs().size());
        assertEquals(2, loadAPI.getConflictPIRs().size());
    }
}