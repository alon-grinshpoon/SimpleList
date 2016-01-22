package com.test.myapplication.server;

/**
 * Created by Alon on 06/01/2016.
 * An implementation of an result of a asynchronous operation used to parse server responses.
 */

import android.graphics.Bitmap;

import com.test.myapplication.db.User;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AsyncResult {

    private User user;
    private List<User> userList;

    private boolean error = false;
    private String message = "";
    private int statusCode = 200;

    public User getData() {
        return user;
    }

    public void setDataList(List<User> dataList) {
        this.userList = dataList;
    }

    public List<User> getDataList() {
        return userList;
    }

    public void setData(User data) {
        this.user = data;
    }

    public boolean errored() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Parses a caught exception into this result.
     * @param e A caught IO exception
     */
    public void catchException(IOException e){
        this.setError(true);
        this.setMessage(e.getMessage());
        this.setStatusCode(100);
    }
}