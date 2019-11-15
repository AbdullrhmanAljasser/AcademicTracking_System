import static org.junit.Assert.assertEquals;

//import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import main.client.ATClient;
import main.server.logic.handler.InputHandler;
import main.server.logic.model.University;
import main.server.network.ATServer;
import main.utilities.Config;

public class featureGlueCode {
	static ATServer ATS = new ATServer(Config.DEFAULT_PORT);
	static ATClient ATC = new ATClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	
	@Given("^Server Running$")
	public void server_Running() throws Throwable {
		//Server running
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
	
	//----------------UC 2
	
	@Given("^Client logged in as Clerk$")
	public void client_logged_in_as_Clerk() throws Throwable {
		ATS.handle(ATC.getID(), "hi");
		ATS.handle(ATC.getID(), "clerk");
		ATS.handle(ATC.getID(), "admin");
	}

	@When("^Client in Clerk Menu selects log out$")
	public void client_in_Clerk_Menu_selects_log_out() throws Throwable {
		ATS.handle(ATC.getID(), "log out");
	}

	@Then("^Client back to greeting terminal$")
	public void client_back_to_greeting_terminal() throws Throwable {
	    assertEquals(InputHandler.WAITING,ATS.getClientState(ATC.getID()));
	}
	
	//----------------UC 3
	
	@Given("^Client in Clerk menu and selected create course$")
	public void client_in_Clerk_menu() throws Throwable {
		
	    if(ATS.getClientState(ATC.getID()) != InputHandler.CLERK) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "clerk");
			ATS.handle(ATC.getID(), "admin");
			ATS.handle(ATC.getID(), "create course");
	    } else {
	    	ATS.handle(ATC.getID(), "create course");
	    }
	}

	@Given("^University Term has not ended$")
	public void university_Term_has_not_ended() throws Throwable {
	    Config.TERM_ENDS = false;
	}
	
	@Given("^University Term Ended$")
	public void university_Term_Ended() throws Throwable {
		Config.TERM_ENDS = true;
	}

	@Given("^University Registration has not begun$")
	public void university_Registration_has_not_begun() throws Throwable {
		Config.REGISTRATION_STARTS = false;
	}
	@Given("^University Registration has begun$")
	public void university_Registration_has_begun() throws Throwable {
		Config.REGISTRATION_STARTS = true;
	}

	@When("^Client submit course info$")
	public void client_submit_course_info() throws Throwable {
		ATS.handle(ATC.getID(), "MATH, 110011, 20, n, 0, 3, n, n");
	}

	@Then("^Course is created successfully$")
	public void course_is_created_successfully() throws Throwable {
		assertEquals(ATS.getClientState(ATC.getID()),InputHandler.CLERK);
	    assertEquals(University.getInstance().CheckCourse(110011), true);
	    University.getInstance().DestroyCourse(University.getInstance().GetCourse(110011));
	    ATS.handle(ATC.getID(), "main menu");
	}

	@When("^Client submit course incorrect format info$")
	public void client_submit_course_incorrect_format_info() throws Throwable {
		ATS.handle(ATC.getID(), "MATH, 1011, 20, n, 0, n, n");
	}

	@Then("^Client is sent back to resubmit course info$")
	public void client_is_sent_back_to_resubmit_course_info() throws Throwable {
	    assertEquals(ATS.getClientState(ATC.getID()), InputHandler.CREATECOURSE);
	    ATS.handle(ATC.getID(), "main menu");
	}
	
	@Then("^Course is not created$")
	public void course_is_not_created() throws Throwable {
		Config.TERM_ENDS = false;
		Config.REGISTRATION_STARTS = false;
		Config.REGISTRATION_ENDS = false;
		assertEquals(false, University.getInstance().CheckCourse(110011));
		ATS.handle(ATC.getID(), "main menu");
	}
	
	//----------------- UC4
	
	@Given("^Client in Clerk menu and selected destroy course$")
	public void client_in_Clerk_menu_and_selected_destroy_course() throws Throwable {
		if(ATS.getClientState(ATC.getID()) != InputHandler.CLERK) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "clerk");
			ATS.handle(ATC.getID(), "admin");
			ATS.handle(ATC.getID(), "delete course");
	    } else {
	    	ATS.handle(ATC.getID(), "delete course");
	    }
	}

	@Given("^Course exists$")
	public void course_exists() throws Throwable {
		Config.TERM_ENDS = false;
		Config.REGISTRATION_STARTS = false;
		Config.REGISTRATION_ENDS = false;
	    University.getInstance().CreateCourse("PSYC", 110012, 5, false, 1, 1, false, false);
	}
	@Given("^Course doesn't exists$")
	public void course_doesn_t_exists() throws Throwable {
	    if(University.getInstance().CheckCourse(110012)) {
	    	University.getInstance().DestroyCourse(University.getInstance().GetCourse(110012));
	    }
	}


	@When("^Client submit course code to be deleted$")
	public void client_submit_course_code_to_be_deleted() throws Throwable {
		ATS.handle(ATC.getID(), "110012");
	}

	@Then("^Course is removed from university$")
	public void course_is_removed_from_university() throws Throwable {
	    assertEquals(false, University.getInstance().CheckCourse(110012));
	    ATS.handle(ATC.getID(), "main menu");
	}
	
	@Then("^Course is not removed from university$")
	public void course_is_not_removed_from_university() throws Throwable {
		assertEquals(true, University.getInstance().CheckCourse(110012));
	    ATS.handle(ATC.getID(), "main menu");
	}
	
	@Then("^Course is not removed from university and clerk returned to selection$")
	public void course_is_not_removed_from_university_and_clerk_returned_to_selection() throws Throwable {
		assertEquals(false, University.getInstance().CheckCourse(110012));
		assertEquals(InputHandler.CLERK, ATS.getClientState(ATC.getID()));
	}
	
	//----------------- UC 5
	
	@Given("^Client in Clerk menu and selected cancel course$")
	public void client_in_Clerk_menu_and_selected_cancel_course() throws Throwable {
		if(ATS.getClientState(ATC.getID()) != InputHandler.CLERK) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "clerk");
			ATS.handle(ATC.getID(), "admin");
			ATS.handle(ATC.getID(), "cancel course");
	    } else {
	    	ATS.handle(ATC.getID(), "cancel course");
	    }
	}

	@Given("^University Registration has not ended$")
	public void university_Registration_has_not_ended() throws Throwable {
	    Config.REGISTRATION_ENDS = false;
	}
	@Given("^University Registration has ended$")
	public void university_Registration_has_ended() throws Throwable {
		Config.REGISTRATION_ENDS = true;
	}

	@When("^Client submit course code to be cancelled$")
	public void client_submit_course_code_to_be_cancelled() throws Throwable {
		ATS.handle(ATC.getID(), "110012");
	}

	@Then("^Course isn't removed but students are deregistered from it$")
	public void course_isn_t_removed_but_students_are_deregistered_from_it() throws Throwable {
	    //TO ensure the info is correct then refer to logs
		assertEquals(true, University.getInstance().CheckCourse(110012));
		
	}
	@Then("^Course isn't removed and students aren't deregistered from it$")
	public void course_isn_t_removed_and_students_aren_t_deregistered_from_it() throws Throwable {
	    //Due to the course not having student in it, success or no can be checked using logs
	}
	
	//--------------------- UC 6
	
	@Given("^Client in Clerk menu and selected create student$")
	public void client_in_Clerk_menu_and_selected_create_student() throws Throwable {
		if(ATS.getClientState(ATC.getID()) != InputHandler.CLERK) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "clerk");
			ATS.handle(ATC.getID(), "admin");
			ATS.handle(ATC.getID(), "create student");
	    } else {
	    	ATS.handle(ATC.getID(), "create student");
	    }
	}

	@Given("^Student does not exists$")
	public void student_does_not_exists() throws Throwable {
		if(University.getInstance().CheckStudent(111000111) == true)
			University.getInstance().DestroyStudent(University.getInstance().GetStudent(111000111));
	    assertEquals(false, University.getInstance().CheckStudent(111000111));
	}

	@When("^Client submit student info to be created$")
	public void client_submit_student_info_to_be_created() throws Throwable {
	    ATS.handle(ATC.getID(), "111000111, Abdul Jas, y");
	}

	@Then("^Student is successfully created$")
	public void student_is_successfully_created() throws Throwable {
		assertEquals(true, University.getInstance().CheckStudent(111000111));
		//REMOVING STUDETN FOR OTHER PATHS TESTS
		University.getInstance().DestroyStudent(University.getInstance().GetStudent(111000111));
		ATS.handle(ATC.getID(), "main menu");
		
	}
	@Then("^Student is unsuccessfully created$")
	public void student_is_unsuccessfully_created() throws Throwable {
		assertEquals(false, University.getInstance().CheckStudent(111000111));
	}
	@Given("^Student does exists$")
	public void student_does_exists() throws Throwable {
	    University.getInstance().CreateStudent(111000111, "Abdul Jas", true);
	}

	@Then("^Student is unsuccessfully created, but exists$")
	public void student_is_unsuccessfully_created_but_exists() throws Throwable {
		assertEquals(true, University.getInstance().CheckStudent(111000111));
	}
	
	//----------- UC7
	
	@Given("^Client in Clerk menu and selected delete student$")
	public void client_in_Clerk_menu_and_selected_delete_student() throws Throwable {
		if(ATS.getClientState(ATC.getID()) != InputHandler.CLERK) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "clerk");
			ATS.handle(ATC.getID(), "admin");
			ATS.handle(ATC.getID(), "delete student");
	    } else {
	    	ATS.handle(ATC.getID(), "delete student");
	    }
	}

	@When("^Client submit student info to be deleted$")
	public void client_submit_student_info_to_be_deleted() throws Throwable {
		ATS.handle(ATC.getID(), "111000111");
	}

	@Then("^Student is deleted successfully$")
	public void student_is_deleted_successfully() throws Throwable {
	    assertEquals(false, University.getInstance().CheckStudent(111000111));
	}
	
	@Then("^Student isn't deleted$")
	public void student_isn_t_deleted() throws Throwable {
		assertEquals(true, University.getInstance().CheckStudent(111000111));
	}
	@Then("^Student isn't deleted and informed about non-existence$")
	public void student_isn_t_deleted_and_informed_about_non_existence() throws Throwable {
	    //This must be checked using logs to preview the servers respond
	}
	
	//-------------------- UC8
	
	@Given("^Student has been created by Clerk$")
	public void student_has_been_created_by_Clerk() throws Throwable {
		University.getInstance().CreateStudent(111000111, "Abdul Jas", true);
	}

	@Given("^Client in Student login$")
	public void client_in_Student_login() throws Throwable {
		ATS.handle(ATC.getID(), "hi");
		ATS.handle(ATC.getID(), "student");
	}

	@When("^Client input correct Student info$")
	public void client_input_correct_Student_info() throws Throwable {
		ATS.handle(ATC.getID(), "111000111, Abdul Jas");
	}

	@Then("^Client logs in to student menu$")
	public void client_logs_in_to_student_menu() throws Throwable {
	    assertEquals(InputHandler.STUDENT, ATS.getClientState(ATC.getID()));
	}
	
	@When("^Client input incorrect Student info$")
	public void client_input_incorrect_Student_info() throws Throwable {
		ATS.handle(ATC.getID(), "111010111, Abdul Jas");
	}

	@Then("^Client is prompted for login again$")
	public void client_is_prompted_for_login_again() throws Throwable {
		assertEquals(InputHandler.STUDENTLOGIN, ATS.getClientState(ATC.getID()));
	}

	//-------------- UC9
	
	@Given("^Client logged in as Student$")
	public void client_logged_in_as_Student() throws Throwable {
		ATS.handle(ATC.getID(), "hi");
		ATS.handle(ATC.getID(), "student");
		ATS.handle(ATC.getID(), "101075401, tom");
	}

	@When("^Client in Student Menu selects log out$")
	public void client_in_Student_Menu_selects_log_out() throws Throwable {
		ATS.handle(ATC.getID(), "log out");
	}
	
	//-------------- UC10
	
	@Given("^Client in Student menu and has courses selected$")
	public void client_in_Student_menu_and_has_courses_selected() throws Throwable {
		University.getInstance().CreateStudent(111000111, "Abdul Jas", true);
		if(ATS.getClientState(ATC.getID()) != InputHandler.STUDENT) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "student");
			ATS.handle(ATC.getID(), "111000111, Abdul Jas");
			ATS.handle(ATC.getID(), "select course");
			ATS.handle(ATC.getID(), "105104");
	    } else {
	    	ATS.handle(ATC.getID(), "select course");
			ATS.handle(ATC.getID(), "105104");
	    }
	}

	@Given("^Client in student menu and selected register for coruse$")
	public void client_in_student_menu_and_selected_register_for_coruse() throws Throwable {
		if(ATS.getClientState(ATC.getID()) != InputHandler.STUDENT) {
	    	ATS.handle(ATC.getID(), "main menu");
	    	ATS.handle(ATC.getID(), "register for course");
	    } else {
	    	ATS.handle(ATC.getID(), "register for course");
	    }
	}

	@When("^Client enters the code of the selected course to register$")
	public void client_enters_the_code_of_the_selected_course_to_register() throws Throwable {
		ATS.handle(ATC.getID(), "105104");
	}

	@Then("^Student Successfully registered$")
	public void student_Successfully_registered() throws Throwable {
		assertEquals(true,University.getInstance().GetStudent(111000111).IsRegistered(University.getInstance().GetCourse(105104)));
		University.getInstance().GetStudent(111000111).DeRegisterCourse(University.getInstance().GetCourse(105104));
	}

	@Then("^Student unsuccessfully registered$")
	public void student_unsuccessfully_registered() throws Throwable {
		assertEquals(false,University.getInstance().GetStudent(111000111).IsRegistered(University.getInstance().GetCourse(105104)));
	}
	
	// ------------ UC 11
	
	@Given("^Client in student menu and selected deregister course$")
	public void client_in_student_menu_and_selected_deregister_course() throws Throwable {
		//University.getInstance().CreateStudent(111000111, "Abdul Jas", true); //-- NOT NEEDED
		if(ATS.getClientState(ATC.getID()) != InputHandler.STUDENT) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "student");
			ATS.handle(ATC.getID(), "111000111, Abdul Jas");
			ATS.handle(ATC.getID(), "deregister course");
	    } else {
	    	ATS.handle(ATC.getID(), "deregister course");
	    }
	}

	@Given("^Student registered in course to be dropped$")
	public void student_registered_in_course_to_be_dropped() throws Throwable {
		University.getInstance().CreateStudent(111000111, "Abdul Jas", true);
		University.getInstance().GetStudent(111000111).SelectCourse(University.getInstance().GetCourse(105104));
		University.getInstance().GetStudent(111000111).RegisterCourse(University.getInstance().GetCourse(105104));
		
	}

	@When("^Client enters the code of the course to deregister$")
	public void client_enters_the_code_of_the_course_to_deregister() throws Throwable {
		ATS.handle(ATC.getID(), "105104");
	}

	@Then("^Student successfully deregister from course$")
	public void student_successfully_deregister_from_course() throws Throwable {
		assertEquals(false,University.getInstance().GetStudent(111000111).IsRegistered(University.getInstance().GetCourse(105104)));
		
	}
	
	@Then("^Student unsuccessfully deregister from course$")
	public void student_unsuccessfully_deregister_from_course() throws Throwable {
		assertEquals(true,University.getInstance().GetStudent(111000111).IsRegistered(University.getInstance().GetCourse(105104)));
		
	}
	
	//-------------- UC 12
	
	@Given("^Client has selected list of courses$")
	public void client_has_selected_list_of_courses() throws Throwable {
		University.getInstance().CreateStudent(111000111, "Abdul Jas", true);
		University.getInstance().GetStudent(111000111).SelectCourse(University.getInstance().GetCourse(105104));
	}

	@Given("^Client in student menu and selected drop course$")
	public void client_in_student_menu_and_selected_drop_course() throws Throwable {
		if(ATS.getClientState(ATC.getID()) != InputHandler.STUDENT) {
	    	ATS.handle(ATC.getID(), "hi");
			ATS.handle(ATC.getID(), "student");
			ATS.handle(ATC.getID(), "111000111, Abdul Jas");
			ATS.handle(ATC.getID(), "drop course");
	    } else {
	    	ATS.handle(ATC.getID(), "drop course");
	    }
	}

	@When("^Client enters course to be dropped$")
	public void client_enters_course_to_be_dropped() throws Throwable {
		ATS.handle(ATC.getID(), "105104");
	}

	@Then("^Course removed from student selected list$")
	public void course_removed_from_student_selected_list() throws Throwable {
		assertEquals(false, University.getInstance().GetStudent(111000111).IsSelected(University.getInstance().GetCourse(105104)));
	}

	@Then("^Course isn't removed from student selected list$")
	public void course_isn_t_removed_from_student_selected_list() throws Throwable {
		assertEquals(true, University.getInstance().GetStudent(111000111).IsSelected(University.getInstance().GetCourse(105104)));
		
	}
	
}
