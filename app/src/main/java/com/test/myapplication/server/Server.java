package com.test.myapplication.server;

/**
 * Created by Alon on 06/01/2016.
 */
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.myapplication.db.User;
import com.test.myapplication.db.UserContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class implementation of all valid server calls.
 */
public class Server {

    /**
     * Server URL
     */
    private static final String server = "https://api.dapulse.com/v1/updates.json?page=1&per_page=20&api_key=eebf992d5327869b8e77139c1506e2f7";

    public static final int SERVER_ACTION_GET_DATA = 0;

    protected static final List<User> getData() throws IOException {
        boolean success = true;
        String jsonResponse = HTTPHandler.getRequest(server);
        if (checkJSONError(jsonResponse)){
            throw new IOException();
        }
        UserContainer[] userContainer = new Gson().fromJson(jsonResponse, UserContainer[].class);
        List<User> users = new ArrayList<>();
        for (UserContainer uc : userContainer){
            users.add(uc.getUser());
        }
        return users;
    }

    /**
     * Check JSON validity
     * @param json A JSON object as a string
     * @return The if the JSON is valid
     */
    private static final boolean checkJSONError(String json){
        return (json == null || json.equalsIgnoreCase("{}"));
    }

}
