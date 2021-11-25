package com.example.drone.util;

import java.util.List;

public class Const {

    public enum DRONE_STATE{
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;
    }

    public enum MEDICATION_STATE{
        ONLOAD, OFFLOAD, AVAILABLE
    }

    public static final List<String> DRONE_MODEL = List.of("Lightweight, Middleweight, Cruiserweight, Heavyweight".split(","));
    public static final String FAILED = "Failed";
    public static final String SUCCESSFUL = "Successful";
    public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";
    public static final float REDUCE_BATTERY_LEVEL = 5.00f;
    // 00 -> Successful, 01 -> Failed
    public static final String[] RESPONSECODE = "00,01".split(",");
    public static final float BATTERY_LOW = 25.00f;
}
