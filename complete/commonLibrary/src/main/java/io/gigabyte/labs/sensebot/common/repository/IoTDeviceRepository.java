package io.gigabyte.labs.sensebot.common.repository;

import io.gigabyte.labs.sensebot.common.model.entity.IoTDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IoTDeviceRepository extends JpaRepository<IoTDevice, String> {
}
