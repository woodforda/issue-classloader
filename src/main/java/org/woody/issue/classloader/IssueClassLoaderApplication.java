package org.woody.issue.classloader;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.loader.LaunchedURLClassLoader;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@SpringBootApplication
public class IssueClassLoaderApplication {

    public static void main(final String[] args) {
        withCurrentClassLoader("C:\\Users\\woodford/.keystore");
        withUrlClassLoader("C:\\Users\\woodford/.keystore");
        withBootClassLoader("C:\\Users\\woodford/.keystore");
    }

    private static void withCurrentClassLoader(final String location) {
        final ClassLoader classLoader = Thread.currentThread()
                                              .getContextClassLoader();
        loadResource(classLoader, location);
    }

    public static void withUrlClassLoader(final String location) {
        final ClassLoader parent = Thread.currentThread()
                                         .getContextClassLoader()
                                         .getParent();
        final URLClassLoader classLoader = new URLClassLoader(getUrls(), parent);
        loadResource(classLoader, location);
    }

    public static void withBootClassLoader(final String location) {
        final ClassLoader parent = Thread.currentThread()
                                         .getContextClassLoader()
                                         .getParent();
        final LaunchedURLClassLoader classLoader = new LaunchedURLClassLoader(getUrls(), parent);
        loadResource(classLoader, location);
    }

    private static void loadResource(final ClassLoader classLoader,
                                     final String location) {
        try {
            final InputStream inputStream = classLoader.getResourceAsStream(location);
            if (inputStream == null) {
                System.out.println("The resource is null");
            }
        } catch (final Exception exception) {
            System.out.println("Exception thrown for " + location + " - " + exception);
            exception.printStackTrace();
        }
    }

    private static URL[] getUrls() {
        try {
            return new URL[] { new URL("jar:file:./target/issue-class-loader-1.0-SNAPSHOT.jar!/BOOT-INF/classes!/") };
        } catch (final MalformedURLException exception) {
            throw new RuntimeException("Error ", exception);
        }
    }

}
