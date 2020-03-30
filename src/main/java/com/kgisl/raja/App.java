package com.kgisl.raja;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.jackson2.JacksonFactory;

public class App {

    private static HttpRequestFactory REQ_FACTORY;
    private static HttpTransport TRANSPORT;
    private static com.google.api.client.json.JsonFactory JSON_FACTORY = new JacksonFactory();
    public  static  void sendGet(){
        TRANSPORT = new NetHttpTransport();
        REQ_FACTORY = TRANSPORT.createRequestFactory();
        GenericUrl genericUrl = new GenericUrl("https://rajaonlineportal.herokuapp.com/get/1");
        HttpRequest httpRequest;
        try {
            httpRequest = REQ_FACTORY.buildGetRequest(genericUrl);
            HttpResponse httpResponse = httpRequest.execute();
            String s = httpResponse.parseAsString();
            ObjectMapper mapper = new ObjectMapper();
            Student student = mapper.readValue(s, Student.class);
            System.out.println(student.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public  static  void sendPost(){
        TRANSPORT = new NetHttpTransport();
        REQ_FACTORY = TRANSPORT.createRequestFactory();
        GenericUrl genericUrl = new GenericUrl("https://rajaonlineportal.herokuapp.com/add");
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("id",12);
        data.put("name", "somename");
        HttpContent httpContent = new JsonHttpContent(JSON_FACTORY, data);
        try {
            REQ_FACTORY.buildPostRequest(genericUrl, httpContent).execute();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public  static  void sendGetAll(){
        TRANSPORT = new NetHttpTransport();
        REQ_FACTORY = TRANSPORT.createRequestFactory();
        GenericUrl genericUrl = new GenericUrl("https://rajaonlineportal.herokuapp.com/get/all");
        HttpRequest httpRequest;
        try {
            httpRequest = REQ_FACTORY.buildGetRequest(genericUrl);
            HttpResponse httpResponse = httpRequest.execute();
            String s = httpResponse.parseAsString();
            ObjectMapper mapper = new ObjectMapper();
            Student[] students = mapper.readValue(s, Student[].class);
            for(Student x:students){
                System.out.println(x.toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //sendGet();
        //sendPost();
        sendGetAll();
    }
}