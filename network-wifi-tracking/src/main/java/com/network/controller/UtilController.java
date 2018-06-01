package com.network.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.network.entity.Device;
import com.network.entity.Network;
import com.network.service.NetworkService;


@RestController
@RequestMapping("util/api")
public class UtilController {
	
	@Autowired
	private NetworkService networkService;
	
	@PostMapping("/network/devices")
	public ResponseEntity<Void> addDevices(@RequestBody String payload){
	     
		JSONObject json=new JSONObject(payload);
		String deviceId=json.getString("device_id"); 
		String deviceName=json.getString("device_name");
		long networkId=json.getLong("network_id");
		String auth=json.getString("auth");
		Network network=networkService.getNetworkById(networkId);
		Device device=new Device(deviceId,deviceName);
		if(network==null){
			network=new Network(networkId,auth);	
		}
		List<Device> listDevices=network.getDevices();
		listDevices.add(device);
		device.setNetwork(network);
		networkService.update(network);
		
		network.setAvgThroughput();
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);	
	}


}
