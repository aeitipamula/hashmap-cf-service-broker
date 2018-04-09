package com.karthik.hashmap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.karthik.hashmap.model.ServiceInstance;

@Service
public class HaashMapServiceInstanceService {

	Map<String,ServiceInstance> listServiceInstance = new LinkedHashMap<String,ServiceInstance>();

	public void createServiceInstance(String id ,ServiceInstance request) {
		listServiceInstance.put(id, request);
	}


	public ServiceInstance getServiceInstance(String id) {
		return listServiceInstance.get(id);
	}


	public boolean isExists(String id) {
		if (listServiceInstance.get(id) != null){
			return true;
		}

		return false;
	}

	public void create(ServiceInstance serviceInstance) {
		listServiceInstance.put(serviceInstance.getId(), serviceInstance);

	}


	public void delete(String id) {
		listServiceInstance.remove(id);
	}

}
