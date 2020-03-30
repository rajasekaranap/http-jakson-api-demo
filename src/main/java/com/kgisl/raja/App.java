package com.kgisl.raja;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

public class App {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        App obj = new App();

        try {
            System.out.println("Testing 1 - Send Http GET request");
            obj.sendGet();
        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://rajaonlineportal.herokuapp.com/get/1");

        // add request headers
        /* request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot"); */

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                ObjectMapper mapper = new ObjectMapper();
                Student student = null;
                try{
                student = mapper.readValue(result, Student.class);
                }
                catch (JsonParseException e) { e.printStackTrace();}
                catch (JsonMappingException e) { e.printStackTrace(); }
                catch (IOException e) { e.printStackTrace(); }
                System.out.println(result);
                System.out.println("Student:"+student.toString());
                System.out.println(student.getId()+100);
            }

        }

    }

}