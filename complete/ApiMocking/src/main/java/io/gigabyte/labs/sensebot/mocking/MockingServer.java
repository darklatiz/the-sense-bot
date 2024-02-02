package io.gigabyte.labs.sensebot.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.gigabyte.labs.sensebot.mocking.transformer.DeviceResponseTransformer;

public class MockingServer {
    public static void main(String[] args) {
        WireMockConfiguration configuration = WireMockConfiguration.options()
          .port(8084)
          .extensions(new DeviceResponseTransformer());
        WireMockServer wireMockServer = new WireMockServer(configuration);
        DeviceMockingGenerationService deviceMockingGenerationService = new DeviceMockingGenerationService();
        wireMockServer.start();

        wireMockServer.stubFor(
          WireMock.get(
            WireMock.urlEqualTo("/iot/devices")
          ).willReturn(
            WireMock.aResponse()
              .withTransformers("device-transformer")
          )
        );
        Runtime.getRuntime().addShutdownHook(new Thread(wireMockServer::stop));
    }
}