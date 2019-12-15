package com.coviam.b2bcarpool.helper;

public interface ErrorMessages {

    String EMPTY = "";
    String USER_NOT_EXISTS = "User Not Exists!";
    String SOME_UNEXPECTED_ERROR_OCCUR = "Some unexpected Error occured";
    String NO_RIDES_JOINED = "There no ride requests or rides for your trip!";
    String NO_TRIPS_FOUND = "There are no currently commuters for your search";
    String NO_DATA_AVAILABE = "No AData Available!!";
    String WRONG_PASSWORD = "Wrong Password!";
    String RIDER_ALREADY_JOINED = "Rider Already Joined the ride! Cannot join again!";
    String NO_SEATS_AVAILABLE_IN_TRIP = "there are no seats available in this trip!";
    String TRIP_ALREADY_ENDED = "Sorry! Trip Already Completed, cannot join!¬";
    String SAME_VEHICLE_REGISTRATION_ATTEMPT = "Sorry! Maybe Your trying to register a Vehicle, which is already registered!";
}
