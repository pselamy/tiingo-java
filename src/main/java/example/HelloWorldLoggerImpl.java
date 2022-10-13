package example;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

class HelloWorldLoggerImpl implements HelloWorldLogger {
    private final MessageRecorder messageRecorder;

    @Inject
    HelloWorldLoggerImpl(MessageRecorder messageRecorder) {
        this.messageRecorder = messageRecorder;
    }

    @Override
    public void logMessage(String message) {
        messageRecorder.loggedMessages.add(message);
    }

    @Override
    public ImmutableList<String> loggedMessages() {
        return messageRecorder.loggedMessages.build();
    }

    private static final class MessageRecorder {
        private final ImmutableList.Builder<String> loggedMessages;

        @Inject
        MessageRecorder() {
            this.loggedMessages = ImmutableList.builder();
        }
    }
}
