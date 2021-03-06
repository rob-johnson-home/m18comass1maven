/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asc.ui;

import com.asc.control.OrderController;
import com.asc.data.Item;
import com.asc.data.Order;
import com.asc.data.OrderList;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rob
 */
public class WebOrderControlUI implements Runnable, HttpHandler {
    private static final Logger LOGGER = Logger.getLogger( WebOrderControlUI.class.getName() );

    private String url = "http://localhost:8080";
    private int timeout = 360000;
    private Boolean stop = false;

    private Order interpretJson(String orderJSON) {
        Gson g = new Gson();
        Order o = g.fromJson(orderJSON, Order.class);
        return o;
    }

    public void run() {
        try {
            LOGGER.log( Level.FINE, "********   web order service ********");
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/order", this);
            server.setExecutor(null); // creates a default executor
            LOGGER.log( Level.FINE, "starting server");
            server.start();
            LOGGER.log( Level.FINE, "server started");
            // wait to be shut down
            while (!stop) {
                Thread.sleep(1000);
            }
            server.stop(0);

        } catch (IOException ex) {
            LOGGER.log( Level.SEVERE, "IO Exception in server startup.");
            LOGGER.log( Level.SEVERE, "Exception : " + ex);
            System.exit(0);
        } catch (InterruptedException e) {
            // good practice
            Thread.currentThread().interrupt();
            
        }

    }
    
    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        // read input
        LOGGER.log( Level.FINE, "Handling request");
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        LOGGER.log( Level.FINE, sb.toString());
        OrderController oc = new OrderController();
        byte[] response;
        if (oc.newOrder(sb.toString())) {
            LOGGER.log( Level.FINE, "Order Accepted");
            response = "Order Accepted".getBytes();
        } else {
            LOGGER.log( Level.FINE, "Order Rejected");
            response = "Order Rejected".getBytes();
        }

        t.sendResponseHeaders(200, response.length);
        OutputStream os = t.getResponseBody();
        os.write(response);
        os.close();
    }

}
