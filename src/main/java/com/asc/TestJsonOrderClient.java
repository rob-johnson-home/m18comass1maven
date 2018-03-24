package com.asc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJsonOrderClient {
    
    private static final Logger LOGGER = Logger.getLogger( TestJsonOrderClient.class.getName() );
    
    public static void main(String[] args) {
        TestJsonOrderClient client = new TestJsonOrderClient();
        client.doPost();
    }

    public void doPost() {
        try {
            URL url = new URL("http://localhost:8000/order");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            String order = "{\"orderDate\":{\"year\":2018,\"month\":5,\"day\":1}"+
                    ",\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false"
                    +",\"orderId\":1023,\"customer\":{\"name\":\"Rob\",\"email\":\"rob@gmail.com\""
                    +",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},"
                    +"\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":1,\"orderId\":1023}]}";
            
            out.writeBytes(order);
            out.flush();


            DataInputStream in = new DataInputStream(con.getInputStream());
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String input = br.readLine();
            LOGGER.log( Level.FINE, "Response : " + input );
            in.close();
            out.close();
            LOGGER.log( Level.FINE, "done");
            
        } catch (Exception ex) {
            LOGGER.log( Level.SEVERE, "Failed : " + ex);
        }
    }

}
