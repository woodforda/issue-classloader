package org.woody.issue.classloader;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class IssueClassLoaderApplication {

    public static void main(final String[] args) {
        final ClassLoader loader = Thread.currentThread()
                                         .getContextClassLoader();
        final InputStream inputStream = loader.getResourceAsStream("C:\\Users\\woodford/.keystore");
        if (inputStream == null) {
            System.out.println("The resource is null");
        }
    }

}
