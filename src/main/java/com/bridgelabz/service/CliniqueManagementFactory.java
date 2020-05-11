package com.bridgelabz.service;

public class CliniqueManagementFactory {

    public static CliniqueManagement createServiceObject(){
        return new CliniqueManagementService();
    }
}
