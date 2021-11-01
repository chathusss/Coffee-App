package com.cbl.cofees.EVENTMANAGMENT.passed;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class EventLoadVendos {
    ArrayList<JsonObject> sendinglist;

    public ArrayList<JsonObject> getSendinglist() {
        return sendinglist;
    }

    public void setSendinglist(ArrayList<JsonObject> sendinglist) {
        this.sendinglist = sendinglist;
    }
}
