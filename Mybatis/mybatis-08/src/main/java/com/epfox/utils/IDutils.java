package com.epfox.utils;

import java.util.UUID;

public class IDutils {
    public static String getID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {

    }


}
