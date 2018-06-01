package com.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.network.entity.Network;
import com.network.repository.NetworkRepository;

@Service
public class NetworkService {
	
	@Autowired
	private NetworkRepository  networkRepository;
	
	public Network getNetworkById(long networkId){
		Network network=networkRepository.findOne(networkId);
		return network;
	}
	
	
	public void createNetwork(long id,String networkName){
		Network wifiNetwork=new Network(id,networkName);
	 	networkRepository.saveAndFlush(wifiNetwork);
		
	}


	public void update(Network network) {
		Network dbNetwork=networkRepository.getOne(network.getId());
		dbNetwork.setAvgThroughput(network.getAvgThroughput());
		networkRepository.save(dbNetwork);
		
		//networkRepository.updateNetwork(network.getId(), network.getAvgThroughput());
		
	}
	public void saveNewNetwork(Network network) {
		networkRepository.saveAndFlush(network);
		
	}

}
