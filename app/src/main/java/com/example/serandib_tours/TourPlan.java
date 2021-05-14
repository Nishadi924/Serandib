package com.example.serandib_tours;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TourPlan implements Parcelable {
        private int id;
        private String destination;
        private String accommodation;
        private String date ;
        private String otherPlaces;
        private String noOfDays;
        private String state = "Sheduled";

    public TourPlan() {

    }

    public TourPlan(String destination, String otherPlaces ,String accommodation, String date, String noOfDays) {
            this.destination = destination;
            this.otherPlaces = otherPlaces;
            this.accommodation = accommodation;
            this.date = date;
        this.noOfDays = noOfDays;
        }

    public TourPlan(String destination,String otherPlaces, String accommodation, String noOfDays,String  date, String state) {
        this.destination = destination;
        this.otherPlaces = otherPlaces;
        this.accommodation = accommodation;
        this.date = date;
        this.noOfDays = noOfDays;
        this.state = state;
    }

    protected TourPlan(Parcel in) {
        id = in.readInt();
        destination = in.readString();
        accommodation = in.readString();
        date = in.readString();
       // otherPlaces = in.createStringArrayList();
        otherPlaces = in.readString();
        noOfDays = in.readString();
        state = in.readString();
    }

    public static final Creator<TourPlan> CREATOR = new Creator<TourPlan>() {
        @Override
        public TourPlan createFromParcel(Parcel in) {
            return new TourPlan(in);
        }

        @Override
        public TourPlan[] newArray(int size) {
            return new TourPlan[size];
        }
    };

    public void setDestination(String destination) {
            this.destination = destination;
        }

        public void setAccommodation(String accommodation) {
            this.accommodation = accommodation;
        }

        public void setDate(String date) {
            this.date = date;
        }

    public String getId() {
        return Integer.toString(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoOfDays(String noOfDays) {
            this.noOfDays = noOfDays;
        }

    public void setOtherPlaces(String otherPlaces) {
        this.otherPlaces = otherPlaces;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDestination() {
            return destination;
        }

        public String getAccommodation() {
            return accommodation;
        }

        public String getDate() {
            return date;
        }

        public String getOtherPlaces() {
            return otherPlaces;
        }

        public String getNoOfDays() {
            return noOfDays;
        }

        public String getState() {
        return state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(destination);
        dest.writeString(accommodation);
        dest.writeString(date);
        //dest.writeStringList(otherPlaces);
        dest.writeString(otherPlaces);
        dest.writeString(noOfDays);
        dest.writeString(state);
    }
}

