package com.juanlucena.sdos.model;

import com.orm.SugarRecord;

public class WebserviceObject extends SugarRecord{

    private String zipcode;
    private Location_1 location_1;
    private String item;
    private String business;
    private String farmer_id;
    private String category;
    private String l;
    private String farm_name;
    private String phone1;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Location_1 getLocation1() {
        return location_1;
    }

    public void setLocation1(Location_1 location_1) {
        this.location_1 = location_1;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getFarmerId() {
        return farmer_id;
    }

    public void setFarmerId(String farmer_id) {
        this.farmer_id = farmer_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getFarmName() {
        return farm_name;
    }

    public void setFarmName(String farm_name) {
        this.farm_name = farm_name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
}
