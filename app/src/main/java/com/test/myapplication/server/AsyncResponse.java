package com.test.myapplication.server;

/**
 * Created by Alon on 06/01/2016.
 * An interface to be implemented by all classes which need to preform asynchronous server calls.
 */
public interface AsyncResponse {
    /**
     * Handle the result of the asynchronous server call.
     * @param result A asynchronous result object
     */
    void handleResult(AsyncResult result);
}