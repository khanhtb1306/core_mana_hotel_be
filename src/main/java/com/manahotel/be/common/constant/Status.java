package com.manahotel.be.common.constant;

public class Status {

    // Activate or deactivate
    public static final long ACTIVATE = 1;
    public static final long DEACTIVATE = 2;

    // Inventory checklist
    public static final long CANCEL = 3;
    public static final long TEMPORARY = 4;
    public static final long BALANCE = 5;

    // Delete
    public static final long DELETE = 6;

    //Booking
    public static final String EMPTY = "ROOM_EMPTY";
    public static final String BOOKING = "ROOM_BOOKING";
    public static final String USING = "ROOM_USING";

    //Clean room
    public static final String CLEAN = "ROOM_CLEAN";
    public static final String UNCLEAN = "ROOM_UNCLEAN";

    //Reservation_type
    public static final String HOURLY = "HOURLY";
    public static final String DAILY = "DAILY";
    public static final String OVERNIGHT = "OVERNIGHT";
}
