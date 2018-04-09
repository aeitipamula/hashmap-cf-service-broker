package com.karthik.hashmap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.hashmap.service.CustomHashMapService;
import com.karthik.hashmap.service.HashMapBrokerService;

@RestController
@RequestMapping("/HashMap")
public class HashMapController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private HashMapBrokerService mapBrokerService;
	
	@GetMapping("/test")
	public String testing(){
		return "test";
	}
	
	@PutMapping("put/{instanceId}/key/{key}/value/{value}")
	public ResponseEntity<?> put(@PathVariable String instanceId,@PathVariable String key,@PathVariable String value){
			if ( key.isEmpty() ){
				return new ResponseEntity<Object>("key Empty",HttpStatus.NOT_ACCEPTABLE);
			}else {
			   mapBrokerService.put(instanceId, key, value);
			   return new ResponseEntity<Object>("inserted",HttpStatus.CREATED);
			}
	}
	
	@GetMapping("get/{instanceId}/{key}")
	public ResponseEntity<?> get(@PathVariable String instanceId, @PathVariable String key){
		final Object response = mapBrokerService.get(instanceId, key);
		if ( null == response ){
			return new ResponseEntity<Object>("Key Not Found",HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<Object>(response,HttpStatus.FOUND);
		}
	}
	
	@RequestMapping("remove/{instanceId}/{key}")
	public ResponseEntity<?> remove(@PathVariable String instanceId,@PathVariable String key){
		final Object response = mapBrokerService.get(instanceId, key);
		if (response != null) {
			mapBrokerService.delete(instanceId, key);
			return new ResponseEntity<>("{}", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("{}", HttpStatus.GONE);
		}
	}
	
	@GetMapping("display/{instanceId}")
	public  ResponseEntity<?> display(@PathVariable String instanceId){
		return new ResponseEntity<Object>(mapBrokerService.display(instanceId),HttpStatus.OK);
	}
	
}
