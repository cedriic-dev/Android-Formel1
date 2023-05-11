package at.ac.htlperg.viewmodeldemo.model;

import java.util.List;

public class DriverTable {
    private String season;
    private List<Driver> Drivers;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<Driver> getDrivers() {
        return Drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        Drivers = drivers;
    }
}