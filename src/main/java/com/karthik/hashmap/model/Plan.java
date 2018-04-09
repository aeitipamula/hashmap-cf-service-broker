package com.karthik.hashmap.model;

import java.util.HashMap;
import java.util.Map;


/**
 * A service plan available for a ServiceDefinition
 * 
 */
public class Plan {

	private String id;
	
	private String name;
	
	private String description;
	
	private boolean free  ;
	
    private Map<String,Object> metadata;

	public Plan(String id, String name, String description,boolean free) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.free = free;
	}

	public Plan(String id, String name, String description,boolean free, Map<String,Object> metadata) {
		this(id, name, description,free);
		setMetadata(metadata);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}
	
	public void setMetadata(Map<String, Object> metadata) {
	    this.metadata = metadata;
	}
	
}
