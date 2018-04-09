package com.karthik.hashmap.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.karthik.hashmap.HashMapServiceBrokerApplication;
import com.karthik.hashmap.service.HashMapBrokerService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashMapServiceBrokerApplication.class)
public class HashMapControllerTest {

	@Autowired
	HashMapController hashMapController;
	
	
	
	@Test
	public void testTesting() {
		hashMapController.testing();
	}

	
}
