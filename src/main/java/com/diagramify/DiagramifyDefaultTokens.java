package com.diagramify;

import java.util.HashMap;
import java.util.Map;

public class DiagramifyDefaultTokens {

    private static Map<String,String> tokens;

    void loadDefaultTokens(){
        
        tokens = new HashMap<String,String>();
        
        //cloud tokens
        tokens.put("diagramify.cloud.server.expose","false");
        tokens.put("diagramify.cloud.account.id",null);
        tokens.put("diagramify.cloud.server.user",null);
        tokens.put("diagramify.cloud.server.secret",null);
        
    }

  

}
