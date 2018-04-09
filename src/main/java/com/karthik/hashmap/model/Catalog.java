package com.karthik.hashmap.model;

import java.util.ArrayList;
import java.util.List;

;

/**
 * The catalog of services offered by this broker.
 * 
 */
public class Catalog  {

	private List<ServiceDefinition> serviceDefinitions = new ArrayList<ServiceDefinition>();
	
	public Catalog(List<ServiceDefinition> serviceDefinitions) {
		this.setServiceDefinitions(serviceDefinitions); 
	}
	
	public List<ServiceDefinition> getServiceDefinitions() {
		return serviceDefinitions;
	}

	private void setServiceDefinitions(List<ServiceDefinition> serviceDefinitions) {
		if ( serviceDefinitions == null ) {
			// ensure serialization as an empty array, not null
			this.serviceDefinitions = new ArrayList<ServiceDefinition>();
		} else {
			this.serviceDefinitions = serviceDefinitions;
		} 
	}
	
}
