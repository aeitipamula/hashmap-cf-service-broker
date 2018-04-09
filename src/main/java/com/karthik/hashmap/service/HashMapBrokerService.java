/**
 * 
 */
package com.karthik.hashmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HashMapBrokerService {


	@Autowired
	private CustomHashMapService<String, CustomHashMapService<Object, Object>> map;

	/**
	 * @return the map
	 */
	public CustomHashMapService<String, CustomHashMapService<Object, Object>> getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(CustomHashMapService<String, CustomHashMapService<Object, Object>> map) {
		this.map = map;
	}

	/**
	 * 
	 * @param id
	 * @param key
	 * @param value
	 */
	public void put(final String id, final Object key, final Object value) {
		final CustomHashMapService<Object, Object> customMap = map.get(id);
		customMap.put(key, value);
	}

	/**
	 * 
	 * @param id
	 * @param key
	 * @return
	 */
	public Object get(final String id, final Object key) {
		final CustomHashMapService<Object, Object> customMap = map.get(id);
		return customMap.get(key);
	}

	/**
	 * 
	 * @param id
	 * @param key
	 */
	public void delete(final String id, final Object key) {
		final CustomHashMapService<Object, Object> customMap = map.get(id);
		customMap.remove(key);
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(final String id) {
		map.remove(id);
	}

	/**
	 * 
	 * @param id
	 */
	public void create(final String id) {
		map.put(id, new CustomHashMapService<Object, Object>());
	}

	public String display(String instanceId) {
		final CustomHashMapService<Object, Object> customMap = map.get(instanceId);
		return customMap.display();
	}
}
