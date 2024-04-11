package com.galactics.airlines.reservations.model.dto.request;

public class FlightDTORequest {
    private Long flightId;
    private String departureCity;
    private String arrivalCity;
    private String departureDateTime;
    private String arrivalDateTime;
    private AirportDTORequest departureAirport;
    private AirportDTORequest arrivalAirport;
    private AirplaneDTORequest airplane;
    private int numberOfSeats;

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public AirportDTORequest getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportDTORequest departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportDTORequest getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportDTORequest arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public AirplaneDTORequest getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneDTORequest airplane) {
        this.airplane = airplane;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
}
