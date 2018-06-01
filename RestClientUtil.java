package com.nerwork.util;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.network.entity.Device;
import com.network.entity.Network;

public class RestClientUtil {
    
	private static String baseUrl="http://localhost:8080/wifi-tracking/api/network";
	//private static String baseUrljson="http://localhost:8080/wifi-tracking/api/networkJson";
	
	private RestTemplate restTemplate; 
	
	RestClientUtil(){
		 restTemplate = new RestTemplate();
	}
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

   /* public void getAllBranches() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/api/branches";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Branch[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Branch[].class);
        Branch[] branches = responseEntity.getBody();
        for(Branch branch : branches) {
              System.out.println("Id:"+branch.getAddress()+", Title:"+branch.getName());
        }
    }
    
    
    
    
    public static  String  convertBankToJson(String bankName,String branch1Name,String branch1Address,String branch2Name,String branch2Address){
    	Bank bank = new Bank();
   	    bank.setName(bankName);
   	    Branch b=new Branch();
   	    b.setName(branch1Name);
   	    b.setAddress(branch1Address);
   	    Branch b1=new Branch();
   	    b1.setAddress(branch2Address);
   	    b1.setName(branch2Name);
   	    Set<Branch> branches =new HashSet();
   	    branches.add(b);
   	    branches.add(b1);
   	  
   	     bank.setBranches(branches);
   	     ObjectMapper mapper = new ObjectMapper();
   	    String bankAsPrettyString="";
   	     try {
 			bankAsPrettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bank);
 			System.out.println("RestClientUtil.convertBankToJson(): object is "+bankAsPrettyString);
 		
   	     
   	     } 
   	     catch (JsonProcessingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
   	     
   	    return bankAsPrettyString;
    }
    
    public void addBank(String bank){
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	 RestTemplate restTemplate = new RestTemplate();
  	     String url = "http://localhost:8080/api/addbank";  	     	     
  	     HttpEntity<String> requestEntity = new HttpEntity<String>(bank, headers);
  	     restTemplate.postForLocation(url, requestEntity);
     	
    }*/
	
	
	
	public void getNetworkByIdObj(long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    // good http://localhost:8080/wifi-tracking/api/network?id=123456
	 //   http://localhost:8080/wifi-tracking/api/network?=123456
		String url = baseUrl+"?id="+id;
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Network> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Network.class);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        //networkJson
        Network network = responseEntity.getBody();
        System.out.println("RestClientUtil.getNetworkById():network is "+network);
        List<Device>devices=network.getDevices();
        for(Device device : devices) {
             System.out.println("Id:"+device.getName());
        }
    }
	
     
	/*public void getNetworkById(long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    // good http://localhost:8080/wifi-tracking/api/network?id=123456
	 //   http://localhost:8080/wifi-tracking/api/network?=123456
		String url = baseUrljson+"?id="+id;
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        //ResponseEntity<Network> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Network.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        //networkJson
        String network = responseEntity.getBody();
        System.out.println("RestClientUtil.getNetworkById():network is "+network);
       
    }*/
    
    public void connect(String connectionParams) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
   	    String url = baseUrl+"/connect";
	    
        HttpEntity<String> requestEntity = new HttpEntity<String>(connectionParams, headers);
        restTemplate.put(url, requestEntity);
    }
    
    public void report(String connectionParams) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
   	    String url = baseUrl+"/report";
	    
        HttpEntity<String> requestEntity = new HttpEntity<String>(connectionParams, headers);
        restTemplate.postForEntity(url, requestEntity, String.class);//(url, requestEntity);
        System.out.println("RestClientUtil.enclosing_method()");
    }
     
    
    /*public void deleteBank() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/api/bank/1";
        HttpEntity<Bank> requestEntity = new HttpEntity<Bank>(headers);
        restTemplate.delete(url);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1);
        System.out.println("RestClientUtil.deleteBank()");
    }
    */
   
    public static void main(String args[]) {
    	
    	     
    	Path path;
    	  StringBuilder data = new StringBuilder();
		try {
			path = Paths.get(RestClientUtil.class.getClassLoader()
				      .getResource("reportParams.json").toURI());
			
			//data = new StringBuilder();
 		    Stream<String> lines = Files.lines(path);
 		    lines.forEach(line -> data.append(line).append("\n"));
 		    lines.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		          
    		   
    	
    		    
    		    String connectionParams;//=convertBankToJson("MIZRAHI","TEL-AVIV","WEITZMAN","HOLON","SOKOLOV");
    	
    	RestClientUtil util = new RestClientUtil();
    	util.report(data.toString());
    	//util.getNetworkByIdObj(123456);
        //util.getArticleByIdDemo();
    	//util.connect(data.toString()) ;
    	
    //	jsonBank=convertBankToJson("HAPOALIM","HERZELIA","ADD1","KFAR-SABA","ADD2");
    	
    	//util.addBank(jsonBank);
    	//util.deleteArticleDemo();
    //	util.updateBank();
    	//util.deleteBank();
    	
    	//util.addArticleDemo();
  //  	util.getAllBranches();
    	//util.updateArticleDemo();
    	//util.deleteArticleDemo();
    }    
}
