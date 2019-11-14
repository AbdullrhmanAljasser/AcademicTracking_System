import static org.junit.Assert.assertEquals;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import main.client.ATClient;
import main.server.logic.handler.InputHandler;
import main.server.network.ATServer;
import main.utilities.Config;

public class featureGlueCode {
	static ATServer ATS = new ATServer(Config.DEFAULT_PORT);;
	ATClient ATC = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	
	@Given("^Server Running$")
	public void server_Running() throws Throwable {
		/*if(ATS == null)
			ATS = new ATServer(Config.DEFAULT_PORT);*/
	}

	@Given("^Client in Clerk login$")
	public void client_in_Clerk_login() throws Throwable {
		//ATC = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
		ATS.handle(ATC.getID(), "hi");
		ATS.handle(ATC.getID(), "clerk");
	}

	@When("^Client inputs correct Clerk password$")
	public void client_inputs_correct_Clerk_password() throws Throwable {
		ATS.handle(ATC.getID(), "admin");
		System.out.println(ATC.getID());
	}

	@Then("^Client is logged into Clerk menu$")
	public void client_is_logged_into_Clerk_menu() throws Throwable {
		assertEquals(ATS.getClientState(ATC.getID()),InputHandler.CLERK);
		//Used to get the client back to main menu
		ATS.handle(ATC.getID(), "log out");
	}

	@When("^Client inputs incorrect Clerk password$")
	public void client_inputs_incorrect_Clerk_password() throws Throwable {
	    ATS.handle(ATC.getID(), "zxcqwe");
	}

	@Then("^Client is sent back to login menu$")
	public void client_is_sent_back_to_login_menu() throws Throwable {
		assertEquals(ATS.getClientState(ATC.getID()),InputHandler.CLERKLOGIN);
	}
}
