package io.gigabyte.labs.sensebot.common.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "iot_device")
public class IoTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String deviceType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "iot_device_id")
    private List<Metric> metrics;

    @Embedded
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata_id", referencedColumnName = "id")
    private Metadata metadata;

    // Standard getters and setters

    @Entity
    public static class Metric {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String metricType;
        private double metricValue;
        private String unit;
        @Temporal(TemporalType.TIMESTAMP)
        private Date timestamp;

        // Standard getters and setters
    }

    @Embeddable
    public static class Location {
        private double latitude;
        private double longitude;

        // Standard getters and setters
    }

    @Entity
    public static class Metadata {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firmwareVersion;
        private int batteryLevel;
        private String connectivityType;
        @Temporal(TemporalType.DATE)
        private Date lastMaintenanceDate;
        @Temporal(TemporalType.DATE)
        private Date installationDate;
        private String manufacturer;
        private String model;
        private String serialNumber;
        private String locationDescription;
        private String operationalStatus;
        private String securityStatus;
        private String ipAddress;
        private String macAddress;
        private String uptime;
        private String timeZone;

        @Embedded
        private SoftwareUpdates softwareUpdates;

        @Embedded
        private NetworkQuality networkQuality;

        @Embedded
        private Environment environment;

        @ElementCollection
        private List<String> certifications;

        @ElementCollection
        private List<String> capabilities;

        @ElementCollection
        private List<String> warnings;

        private String notes;

        // Standard getters and setters

        @Embeddable
        public static class SoftwareUpdates {
            private boolean available;
            @Temporal(TemporalType.TIMESTAMP)
            private Date lastUpdateCheck;
            private String updatePolicy;

            // Standard getters and setters
        }

        @Embeddable
        public static class NetworkQuality {
            private String signalStrength;
            private String transmissionRate;
            private String connectionUptime;

            // Standard getters and setters
        }

        @Embeddable
        public static class Environment {
            private String operatingTemperatureRange;
            private String recommendedHumidityRange;

            // Standard getters and setters
        }
    }

    // Additional standard getters and setters for IoTDevice class
}
