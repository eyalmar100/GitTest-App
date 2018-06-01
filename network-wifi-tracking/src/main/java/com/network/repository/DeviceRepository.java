package com.network.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.network.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
	
	
	//@Query("select D from Device d where d.network = ?1")
   // List<Device> getAllDevicesByNetworkId(Long NetworkId);
	
}
