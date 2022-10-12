/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.skiel.latih_02_hellogui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Amiru
 */
public class DicodingBookshelfAPI {    
    public static JSONObject getBooks(String name) {
        try {
            URL url;
            
            if (name != "") {
                url = new URL("https://dicoding-be-bookshelfapi.herokuapp.com/books?name=" + name);
            } else {
                url = new URL("https://dicoding-be-bookshelfapi.herokuapp.com/");
            }
            
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code : " + responseCode);
            
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream())
                );
                
                String inputLine;
                StringBuffer response = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                in.close();
                
                JSONObject responseJson = new JSONObject(response.toString());
                
                return responseJson;
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException ex) {
            Logger.getLogger(DicodingBookshelfAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JSONObject response = new JSONObject();
        response.put("status", "fail");
        response.put("message", "Client Error");
        
        return response;
    }
}
