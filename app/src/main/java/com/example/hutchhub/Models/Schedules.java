package com.example.hutchhub.Models;

public class Schedules {

    String Morning,Afternoon,Evening;

    public Schedules() {
    }

    public Schedules(String morning, String afternoon, String evening) {
        Morning = morning;
        Afternoon = afternoon;
        Evening = evening;
    }

    public String getMorning() {
        return Morning;
    }

    public void setMorning(String morning) {
        Morning = morning;
    }

    public String getAfternoon() {
        return Afternoon;
    }

    public void setAfternoon(String afternoon) {
        Afternoon = afternoon;
    }

    public String getEvening() {
        return Evening;
    }

    public void setEvening(String evening) {
        Evening = evening;
    }
}
