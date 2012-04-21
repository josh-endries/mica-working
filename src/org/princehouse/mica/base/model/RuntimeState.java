package org.princehouse.mica.base.model;


import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.princehouse.mica.base.net.model.Address;


/**
 * This is the chunk of global state that Protocol instances have access to.
 * 
 * @author lonnie
 *
 */
public class RuntimeState implements Serializable {
	private static final long serialVersionUID = 1L;
	private Address address = null;
	private Random random;
	private final ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<String, Object>();
	
	public RuntimeState() {
		random = new Random();
	}
	
	public Address getAddress() {
		return address;
	}
	
	/**
	 * Retrieve a reference to a data item.
	 * 
	 * @param key The key associated with the data item to be returned.
	 * @return The data item associated with the specified key, or null if the key doesn't exist.
	 */
	public Object getData(String key) {
		return data.get(key);
	}
	
	public Random getRandom() {
		return random;
	}
	
	/**
	 * Put the specified data item into the data map and associated it with the specified key.
	 * 
	 * @param key The key with which to associate the data item.
	 * @param data The data to put into the data map.
	 */
	public Object putData(String key, Object data) {
		return this.data.put(key, data);
	}
	
	public void setAddress(Address address) {
		// long tid = Thread.currentThread().getId();
		// int hc = hashCode();
		// System.out.printf("setAddress. thread: %d     state-hash: %d    address: %s\n",tid,hc,address );
		
		if(this.address != null) 
			throw new RuntimeException("Address is read-only once it has been set"); 
		this.address = address;
	}
	public void setRandom(Random random) {
		this.random = random;
	} 
	
	public void update(RuntimeState update) {
		if(!update.getAddress().equals(getAddress())) {
			throw new RuntimeException("runtime state addresses differ!  should not change");
		}
		setRandom(update.getRandom());
	}
}
