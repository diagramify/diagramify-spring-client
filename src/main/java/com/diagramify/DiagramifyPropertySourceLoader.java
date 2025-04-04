package com.diagramify;

import com.diagramify.util.TokenUtils;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiagramifyPropertySourceLoader implements PropertySourceLoader {
    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {

        final String diagramify_TOKEN_PATTERN = "::<.*?>::";

        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        Map<String, Object> cleanedProperties = new HashMap<>();

        for (String key : properties.stringPropertyNames()) {

            String rawValue = properties.getProperty(key).trim();
            String diagramifyToken = TokenUtils.extractToken(rawValue);
            String cleanedValue = rawValue.replaceAll(diagramify_TOKEN_PATTERN, "").trim();
            System.out.println(diagramifyToken);
            cleanedProperties.put(key, cleanedValue);
        }

        return Collections.singletonList(new MapPropertySource(name != null ? name : "diagramifyProperties", cleanedProperties));
    }

    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties"};
    }

}