package io.gigabyte.labs.sensebot.common.mapper;

import io.gigabyte.labs.sensebot.common.dto.IoTDeviceDTO;
import io.gigabyte.labs.sensebot.common.model.entity.IoTDevice;
import org.springframework.stereotype.Component;

@Component
public class IoTDeviceMapper {

    public IoTDevice fromDto2Entity(IoTDeviceDTO ioTDeviceDTO) {
        return new IoTDevice();
    }

}
