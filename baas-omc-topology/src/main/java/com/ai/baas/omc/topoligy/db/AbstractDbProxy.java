package com.ai.baas.omc.topoligy.db;

import java.util.Map;

public abstract class AbstractDbProxy {

    public abstract void loadResource(Map<String,String> config);
    
    abstract Object getConnection();
}
