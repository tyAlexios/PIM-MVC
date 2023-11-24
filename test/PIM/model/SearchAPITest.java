package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchAPITest {
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
    SearchAPI searchAPI = new SearchAPI();
    DeleteAPI deleteAPI = new DeleteAPI();

    @Test
    public void verify() {
    }

    @Test
    public void init() {
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey,PIRTestDescription});
        searchAPI.init(new String[]{"search", '"' + PIRTestDescription + '"'});
        searchAPI.exe(new String[]{'"' + PIRTestDescription + '"'});
        assertEquals(1, searchAPI.getRestKeySet().size());
        assertEquals(PIRTextPrimaryKey, searchAPI.getRestKeySet().iterator().next());

        deleteAPI.init(new String[]{"del", PIRTextType, PIRNameForTesting});
        deleteAPI.exe(new String[]{PIRTextPrimaryKey});
    }

    @Test
    public void exe() {
        createAPI.init(new String[]{"new", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey,PIRTestDescription});
        searchAPI.init(new String[]{"search", '"' + PIRTestDescription + '"'});
        searchAPI.exe(new String[]{'"' + PIRTestDescription + '"'});
        assertEquals(1, searchAPI.getRestKeySet().size());
        assertEquals(PIRTextPrimaryKey, searchAPI.getRestKeySet().iterator().next());
        searchAPI.exe(new String[]{"<2023-11-24-11:00"});
        assertEquals(0, searchAPI.getRestKeySet().size());
        try {
            searchAPI.exe(new String[]{"<2023-11-24-11"});
        } catch (Exception e) {
            assertEquals("Invalid time format for targetTime", e.getMessage());
        }
        searchAPI.exe(new String[]{"<11:00"});
        assertEquals(0, searchAPI.getRestKeySet().size());

    }

    @Test
    public void getRestKeySet() {
    }
}