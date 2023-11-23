package PIM.controller;

import java.util.HashMap;



public class OperationLib {
    public static final HashMap<String, OperationProcess> operationMap = new HashMap<>();

    static {
        operationMap.put("create", new CreateProcess());
        operationMap.put("del", new DeleteProcess());
        operationMap.put("modify", new ModifyProcess());
        operationMap.put("search", new SearchProcess());
        operationMap.put("print", new PrintProcess());
        operationMap.put("load", new LoadProcess());
        operationMap.put("store", new StoreProcess());
    }

    public static boolean containOperation(String command)
    {
        return operationMap.containsKey(command);
    }
    public static OperationProcess getOperation(String command)
    {
        return operationMap.get(command);
    }
}