package ua.nure.kn.ovsiienko.agent;

import jade.core.Agent;

public class SearchAgent extends Agent {

	private static final long serialVersionUID = 1L;

protected void setup() {
	
	super.setup();
	System.out.println(getAID().getName()+ " started");
	
}

	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		super.takeDown();
	}
}
