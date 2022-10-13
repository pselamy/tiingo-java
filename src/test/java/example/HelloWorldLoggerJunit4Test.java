package example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class HelloWorldLoggerJunit4Test {
    @Inject
    private HelloWorldLogger helloWorldLogger;

    @Before
    public void setup() {
        Guice.createInjector(new HelloWorldModule()).injectMembers(this);
    }

    @Test
    public void testLogMessage() {
        // Arrange
        String message = "Hello there!";

        // Act
        helloWorldLogger.logMessage(message);

        // Assert
        assertThat(helloWorldLogger.loggedMessages()).containsExactly(message);
    }
}
