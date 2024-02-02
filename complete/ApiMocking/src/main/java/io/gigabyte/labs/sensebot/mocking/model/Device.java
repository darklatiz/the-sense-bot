package io.gigabyte.labs.sensebot.mocking.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Device {
    private String deviceId;
    private String deviceType;
    private Location location;
    private Metadata metadata;

    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Location {
        private double latitude;
        private double longitude;

        // Constructors, getters, and setters are omitted
    }

    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Metadata {
        private String firmwareVersion;
        private String installationDate;
        private String manufacturer;
        private String model;
        private String operationalStatus;
        private int batteryLevel;

        // Constructors, getters, and setters are omitted
    }
}
