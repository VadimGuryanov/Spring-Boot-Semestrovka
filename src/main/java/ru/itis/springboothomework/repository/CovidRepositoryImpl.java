package ru.itis.springboothomework.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.models.CovidData;
import ru.itis.springboothomework.models.Summary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Repository
public class CovidRepositoryImpl implements CovidRepository {

    private final String URL = "https://disease.sh/v2/countries/ru";

    private Logger logger = LogManager.getLogger("covid-api");

    @Override
    @ExceptionAnnotation
    public CovidData getCovidData() {
        StringBuffer content = null;
        try {
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content.toString());
            return (new ObjectMapper()).readValue(content.toString(), CovidData.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
