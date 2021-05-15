package framework.po;

import java.util.HashMap;

public class POStore {
    private static POStore store;

    //保存所有的po， poName: POBasePage实例
    private HashMap<String, POBasePage> poStore=new HashMap<>();

    public static POStore getInstance() {
        if (store == null) {
            store = new POStore();
        }
        return store;
    }

    public void setPO(String name, POBasePage page){
        poStore.put(name, page);
    }
    public POBasePage getPO(String name){
        return poStore.get(name);
    }
}
