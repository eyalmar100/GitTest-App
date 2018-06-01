package com.network.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 





import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Getter
@Setter
@Entity
@Table(name = "device")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Device implements Serializable{
	
   private static final long serialVersionUID = 1L;

	@Id
  	@Column(name="device_id",unique = true)
	//@JsonProperty
    private String id;
	
	@Column(name="device_name")
	//@JsonProperty
	private String name;
	
	@Column(name="device_throughput")
	//@JsonProperty
	private float throughput;
	
	
	//@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "network_id")
    private Network  network;
	
	public Device(String id,String name){
		this.id=id;
		this.name=name;
		this.throughput=0.0F;
	}

}
