package com.juanlucena.sdos.model;

import com.orm.SugarRecord;

public class Location_1 extends SugarRecord {

    private String latitude;
    private String human_address;
    private Boolean needs_recoding;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHumanAddress() {
        return human_address;
    }

    public void setHumanAddress(String human_address) {
        this.human_address = human_address;
    }

    public Boolean getNeedsRecoding() {
        return needs_recoding;
    }

    public void setNeedsRecoding(Boolean needs_recoding) {
        this.needs_recoding = needs_recoding;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
