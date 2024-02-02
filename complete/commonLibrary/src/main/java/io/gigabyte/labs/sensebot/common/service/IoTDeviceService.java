package io.gigabyte.labs.sensebot.common.service;

import io.gigabyte.labs.sensebot.common.dto.IoTDeviceDTO;
import io.gigabyte.labs.sensebot.common.mapper.IoTDeviceMapper;
import io.gigabyte.labs.sensebot.common.model.entity.IoTDevice;
import io.gigabyte.labs.sensebot.common.repository.IoTDeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IoTDeviceService {

    private final IoTDeviceRepository ioTDeviceRepository;
    private final IoTDeviceMapper ioTDeviceMapper;

    public IoTDeviceService(IoTDeviceRepository ioTDeviceRepository, IoTDeviceMapper ioTDeviceMapper) {
        this.ioTDeviceRepository = ioTDeviceRepository;
        this.ioTDeviceMapper = ioTDeviceMapper;
    }

    @Transactional
    public IoTDevice saveIoTDeviceData(IoTDeviceDTO ioTDeviceDTO) {
        IoTDevice ioTDevice = ioTDeviceMapper.fromDto2Entity(ioTDeviceDTO);
        return this.ioTDeviceRepository.save(ioTDevice);
    }
}
