package com.example.oopcw.ticketingsystem.service;

import com.example.oopcw.ticketingsystem.validation.Validation;
import com.example.oopcw.ticketingsystem.constant.configurationFiles;

import com.example.oopcw.ticketingsystem.Configuration;
import com.example.oopcw.ticketingsystem.validation.HandleFiles;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class ConfigurationService {

    public void writeGson(Configuration configuration) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        File file = new File(configurationFiles.configurationFile);
        Writer writer = new FileWriter(file);
        gson.toJson(configuration, writer);
        writer.close();


    }

    public Configuration readGson() throws IOException {
//        File configFile = new File(configurationFiles.configurationFile);
//
//        if (!configFile.exists()) {
//            System.out.println("Configuration file does not exist");
//        } else {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(configurationFiles.configurationFile));
        Configuration configuration = gson.fromJson(bufferedReader, Configuration.class);
        bufferedReader.close();
        return configuration;
    }
//        return null;
//    }

    public void setConfigurationFile() {
        //Check weather the file is there are not if there pre-assign values to valriables so we can update one record
        Scanner scanner = new Scanner(System.in);
        Configuration configuration = new Configuration();
        HandleFiles writeFiles = new HandleFiles();
        Validation validation = new Validation();

        getConfigurationFile("So Configuration File Found \nCreating New Configuration File");
        // if file is exist it updates the values if not create a new file
        boolean loop = true;
        while (loop) {
            System.out.println("1. Change Max Ticket pool capacity ");
            System.out.println("2. Change Total Ticket capacity for Vendors ");
            System.out.println("3. Change Release rate ");
            System.out.println("4. Change Purchase rate ");
            System.out.println("5. Change all ");
            System.out.println("6. Exit");
            System.out.println("Enter your choice :");
            switch (scanner.nextLine()) {
                case "1":
                    configuration.setMaxTicketCapacity(validation.getValidation(scanner, "Enter Max Ticket pool capacity :"));
                    writeFiles.writeOnGson(configuration);
                    //Update only one TODO
                    break;
                case "2":
                    configuration.setTotalTickets(validation.getValidation(scanner, "Enter Total Ticket capacity for Vendors : "));
                    writeFiles.writeOnGson(configuration);
                    break;
                case "3":
                    configuration.setTicketReleaseRate(validation.getValidation(scanner, "Enter The Release rate : "));
                    writeFiles.writeOnGson(configuration);
                    break;
                case "4":
                    configuration.setCustomerRetrievalRate(validation.getValidation(scanner, "Enter Purchase rate : "));
                    writeFiles.writeOnGson(configuration);
                    break;
                case "5":
                    configuration.setMaxTicketCapacity(validation.getValidation(scanner, "Enter Max Ticket pool capacity :"));
                    configuration.setTotalTickets(validation.getValidation(scanner, "Enter Total Ticket capacity for Vendors : "));
                    configuration.setTicketReleaseRate(validation.getValidation(scanner, "Enter The Release rate : "));
                    configuration.setCustomerRetrievalRate(validation.getValidation(scanner, "Enter Purchase rate : "));
                    writeFiles.writeOnGson(configuration);
                    break;
                case "6":
                    System.out.println("Configuration file updated");
                    System.out.println();
                    loop = false;
                    break;

                default:
                    System.out.println("Invalid option \n Enter a Valid Option \n");


            }
        }
    }

    public void getConfigurationFile(String message) {
        //put this where Starting TODO
        Configuration configuration = new Configuration();
        File configFile = new File(configurationFiles.configurationFile);
        if (!configFile.exists()) {
            System.out.println(message);
            //can use with a custom message
            System.out.println("\n");
        } else {
            try {
                //PreSetting Values from Files
                configuration.setTicketReleaseRate(readGson().getTicketReleaseRate());
                configuration.setMaxTicketCapacity(readGson().getMaxTicketCapacity());
                configuration.setTotalTickets(readGson().getTotalTickets());
                configuration.setCustomerRetrievalRate(readGson().getCustomerRetrievalRate());


            } catch (IOException ioExceptione) {
                System.out.println("An error occurred while reading the configuration file");
                ioExceptione.printStackTrace();
            }
        }
    }

    public void printConfigFile(Configuration configuration) {
        try {
            System.out.println("\n");
            System.out.println("Total Tickets : " + readGson().getTotalTickets());
            System.out.println("Customer Retrieval rate : " + configuration.getCustomerRetrievalRate());
            System.out.println("Max TicketPool Capacity : " + configuration.getMaxTicketCapacity());
            System.out.println("Ticket Relase rate : " + configuration.getTicketReleaseRate());
            System.out.println("\n");

        } catch (IOException ioExceptione) {
            System.out.println("An error occurred while printing the configuration file");
            ioExceptione.printStackTrace();
        }
        ;
    }

}

