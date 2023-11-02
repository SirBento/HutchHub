package com.example.hutchhub.Models;

public class GrowthWeight {
    String name,DOB,
            FirstWeight,FirstHeight,FirstDate,
            SecondWeight,SecondHeight,SecondDate,
            ThirdWeight,ThirdHeight,ThirdDate;

    public GrowthWeight() {
    }

    public GrowthWeight(String name, String DOB, String firstWeight, String firstHeight, String firstDate, String secondWeight, String secondHeight, String secondDate, String thirdWeight, String thirdHeight, String thirdDate) {
        this.name = name;
        this.DOB = DOB;
        FirstWeight = firstWeight;
        FirstHeight = firstHeight;
        FirstDate = firstDate;
        SecondWeight = secondWeight;
        SecondHeight = secondHeight;
        SecondDate = secondDate;
        ThirdWeight = thirdWeight;
        ThirdHeight = thirdHeight;
        ThirdDate = thirdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getFirstWeight() {
        return FirstWeight;
    }

    public void setFirstWeight(String firstWeight) {
        FirstWeight = firstWeight;
    }

    public String getFirstHeight() {
        return FirstHeight;
    }

    public void setFirstHeight(String firstHeight) {
        FirstHeight = firstHeight;
    }

    public String getFirstDate() {
        return FirstDate;
    }

    public void setFirstDate(String firstDate) {
        FirstDate = firstDate;
    }

    public String getSecondWeight() {
        return SecondWeight;
    }

    public void setSecondWeight(String secondWeight) {
        SecondWeight = secondWeight;
    }

    public String getSecondHeight() {
        return SecondHeight;
    }

    public void setSecondHeight(String secondHeight) {
        SecondHeight = secondHeight;
    }

    public String getSecondDate() {
        return SecondDate;
    }

    public void setSecondDate(String secondDate) {
        SecondDate = secondDate;
    }

    public String getThirdWeight() {
        return ThirdWeight;
    }

    public void setThirdWeight(String thirdWeight) {
        ThirdWeight = thirdWeight;
    }

    public String getThirdHeight() {
        return ThirdHeight;
    }

    public void setThirdHeight(String thirdHeight) {
        ThirdHeight = thirdHeight;
    }

    public String getThirdDate() {
        return ThirdDate;
    }

    public void setThirdDate(String thirdDate) {
        ThirdDate = thirdDate;
    }
}
