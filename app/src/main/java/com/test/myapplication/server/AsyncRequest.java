package com.test.myapplication.server;

/**
 * Created by Alon on 06/01/2016.
 */
import android.os.AsyncTask;

import com.test.myapplication.db.User;

import java.io.IOException;
import java.util.List;

/**
 * An implementation of an asynchronous operation used to create server calls.
 */
public class AsyncRequest extends AsyncTask<Object, Void, AsyncResult> {

    /* The calling class object for this asynchronous request */
    AsyncResponse sendResponseTo;

    /* Constructor */
    public AsyncRequest(AsyncResponse sendResponseTo){
        this.sendResponseTo = sendResponseTo;
    }

    @Override
    protected AsyncResult doInBackground(Object... objects) {

        // Define result
        AsyncResult result = new AsyncResult();

        // Get server action (Always the first parameter)
        int serverAction = (int) objects[0];

        // Define parameters
        User user;

        // Perform server action
        switch (serverAction){

            case Server.SERVER_ACTION_GET_DATA:
                // Run server action
                List<User> usersList = null;
                try {
                    usersList = Server.getData();
                    // Configure result
                    result.setDataList(usersList);
                } catch (IOException e) {
                    // Configure result as error
                    result.catchException(e);
                }
                break;
            default:
                break;
        }

        return result;
    }

    protected void onPostExecute(AsyncResult result) {
        sendResponseTo.handleResult(result);
    }
}