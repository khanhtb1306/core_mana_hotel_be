package com.manahotel.be.common.util;

public class IdGenerator {

    public static String generateId(String latestId, String prefix) {
        int lastId = Integer.parseInt(latestId.substring(prefix.length()));
        int nextId = lastId + 1;
        return prefix + String.format("%6d", nextId);
    }
}