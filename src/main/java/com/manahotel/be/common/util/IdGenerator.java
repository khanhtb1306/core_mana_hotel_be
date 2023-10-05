package com.manahotel.be.common.util;

public class IdGenerator {

    public static String generateId(String latestId, String prefix) {
        if(latestId == null) {
            return prefix + "000001";
        }
        int lastId = Integer.parseInt(latestId.substring(prefix.length()));
        int nextId = lastId + 1;
        return prefix + String.format("%06d", nextId);
    }
}