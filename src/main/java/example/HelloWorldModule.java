package example;

import com.google.inject.AbstractModule;

public class HelloWorldModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HelloWorldLogger.class).to(HelloWorldLoggerImpl.class);
    }
}
