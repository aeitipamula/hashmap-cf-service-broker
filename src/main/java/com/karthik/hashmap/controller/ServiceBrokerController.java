package com.karthik.hashmap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.hashmap.model.Catalog;
import com.karthik.hashmap.model.ServiceInstance;
import com.karthik.hashmap.model.ServiceInstanceBinding;
import com.karthik.hashmap.service.HaashMapInstanceBindingService;
import com.karthik.hashmap.service.HaashMapServiceInstanceService;
import com.karthik.hashmap.service.HashMapBrokerService;
import com.karthik.hashmap.service.HashMapCatalogService;



@RestController
public final class ServiceBrokerController {

	@Autowired
	HashMapCatalogService catalogService;

	@Autowired
	HaashMapServiceInstanceService serviceInstancesLists;

	@Autowired
	HashMapBrokerService 	hashMapBrokerService;

	@Autowired
	HaashMapInstanceBindingService serviceInstanceBindingLists;

	
	@Value("${dashboardUrl}")
	String dashboardUrl;

	@RequestMapping("/v2/catalog")
	public Catalog getCatalog(){
		return catalogService.getCatalog();
	}

	@RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> create(@PathVariable("id") String id, @RequestBody ServiceInstance serviceInstance) {

		serviceInstance.setId(id);

		boolean exists = serviceInstancesLists.isExists(id);

		if (exists) {
			ServiceInstance existing = serviceInstancesLists.getServiceInstance(id);
			if (existing.equals(serviceInstance)) {
				return new ResponseEntity<>("{}", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("{}", HttpStatus.CONFLICT);
			}
		} else {
			serviceInstancesLists.create(serviceInstance);
			hashMapBrokerService.create(id);
			return new ResponseEntity<>("{}", HttpStatus.CREATED);
		}
	}
	@RequestMapping(value = "/v2/service_instances/{instanceId}/service_bindings/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> createBinding(@PathVariable("instanceId") String instanceId,
			@PathVariable("id") String id,
			@RequestBody ServiceInstanceBinding serviceInstanceBinding) {

		if (!serviceInstancesLists.isExists(instanceId)) {
			return new ResponseEntity<Object>("{\"description\":\"Service instance " + instanceId + " does not exist!\"", HttpStatus.BAD_REQUEST);
		}
		serviceInstanceBinding.setId(id);
		serviceInstanceBinding.setServiceInstanceId(instanceId);
		
        
		boolean exists = serviceInstanceBindingLists.isExists(id);

		if (exists) {
			ServiceInstanceBinding existing = serviceInstanceBindingLists.getServiceBindingInstance(id);
			if (existing.equals(serviceInstanceBinding)) {
				return new ResponseEntity<Object>((existing.getCredentials()), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("{}", HttpStatus.CONFLICT);
			}
		}else {
			Map<String,Object> credentials = new HashMap<String,Object>();
			credentials.put(id,UUID.randomUUID().toString());
			credentials.put("uri", "http://" + dashboardUrl + "/HashMap/" + instanceId );
			credentials.put("username","username");
			credentials.put("password","password");
			serviceInstanceBinding.setCredentials(credentials);
			serviceInstanceBindingLists.createServiceInstance(id,serviceInstanceBinding);
			return new ResponseEntity<Object>(credentials, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/v2/service_instances/{instanceId}/service_bindings/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBinding(@PathVariable("instanceId") String instanceId,
			@PathVariable("id") String id,
			@RequestParam("service_id") String serviceId,
			@RequestParam("plan_id") String planId) {

		boolean exists = serviceInstanceBindingLists.isExists(id);

		if (exists) {
			serviceInstanceBindingLists.delete(id);
			return new ResponseEntity<>("{}", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{}", HttpStatus.GONE);
		}

	}

	@RequestMapping(value = "/v2/service_instances/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") String id,
			@RequestParam("service_id") String serviceId,
			@RequestParam("plan_id") String planId) {

		boolean exists = serviceInstancesLists.isExists(id);

		if (exists) {
			serviceInstancesLists.delete(id);
			hashMapBrokerService.delete(id);
			return new ResponseEntity<>("{}", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{}", HttpStatus.GONE);
		}


	}


}
