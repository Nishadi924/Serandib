package com.example.serendibtours;

public class TourDataModelClass {
    private int destId;
    private String location,packageNo,tourGuideName,noOfDaysPerTrip,isAccommodationAvailable,isFoodAvailable;

    public TourDataModelClass(){

    }

    public TourDataModelClass(int destId, String location, String packageNo, String tourGuideName, String noOfDaysPerTrip, String isAccommodationAvailable, String isFoodAvailable) {
        this.destId = destId;
        this.location = location;
        this.packageNo = packageNo;
        this.tourGuideName = tourGuideName;
        this.noOfDaysPerTrip = noOfDaysPerTrip;
        this.isAccommodationAvailable = isAccommodationAvailable;
        this.isFoodAvailable = isFoodAvailable;
    }

    public TourDataModelClass(String location, String packageNo, String tourGuideName, String noOfDaysPerTrip, String isAccommodationAvailable, String isFoodAvailable) {
        this.location = location;
        this.packageNo = packageNo;
        this.tourGuideName = tourGuideName;
        this.noOfDaysPerTrip = noOfDaysPerTrip;
        this.isAccommodationAvailable = isAccommodationAvailable;
        this.isFoodAvailable = isFoodAvailable;
    }

    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getTourGuideName() {
        return tourGuideName;
    }

    public void setTourGuideName(String tourGuideName) {
        this.tourGuideName = tourGuideName;
    }

    public String getNoOfDaysPerTrip() {
        return noOfDaysPerTrip;
    }

    public void setNoOfDaysPerTrip(String noOfDaysPerTrip) {
        this.noOfDaysPerTrip = noOfDaysPerTrip;
    }

    public String getIsAccommodationAvailable() {
        return isAccommodationAvailable;
    }

    public void setIsAccommodationAvailable(String isAccommodationAvailable) {
        this.isAccommodationAvailable = isAccommodationAvailable;
    }

    public String getIsFoodAvailable() {
        return isFoodAvailable;
    }

    public void setIsFoodAvailable(String isFoodAvailable) {
        this.isFoodAvailable = isFoodAvailable;
    }
}
