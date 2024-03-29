package org.princehouse.mica.example;


import java.io.Serializable;
import java.util.Set;

import org.princehouse.mica.base.BaseProtocol;
import org.princehouse.mica.base.annotations.GossipUpdate;
import org.princehouse.mica.base.annotations.SelectUniformRandom;
import org.princehouse.mica.base.net.model.Address;

/**
 * Pull-based "find minimum value" example protocol
 * 
 * @author lonnie
 *
 */
public class FindMinPull extends BaseProtocol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x; 

	@SelectUniformRandom
	public Set<Address> view;
	
	public FindMinPull(int x, Set<Address> view) {
		this.x = x;
		this.view = view;
	}

	@GossipUpdate
	public void update(FindMinPull other) {
		FindMinPull o = (FindMinPull) other;
		int temp = Math.min(x, o.x);		
		x = temp;
	}

}
