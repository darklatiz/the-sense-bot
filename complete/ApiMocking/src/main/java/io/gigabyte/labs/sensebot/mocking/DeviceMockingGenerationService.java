package io.gigabyte.labs.sensebot.mocking;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.gigabyte.labs.sensebot.mocking.model.ApiResponse;
import io.gigabyte.labs.sensebot.mocking.model.Device;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class DeviceMockingGenerationService {

    private DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
    private static final Faker theFaker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());
    private static final List<String> deviceTypeList = new ArrayList<>();
    private static final Random random = new Random();
    private ObjectMapper theObjMapper = new ObjectMapper();

    public List<Device> generateRandomDevices(Integer numberOfDevices) {
        if (Objects.isNull(numberOfDevices) || numberOfDevices <= 0) {
            numberOfDevices = 10;
        }

        return IntStream.range(0, numberOfDevices)
          .mapToObj(operand -> generateRandomDevice())
          .toList();


    }

    public Device generateRandomDevice() {
        Device.DeviceBuilder deviceBuilder = Device.builder();
        deviceBuilder.deviceId(theFaker.internet().macAddress());
        deviceBuilder.deviceType(getRandomValue(getDeviceTypeList()));
        deviceBuilder.location(Device.Location.builder()
          .latitude(Double.parseDouble(theFaker.address().latitude()))
          .longitude(Double.parseDouble(theFaker.address().longitude()))
          .build());
        deviceBuilder.metadata(Device.Metadata.builder()
          .batteryLevel(theFaker.number().numberBetween(0, 100))
          .firmwareVersion(fakeValuesService.bothify("#.###.####"))
          .installationDate(isoFormatter.format(LocalDateTime.now()))
          .manufacturer(theFaker.company().name())
            .model(fakeValuesService.bothify("???-####"))
            .operationalStatus("active")
          .build());
        return deviceBuilder.build();
    }

    public List<String> getDeviceTypeList() {
        if (ObjectUtils.isEmpty(deviceTypeList)) {
            deviceTypeList.add("Humidity_Sensor");
            deviceTypeList.add("Air_Quality_Sensor");
            deviceTypeList.add("Water_Quality_Sensor");
            deviceTypeList.add("Accelerometer");
            deviceTypeList.add("Gyroscope");
            deviceTypeList.add("GPS");
            deviceTypeList.add("Fitness_Tracker");
            deviceTypeList.add("Smartwatch");
            deviceTypeList.add("Thermostat");
            deviceTypeList.add("Medical Monitoring");

        }
        return deviceTypeList;
    }

    public String fromObj2Json(ApiResponse theResponse) {
        try {
            return theObjMapper.writeValueAsString(theResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse getDevicesApi() {
        List<Device> devices = generateRandomDevices(random.nextInt(3, 20));
        return ApiResponse.builder()
          .devices(devices)
          .status(200)
          .build();
    }

    public String getRandomValue(List<String> options) {

        // Generate a random index within the bounds of the list
        int randomIndex = random.nextInt(options.size());

        // Retrieve the randomly selected option
        return options.get(randomIndex);
    }

    public static void main(String[] args) {
        DeviceMockingGenerationService deviceMockingGenerationService = new DeviceMockingGenerationService();
        System.out.println(deviceMockingGenerationService.generateRandomDevices(10));
    }

}
