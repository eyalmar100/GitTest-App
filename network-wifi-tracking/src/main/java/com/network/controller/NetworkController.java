package com.network.controller;

import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.network.entity.Device;
import com.network.entity.Network;
import com.network.service.DeviceService;
import com.network.service.NetworkService;

 

@RestController
@RequestMapping("wifi-tracking/api")
public class NetworkController {

	@Autowired
	private NetworkService networkService;
	
	 
	
	@Autowired
	private DeviceService deviceDervice; 
	
	
	@GetMapping("/network")
    public Network getById(@RequestParam("id") Long id) {
		Network net=networkService.getNetworkById(id);
		
		List<Device>devices=net.getDevices(); 
 		net.setDevices(devices);
        return net;
	}
	
	@PutMapping("/network/connect")
 	public ResponseEntity<Void> connect(@RequestBody String payload){
	     
		JSONObject json=new JSONObject(payload);
		String deviceId=json.getString("device_id"); 
		String deviceName=json.getString("device_name");
		long networkId=json.getLong("network_id");
		String auth=json.getString("auth");
		Network network=networkService.getNetworkById(networkId);
		Device device=new Device(deviceId,deviceName);
		if(network==null){
			
			network=new Network(networkId,auth);
			handleDevices(network, device);
			network.setAvgThroughput();
			networkService.saveNewNetwork(network);
		}
	  else
	  {
		 
		  handleDevices(network, device);
 		  networkService.update(network);
	  }
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);	
	}
	
	@PostMapping("/network/report")
	public ResponseEntity<Void> report(@RequestBody String payload){
		
		JSONObject json=new JSONObject(payload);
		String deviceId=json.getString("device_id"); 
		long networkId=json.getLong("network_id");
		float throughput=json.getFloat("throughput");
		Network network=networkService.getNetworkById(networkId);
		if(network==null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		List<Device> devices=network.getDevices();
		Device requestedDevice=null;
		for(Device device :devices){
			if(device.getId().equals(deviceId)){
				requestedDevice=device;
				break;
			}
					 
		}
		if(requestedDevice==null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);	
		}
	 	requestedDevice.setThroughput(throughput);
		network.setAvgThroughput();
		networkService.update(network);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private void handleDevices(Network network, Device device) {
		List<Device> listDevices=network.getDevices();
		listDevices.add(device);
		device.setNetwork(network);
 		network.setAvgThroughput();
	}
	

	
	 
	
}
