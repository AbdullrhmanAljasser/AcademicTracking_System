import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.client.ATClient;
import main.server.logic.model.University;
import main.server.network.ATServer;
import main.utilities.Config;

public class seqGlueCode {
	static ATServer ATS = new ATServer(Config.DEFAULT_PORT);
	static ATClient ATC1 = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	static ATClient ATC2 = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	static ATClient ATC3 = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	static ATClient ATC4 = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	static ATClient ATC5 = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	
	@Given("^ClerkOne logged in$")
	public void clerkone_logged_in() throws Throwable {
	    ATS.handle(ATC1.getID(), "");
	    ATS.handle(ATC1.getID(), "clerk");
	    ATS.handle(ATC1.getID(), "admin");
	}

	@Given("^ClerkOne logs out$")
	public void clerkone_logs_out() throws Throwable {
		ATS.handle(ATC1.getID(), "log out");
	}
	
	@Given("^ClerkOne Creates StudentOne$")
	public void clerkone_Creates_StudentOne() throws Throwable {
		ATS.handle(ATC1.getID(), "create student");
		ATS.handle(ATC1.getID(), "111010111, Student One, y");
	}

	@Given("^StudentOne logged in$")
	public void studentone_logged_in() throws Throwable {
	    ATS.handle(ATC2.getID(), "");
	    ATS.handle(ATC2.getID(), "student");
	    ATS.handle(ATC2.getID(), "111010111, Student One");
	}

	@Given("^ClerkTwo logged in$")
	public void clerktwo_logged_in() throws Throwable {
		ATS.handle(ATC3.getID(), "");
	    ATS.handle(ATC3.getID(), "clerk");
	    ATS.handle(ATC3.getID(), "admin");
	}

	@Given("^ClerkTwo Create CourseOne$")
	public void clerktwo_Create_CourseOne() throws Throwable {
		ATS.handle(ATC3.getID(), "create course");
		ATS.handle(ATC3.getID(), "MATH, 110012, 5, n, 1, 1, y, y");
	}

	@When("^StudentTwo Register in CourseOne$")
	public void studenttwo_Register_in_CourseOne() throws Throwable {
		Config.REGISTRATION_STARTS = true;
		ATS.handle(ATC2.getID(), "select course");
		ATS.handle(ATC2.getID(), "110012");
		ATS.handle(ATC2.getID(), "register for course");
		ATS.handle(ATC2.getID(), "110012");
		Config.REGISTRATION_STARTS = false;
	}

	@Then("^StudentOne enrolls in CourseOne and logsout$")
	public void studentone_enrolls_in_CourseOne_and_logsout() throws Throwable {
		assertEquals(true, University.getInstance().GetStudent(111010111).IsRegistered(University.getInstance().GetCourse(110012)));
	    ATS.handle(ATC2.getID(), "log out");
	}
	
	@Given("^ClerkTwo logs in$")
	public void clerktwo_logs_in() throws Throwable {
		ATS.handle(ATC2.getID(), "");
	    ATS.handle(ATC2.getID(), "clerk");
	    ATS.handle(ATC2.getID(), "admin");
	}

	@Given("^ClerkTwo Creates CourseTwo$")
	public void clerktwo_Creates_CourseTwo() throws Throwable {
		ATS.handle(ATC2.getID(), "create course");
		ATS.handle(ATC2.getID(), "MATHH, 110013, 5, n, 1, 1, y, y");
	}

	@Given("^ClerkTwo Creates CourseThree$")
	public void clerktwo_Creates_CourseThree() throws Throwable {
		ATS.handle(ATC2.getID(), "create course");
		ATS.handle(ATC2.getID(), "MATHHH, 110014, 5, n, 1, 1, y, y");
	}

	@Given("^ClerkTwo Creates CourseFour$")
	public void clerktwo_Creates_CourseFour() throws Throwable {
		ATS.handle(ATC2.getID(), "create course");
		ATS.handle(ATC2.getID(), "MATHHH, 110015, 5, n, 1, 1, y, y");
	}

	@Given("^ClerkTwo Creates CourseFive$")
	public void clerktwo_Creates_CourseFive() throws Throwable {
		ATS.handle(ATC2.getID(), "create course");
		ATS.handle(ATC2.getID(), "MATHHHH, 110016, 5, n, 1, 1, y, y");
	}

	@Given("^ClerkTwo delete CourseThree$")
	public void clerktwo_delete_CourseThree() throws Throwable {
		ATS.handle(ATC2.getID(), "delete course");
		ATS.handle(ATC2.getID(), "110014");
		ATS.handle(ATC2.getID(), "log out");
	}

	@Given("^ClerkThree logged in$")
	public void clerkthree_logged_in() throws Throwable {
		ATS.handle(ATC2.getID(), "");
	    ATS.handle(ATC2.getID(), "clerk");
	    ATS.handle(ATC2.getID(), "admin");
	}

	@Given("^ClerkThree creates StudentTwo$")
	public void clerkthree_creates_StudentTwo() throws Throwable {
		ATS.handle(ATC2.getID(), "create student");
		ATS.handle(ATC2.getID(), "111020111, Student Two, y");
	}

	@Given("^ClerkThree creates StudentThree$")
	public void clerkthree_creates_StudentThree() throws Throwable {
		ATS.handle(ATC2.getID(), "create student");
		ATS.handle(ATC2.getID(), "111030111, Student Three, y");
	}

	@Given("^ClerkThree delets StudentThree$")
	public void clerkthree_delets_StudentThree() throws Throwable {
		ATS.handle(ATC2.getID(), "delete student");
		ATS.handle(ATC2.getID(), "111030111");
		ATS.handle(ATC2.getID(), "log out");
	}

	@When("^StudentTwo logged in$")
	public void studenttwo_logged_in() throws Throwable {
		ATS.handle(ATC2.getID(), "");
	    ATS.handle(ATC2.getID(), "student");
	    ATS.handle(ATC2.getID(), "111020111, Student Two");
	}

	@When("^StudentTwo Register CourseTwo$")
	public void studenttwo_Register_CourseTwo() throws Throwable {
		Config.REGISTRATION_STARTS = true;
		ATS.handle(ATC2.getID(), "select course");
		ATS.handle(ATC2.getID(), "110013");
		ATS.handle(ATC2.getID(), "register for course");
		ATS.handle(ATC2.getID(), "110013");
	}

	@When("^StudentTwo Register CourseFive$")
	public void studenttwo_Register_CourseFive() throws Throwable {
		ATS.handle(ATC2.getID(), "select course");
		ATS.handle(ATC2.getID(), "110016");
		ATS.handle(ATC2.getID(), "register for course");
		ATS.handle(ATC2.getID(), "110016");
	}

	@When("^StudentTwo deRegister CourseFive$")
	public void studenttwo_deRegister_CourseFive() throws Throwable {
		ATS.handle(ATC2.getID(), "deregister course");
		ATS.handle(ATC2.getID(), "110016");
	}

	@Then("^StudentTwo logs out$")
	public void studenttwo_logs_out() throws Throwable {
		ATS.handle(ATC2.getID(), "log out");
	}
	
	@When("^Registration time Ends$")
	public void registration_time_Ends() throws Throwable {
	    Config.REGISTRATION_ENDS = true;
	}

	@Then("^ClerkTwo cancel course$")
	public void clerktwo_cancel_course() throws Throwable {
		ATS.handle(ATC2.getID(), "cancel course");
		ATS.handle(ATC2.getID(), "110015");
		ATS.handle(ATC2.getID(), "log out");
	}
	
	
}
