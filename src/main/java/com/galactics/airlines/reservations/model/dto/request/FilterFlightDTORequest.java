package com.galactics.airlines.reservations.model.dto.request;

import java.util.Optional;

public class FilterFlightDTORequest {
    private Optional<String> startDate;
    private Optional<String> endDate;
    private Optional<String> departureCity;
    private Optional<String> arrivalCity;
    private Optional<String> departureAirport;
    private Optional<String> arrivalAirport;
    private Optional<String> airplaneModel;

    public Optional<String> getStartDate() {
        return startDate;
    }

    public void setStartDate(Optional<String> startDate) {
        this.startDate = startDate;
    }

    public Optional<String> getEndDate() {
        return endDate;
    }

    public void setEndDate(Optional<String> endDate) {
        this.endDate = endDate;
    }

    public Optional<String> getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(Optional<String> departureCity) {
        this.departureCity = departureCity;
    }

    public Optional<String> getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(Optional<String> arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Optional<String> getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Optional<String> departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Optional<String> getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Optional<String> arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Optional<String> getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(Optional<String> airplaneModel) {
        this.airplaneModel = airplaneModel;
    }
}
