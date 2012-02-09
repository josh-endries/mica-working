package org.princehouse.mica.test;

import fj.F3;

import java.net.UnknownHostException;
import java.util.List;

import org.princehouse.mica.base.net.model.Address;
import org.princehouse.mica.lib.MinAddressLeaderElection;
import org.princehouse.mica.lib.SpanningTree;
import org.princehouse.mica.lib.TreeCountNodes;
import org.princehouse.mica.lib.abstractions.MergeIndependent;
import org.princehouse.mica.lib.abstractions.Overlay;
import org.princehouse.mica.lib.abstractions.StaticOverlay;
import org.princehouse.mica.util.TestHarness;


/**
 * Tests leader election + spanning tree + counting
 * @author lonnie
 *
 */
public class TestStack2 extends TestHarness<MergeIndependent> {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) {


		F3<Integer, Address, List<Address>, MergeIndependent> createNodeFunc = new F3<Integer, Address, List<Address>, MergeIndependent>() {
			@Override
			public MergeIndependent f(Integer i, Address address,
					List<Address> neighbors) {

				Overlay view = new StaticOverlay(neighbors);

				MinAddressLeaderElection leaderElection = new MinAddressLeaderElection(view);
				leaderElection.setName(String.format("leader-%d",i));

				SpanningTree tree = new SpanningTree(leaderElection, view);
				tree.setName(String.format("tree-%d",i));

				TreeCountNodes counting = new TreeCountNodes(tree);
				counting.setName(String.format("count-%d",i));

				return MergeIndependent.merge(
						leaderElection,
						MergeIndependent.merge(
								tree,
								counting
						));
			}
		};

		new TestStack2().runRandomGraph(0, 20, 8, createNodeFunc);

	}

}
