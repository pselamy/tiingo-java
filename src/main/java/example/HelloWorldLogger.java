package example;

import com.google.common.collect.ImmutableList;

interface HelloWorldLogger {
    void logMessage(String message);

    ImmutableList<String> loggedMessages();
}
