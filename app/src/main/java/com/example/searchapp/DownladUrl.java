package com.example.searchapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//retrieve the data from url
public class DownladUrl {

    public String ReadTheUrl(String placeURL) throws IOException
    {
        String Data = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(placeURL);//created the Url
            httpURLConnection = (HttpURLConnection) url.openConnection();//open the connection
            httpURLConnection.connect();// connect

            //read the data from URL
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            String line = "";

            //Read each line one by one
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            Data = stringBuffer.toString();
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                inputStream.close();
                httpURLConnection.disconnect();
        }
        return Data;


    }
}