package com.example.oopcw.ticketingsystem.validation;

import com.example.oopcw.ticketingsystem.Configuration;
import com.example.oopcw.ticketingsystem.service.ConfigurationService;

import java.io.IOException;

public class HandleFiles {

    public void writeOnGson(Configuration configuration) {
        ConfigurationService configurationService = new ConfigurationService();
        configurationService.writeGson(configuration);
    }

//    public Integer readOnGson() {
//        ConfigurationService configurationService = new ConfigurationService();
//
//        try {
//            configurationService.readGson();
//
//        } catch (IOException e) {
//            System.out.println("Unexpected issue occurred while reading the Gson file ");
//            e.printStackTrace();
//        }
//        return
//    }
}
