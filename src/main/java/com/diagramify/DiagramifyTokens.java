package com.diagramify;

import com.diagramify.model.Http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.diagramify.Constants.*;

public final class DiagramifyTokens {

    static final String DIAGRAMIFY_TOKEN_EXTRACT_PATTERN = "::<(.*?)>::";
    static Map<String, Object> tokenMap = null;

    public static String extractToken(String input) {
        Matcher matcher = Pattern.compile(DIAGRAMIFY_TOKEN_EXTRACT_PATTERN).matcher(input);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    public static Map<String, Object> loadDefaultTokens() {

        if (tokenMap != null) {
            return tokenMap;
        }

        tokenMap = new HashMap<String, Object>();

        //expose local
        tokenMap.put(LOCAL_EXPOSE, "false");

        //cloud tokens
        tokenMap.put(CLOUD_SERVER_EXPOSE, "false");
        tokenMap.put(CLOUD_SERVER_ACCOUNT_ID, null);
        tokenMap.put(CLOUD_SERVER_ACCOUNT_USER, null);
        tokenMap.put(CLOUD_SERVER_ACCOUNT_SECRET, null);

        //application tokens
        tokenMap.put(APPLICATION_NAME, null);
        tokenMap.put(APPLICATION_PORT, null);
        tokenMap.put(APPLICATION_CONTEXT_PATH, null);
        tokenMap.put(APPLICATION_GROUP, null);
        tokenMap.put(APPLICATION_HOST, null);
        tokenMap.put(APPLICATION_ENVIRONMENT, null);

        //HTTP Tokens
        tokenMap.put(HTTP, null);

        //DB tokens
        tokenMap.put(DATABASE, null);

        //JMS tokens
        tokenMap.put(JMS, null);

        return tokenMap;
    }

    public static void updateToken(String token, String tokenValue) {

        //Cloud tokens
        if (token.equals(CLOUD_SERVER_EXPOSE)) {
            tokenMap.replace(CLOUD_SERVER_EXPOSE, tokenValue);
        } else if (token.equals(CLOUD_SERVER_ACCOUNT_ID)) {
            tokenMap.replace(CLOUD_SERVER_ACCOUNT_ID, tokenValue);
        } else if (token.equals(CLOUD_SERVER_ACCOUNT_USER)) {
            tokenMap.replace(CLOUD_SERVER_ACCOUNT_USER, tokenValue);
        } else if (token.equals(CLOUD_SERVER_ACCOUNT_SECRET)) {
            tokenMap.replace(CLOUD_SERVER_ACCOUNT_SECRET, tokenValue);
        }

        //Application tokens
        else if (token.equals(APPLICATION_NAME)) {
            tokenMap.replace(APPLICATION_NAME, tokenValue);
        } else if (token.equals(APPLICATION_PORT)) {
            tokenMap.replace(APPLICATION_PORT, tokenValue);
        } else if (token.equals(APPLICATION_CONTEXT_PATH)) {
            tokenMap.replace(APPLICATION_CONTEXT_PATH, tokenValue);
        } else if (token.equals(APPLICATION_GROUP)) {
            tokenMap.replace(APPLICATION_GROUP, tokenValue);
        } else if (token.equals(APPLICATION_HOST)) {
            tokenMap.replace(APPLICATION_HOST, tokenValue);
        } else if (token.equals(APPLICATION_ENVIRONMENT)) {
            tokenMap.replace(APPLICATION_ENVIRONMENT, tokenValue);
        }

        //HTTP tokens
        else if (token.equals(HTTP_OUTBOUND)) {
            addHttpInOutbound(tokenValue, true);
        } else if (token.equals(HTTP_INBOUND)) {
            addHttpInOutbound(tokenValue, false);
        }

        //JMS tokens


    }

    private static void addHttpInOutbound(String tokenValue, boolean isOutboundType) {

        Object object = tokenMap.get(HTTP); // get diagramify.http value from tokens

        if (object != null) { // if object is not null, then must be an HTTP type.
            addHttpEndpoint((Http) object, tokenValue, isOutboundType);
        } else { // else object is null
            Http http = new Http();
            tokenMap.replace(HTTP,http);
            addHttpEndpoint(http, tokenValue, isOutboundType);
        }
    }

    private static void addHttpEndpoint(Http http, String tokeValue, boolean isOutBound) {

        List<String> bound = isOutBound ? http.getOutbound() : http.getInbound();

        if (bound != null) { // if inbound or outbound is not null.

            if (isOutBound) {
                http.getOutbound().add(tokeValue);// add token value in outbound list.
            } else {
                http.getInbound().add(tokeValue); // add token value in inbound list.
            }
        } else { //else inbound or outbound is null.
            bound = new ArrayList<String>();
            bound.add(tokeValue);
            if(isOutBound){http.setOutbound(bound);}else{http.setInbound(bound);}
        }

    }
}
