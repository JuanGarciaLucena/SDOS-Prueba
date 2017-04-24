package com.juanlucena.sdos.model;

import com.orm.SugarRecord;

public class User extends SugarRecord{

    private int userId, totalHoursAsigned;
    private String username, password, name, surname;
    private boolean isAdministrator, isTechnician, hasProductReplace, hasCharge, hasEnvelop, hasOther;


    public User(){}

    public User(int userId, String username, String password, String name, String surname,
                boolean isAdministrator, boolean isTechnician, boolean hasProductReplace,
                boolean hasCharge, boolean hasEnvelop, boolean hasOther, int totalHoursAsigned){

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isAdministrator = isAdministrator;
        this.isTechnician = isTechnician;
        this.hasProductReplace = hasProductReplace;
        this.hasCharge = hasCharge;
        this.hasEnvelop = hasEnvelop;
        this.hasOther = hasOther;
        this.totalHoursAsigned = totalHoursAsigned;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public boolean isTechnician() {
        return isTechnician;
    }

    public void setTechnician(boolean technician) {
        isTechnician = technician;
    }

    public boolean isHasProductReplace() {
        return hasProductReplace;
    }

    public void setHasProductReplace(boolean hasProductReplace) {
        this.hasProductReplace = hasProductReplace;
    }

    public boolean isHasCharge() {
        return hasCharge;
    }

    public void setHasCharge(boolean hasCharge) {
        this.hasCharge = hasCharge;
    }

    public boolean isHasEnvelop() {
        return hasEnvelop;
    }

    public void setHasEnvelop(boolean hasEnvelop) {
        this.hasEnvelop = hasEnvelop;
    }

    public boolean isHasOther() {
        return hasOther;
    }

    public void setHasOther(boolean hasOther) {
        this.hasOther = hasOther;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTotalHoursAsigned() {
        return totalHoursAsigned;
    }

    public void setTotalHoursAsigned(int totalHoursAsigned) {
        this.totalHoursAsigned = totalHoursAsigned;
    }

    public String toString(){
        return name + " " + surname;
    }
}
