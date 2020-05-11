package com.bridgelabz.service;

public class CliniqueManagementFactory {

    //    TO GET SERVICE CLASS OBJECT
    public static CliniqueManagement createServiceObject() {
        return new CliniqueManagementService();
    }
}
