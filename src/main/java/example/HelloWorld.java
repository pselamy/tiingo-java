package example;

import com.google.inject.Guice;

class HelloWorld {
    static void run(HelloWorldModule helloWorldModule) {
        HelloWorldLogger helloWorldLogger = Guice
                .createInjector(helloWorldModule)
                .getInstance(HelloWorldLogger.class);
        helloWorldLogger.logMessage("Hello world!");
    }

    public static void main(String[] args) {
        run(new HelloWorldModule());
    }
}
