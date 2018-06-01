package com.network.entity;

  
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Getter
@Setter
@Entity
@Table(name = "network")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Network implements Serializable {
 
	private static final long serialVersionUID = 1L;

	
	
	public Network(Long Id,String auth){
		super();
		this.id=Id;
		this.auth=auth;
		 
	}
	
	@Id
  	@Column(name="network_id",unique = true)
    private Long id;
	
 	
	@Column(name="network_auth")
	private String auth;
	
	@Column(name="avg_throughput")
	private float avgThroughput;

    public void setAvgThroughput(){
    	if(devices.size()==0){
    		avgThroughput=0;
    		return;
    	}
    	
    	float sum=0;
    	for(Device currDevice:devices){
    		   sum+=currDevice.getThroughput();
    	 }
    	avgThroughput=sum/devices.size();
    }
    @OneToMany(mappedBy = "network", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Device> devices=new ArrayList();

    
}
