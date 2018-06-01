package com.network.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.entity.Device;
import com.network.entity.Network;
import com.network.repository.DeviceRepository;
 
@Service
public class DeviceService {

	@Autowired
	private DeviceRepository  deviceRepository;
	
	
	public Set<Device> getAllDevicesByNetworkId(Long networkId){
		Set<Device>devices=null;//deviceRepository.getAllDevicesByNetworkId(networkId);
		
		//deviceRepository.
		return devices;
		//return networkRepository.
	}
	
	public Device getDeviceById(String deviceId){
		Device device=deviceRepository.findOne(deviceId);
		return device;
	}
	
}
