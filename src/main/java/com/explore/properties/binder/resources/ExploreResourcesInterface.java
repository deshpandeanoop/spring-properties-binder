package com.explore.properties.binder.resources;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ExploreResourcesInterface {

    private ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        printFileContent();
//        printWebContent();
        printClassPathContent();
    }

    private static void printFileContent() throws IOException {
        Resource resource = new FileSystemResource("/Applications/dummy-data/test.txt");
        System.out.println(resource.getContentAsString(Charset.defaultCharset()));
    }

    private static void printWebContent() throws IOException {
        Resource resource = new UrlResource("https://www.geeksforgeeks.org/url-class-java-examples/");
        System.out.println(resource.getContentAsString(StandardCharsets.UTF_8));
    }

    private static void printClassPathContent() throws IOException {
        Resource resource = new ClassPathResource("application.properties");
        System.out.println(resource.getContentAsString(StandardCharsets.UTF_8));
    }
}
