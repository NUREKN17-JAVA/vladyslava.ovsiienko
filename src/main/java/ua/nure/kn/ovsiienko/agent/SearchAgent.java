package ua.nure.kn.ovsiienko.agent;

import java.util.Collection;

import ua.nure.kn.ovsiienko.db.DaoFactory;
import ua.nure.kn.ovsiienko.db.DatabaseException;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class SearchAgent extends Agent {

	private static final long serialVersionUID = 1L;

protected void setup() {
	
	super.setup();
	System.out.println(getAID().getName()+ " started");
	
	DFAgentDescription description = new DFAgentDescription();
	description.setName(getAID());
	ServiceDescription serviceDescription = new ServiceDescription();
	serviceDescription.setName("JADE-searching");
	serviceDescription.setType("searching");
	description.addServices(serviceDescription);
	try{
		DFService.register(this,description);
	}catch(FIPAException e){
		e.printStackTrace();
	}
	
	addBehaviour(new RequestServer());
	
}

	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		try{
			DFService.deregister(this);
		} catch(FIPAException e){
			e.printStackTrace();
			
		}
		super.takeDown();
	}
	
	public void search(String firstName, String lastName) throws SearchException{
		try{
			Collection users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			if (users.size()>0){
			showUsers(users);
			} else{
				addBehaviour(new SearchRequestBehaviour(new AID[] {},firstName,lastName));
			}
		}catch(DatabaseException e){
			throw new SearchException(e);
			
		}
	}
	
	void showUsers(Collection user){
		//show users
	}
}
