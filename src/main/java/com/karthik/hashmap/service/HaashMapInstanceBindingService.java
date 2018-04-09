package com.karthik.hashmap.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.karthik.hashmap.model.ServiceInstanceBinding;

@Service
public class HaashMapInstanceBindingService {

	Map<String,ServiceInstanceBinding> listServiceBindingInstance = new LinkedHashMap<String,ServiceInstanceBinding>();

	public void createServiceInstance(String id ,ServiceInstanceBinding request) {
		listServiceBindingInstance.put(id, request);
	}


	public ServiceInstanceBinding getServiceBindingInstance(String id) {
		return listServiceBindingInstance.get(id);
	}


	public boolean isExists(String id) {
		if (listServiceBindingInstance.get(id) != null){
			return true;
		}

		return false;
	}

	public void create(ServiceInstanceBinding request) {
		listServiceBindingInstance.put(request.getId(), request);

	}


	public void delete(String id) {
		listServiceBindingInstance.remove(id);
	}

}
