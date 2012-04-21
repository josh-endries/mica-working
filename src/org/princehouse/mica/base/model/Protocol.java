package org.princehouse.mica.base.model;

import java.net.InetAddress;

import org.princehouse.mica.base.net.model.Address;
import org.princehouse.mica.util.Distribution;

/**
 * Don't use this directly.
 * Extend BaseProtocol to implement your own protocols.
 * 
 * @author lonnie
 *
 */
public interface Protocol {
	/** 
	 * Not currently used
	 * 
	 * @author lonnie
	 *
	 */
	public static enum Direction {
		PUSH, PULL, PUSHPULL
	}
	
	/**
	 * Execute the update function of this protocol on another local protocol instance
	 * @param other
	 */
	public void executeUpdate(Protocol other);
	
	/**
	 * Execute the rate function for this Protocol instance.
	 * 
	 * @return
	 */
	public double getFrequency();
	
	/**
	 * Retrieve the origin of this protocol instance.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 * @return The origin address of this protocol instance.
	 */
	public InetAddress getOrigin();

	/**
	 * RuntimeState is location-specific state that is independent of the protocol instance.
	 * This includes the runtime clock and random number generator.
	 * 
	 * @return Local node's runtime state.
	 */
	public RuntimeState getRuntimeState();
	
	/**
	 * Execute the select function for this Protocol instance and return its address distribution.
	 * @return Address distribution
	 */
	public Distribution<Address> getSelectDistribution();
	
	/**
	 * Called just before beginning the select/update event loop.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 */
	public void initialize();
	
	/**
	 * postGossip is called just after this protocol instance is received back
	 * from being sent over the network and updated.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 * @param other The address to which this protocol instance was sent.
	 */
	public void postGossip(final Address other);
	
	/**
	 * postUpdate is called on the local protocol instance after receiving an
	 * incoming instance but just before calling update on that incoming
	 * instance.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 * @param other The protocol instance on which update will be called.
	 */
	public void postUpdate(final Protocol other);
	
	/**
	 * preGossip is called just before this protocol instance is sent over the
	 * network.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 * @param other The address to which this protocol instance will be sent.
	 */
	public void preGossip(final Address other);
	
	/**
	 * preUpdate is called on the local protocol instance after receiving an
	 * incoming instance but just before calling update on that incoming
	 * instance.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 * @param other The protocol instance on which update will be called.
	 */
	public void preUpdate(final Protocol other);
	
	/**
	 * Set the origin address for this protocol instance.
	 * 
	 * @author Josh Endries (jce54@cornell.edu)
	 * @param address The origin address.
	 */
	public void setOrigin(InetAddress address);
}
