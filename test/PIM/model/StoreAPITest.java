package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreAPITest {
    private final String PIRNameForTesting = "test";
    private final String PIRTextType = "txt";
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + PIRNameForTesting;
    private final String PIRTestDescription = "Test.";
    private final String PIRTestPath = System.getProperty("user.dir");
    private final String PIRTestFileName = "test.pim";
    private final String PIRTestFileName2 = "test2.pim";
    CreateAPI createAPI = new CreateAPI();
    StoreAPI storeAPI = new StoreAPI();


    @Test
    public void exe() {
        assertEquals(13, storeAPI.verify(new String[]{"store", PIRTestFileName, PIRTestPath}));
        createAPI.init(new String[]{"create", PIRTextType, PIRNameForTesting});
        createAPI.exe(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(0, storeAPI.verify(new String[]{"store", PIRTestFileName, PIRTestPath}));
        storeAPI.init(new String[]{"store", PIRTestFileName, PIRTestPath});
        try {
            storeAPI.exe(null);
            assertEquals(21, storeAPI.verify(new String[]{"store", PIRTestFileName, PIRTestPath}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exe2() {
        storeAPI.init(new String[]{"store", PIRTestFileName2});
        assertEquals(0, storeAPI.verify(new String[]{"store", PIRTestFileName2}));
        try {
            storeAPI.exe(null);
            assertEquals(21, storeAPI.verify(new String[]{"store", PIRTestFileName2}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}