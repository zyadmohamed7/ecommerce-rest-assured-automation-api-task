package org.example.framework.utils;

import io.restassured.response.Response;
import java.util.concurrent.Callable;

public class RetryHandler {

    public static Response handle(Callable<Response> task, int maxRetries, int retryDelayMs) {
        int currentAttempt = 0;
        Response lastResponse = null;

        while (currentAttempt < maxRetries) {
            try {
                lastResponse = task.call();
                
                if (lastResponse != null && lastResponse.statusCode() < 500) {
                    return lastResponse;
                }
                
                FrameworkLogger.warn("⚠️ API Attempt " + (currentAttempt + 1) + " returned status: " + 
                    (lastResponse != null ? lastResponse.statusCode() : "NULL"));

            } catch (Exception e) {
                FrameworkLogger.warn("⚠️ Exception on attempt " + (currentAttempt + 1) + ": " + e.getMessage());
            }

            currentAttempt++;
            if (currentAttempt < maxRetries) {
                try {
                    Thread.sleep(retryDelayMs);
                } catch (InterruptedException ignored) {}
            }
        }
        return lastResponse;
    }
}
