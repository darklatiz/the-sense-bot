package io.gigabyte.labs.sensebot.mocking.transformer;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformerV2;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import io.gigabyte.labs.sensebot.mocking.DeviceMockingGenerationService;

import java.time.LocalDateTime;

public class DeviceResponseTransformer implements ResponseDefinitionTransformerV2 {
    private DeviceMockingGenerationService deviceMockingGenerationService = new DeviceMockingGenerationService();
    @Override
    public ResponseDefinition transform(ServeEvent serveEvent) {

        return new ResponseDefinitionBuilder()
          .withHeader("Content-Type", "application/json")
          .withHeader("transformed-ts", LocalDateTime.now().toString())
          .withStatus(200)
          .withBody(deviceMockingGenerationService.fromObj2Json(deviceMockingGenerationService.getDevicesApi()))
          .build();
    }

    @Override
    public String getName() {
        return "device-transformer";
    }
}
