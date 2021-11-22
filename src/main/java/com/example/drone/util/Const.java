package com.example.drone.util;

import java.util.List;

public class Const {

    public enum DRONE_STATE{
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;
    }

    public static final List<String> DRONE_MODEL = List.of("Lightweight, Middleweight, Cruiserweight, Heavyweight");
    public static final String FAILED = "Failed";
    public static final String SUCCESSFUL = "Successful";

    // 00 -> Successful, 01 -> Failed
    public static final String[] RESPONSECODE = "00,01".split(",");
}
