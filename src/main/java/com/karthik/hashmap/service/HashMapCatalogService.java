package com.karthik.hashmap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.karthik.hashmap.model.Catalog;
import com.karthik.hashmap.model.Plan;
import com.karthik.hashmap.model.ServiceDefinition;

@Service
public class HashMapCatalogService  {
	
 
	
	public Catalog getCatalog() {
	    List<ServiceDefinition> listOfService = new ArrayList<ServiceDefinition>();
	    listOfService.add(getServiceDefinition("hashmap-service"));
		return new Catalog(listOfService) ;
	}

	public ServiceDefinition getServiceDefinition(String serviceId) {
		
		List<Plan> plans = new ArrayList<Plan>();
		plans.add(getPlan());
		return new ServiceDefinition(serviceId, "hashmap-service-broker", "hash map service", true, plans);
		
	}

	private Plan getPlan() {
		return new Plan("simple-plan","standard","A simple plan",true);
		   
	}
}
