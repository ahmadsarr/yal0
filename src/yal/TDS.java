package yal;

import java.util.HashMap;

public final class TDS {
    private static volatile TDS instance=null;
    private HashMap<String,Integer>idf;
    private TDS()
    {
     super();
    }
    public static  TDS getInstance()
    {
        if(instance==null) {
            synchronized (TDS.class) {
                if(instance==null)
                     instance=new TDS();
            }
        }
        return instance;
    }
    public void ajouter()
    {

    }
}
