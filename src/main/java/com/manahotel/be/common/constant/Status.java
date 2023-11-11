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

    //Booking status
    public static final String ROOM_EMPTY = "ROOM_EMPTY";
    public static final String ROOM_USING = "ROOM_USING";

    //Clean room
    public static final String CLEAN = "ROOM_CLEAN";
    public static final String UNCLEAN = "ROOM_UNCLEAN";

    //Reservation_type
    public static final String HOURLY = "HOURLY";
    public static final String DAILY = "DAILY";
    public static final String OVERNIGHT = "OVERNIGHT";

    // Reservation and Reservation Detail
    public static final String PENDING = "PENDING"; // For reservation only
    public static final String BOOKING = "BOOKING";
    public static final String CHECK_IN = "CHECK_IN";
    public static final String CHECK_OUT = "CHECK_OUT";
    public static final String DISCARD = "DISCARD"; // For reservation only
    public static final String DONE = "DONE"; // For reservation only

    // Order
    public static final String UNCONFIMRED = "UNCONFIMRED";

    public static final String CONFIMRED = "CONFIMRED";

    public static final String PAID = "PAID";

    public static final String CANCEL_ORDER = "CANCEL_ORDER";



}
