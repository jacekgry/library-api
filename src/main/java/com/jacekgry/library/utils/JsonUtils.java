package com.jacekgry.cognifide.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacekgry.cognifide.model.Library;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JsonUtils {

    @Value("${app.datasource}")
    private String datasource;

    ObjectMapper mapper = new ObjectMapper();

    public Library encodeJson() {

        Library library = new Library();
        try {
            library = mapper.readerFor(Library.class).readValue(new ClassPathResource(datasource).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return library;
    }


    public static Date tryToParseDate(String dateString) {
        List<String> patterns = new ArrayList<>();
        patterns.add("yyyy-MM-dd");
        patterns.add("yyyy");
        for (String pattern : patterns) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                Date date = df.parse(dateString);
                return date;
            } catch (Exception e) {
            }

        }
        return null;
    }

}
