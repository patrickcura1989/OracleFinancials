import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.formsFT.api.*;
import oracle.oats.scripting.modules.applet.api.*;
import lib.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;
	@FunctionLibrary("OracleFinancialsLibrary") lib.com.acc.OracleFinancialsLibrary oracleFinancialsLibrary;

	public void initialize() throws Exception {
		browser.launch() ;
	}

	/**
	 * Add code to be executed each iteration for this virtual user.
	 */
	public void run() throws Exception {
		oracleFinancialsLibrary.web_login("TANK", "may123456") ;
		oracleFinancialsLibrary.web_navigateToLink("ACC Payables User|Payments|Entry|Payments");
		oracleFinancialsLibrary.forms_waitForOracleFormsReady(120, 5);
		
		oracleFinancialsLibrary.forms_invokeResponsibility();
		oracleFinancialsLibrary.forms_selectResponsibility("CORP Payables Manager");
		
		oracleFinancialsLibrary.forms_selectNavigator_function(Forms_Navigator.OTHER);
		oracleFinancialsLibrary.forms_selectNavigator_function(Forms_Navigator.OTHER_REQUESTS);
		oracleFinancialsLibrary.forms_selectNavigator_function(Forms_Navigator.OTHER_REQUESTS_RUN);
		oracleFinancialsLibrary.forms_clickButton(Forms_Submit_New_Request.OK_BUTTON);
		
		oracleFinancialsLibrary.forms_setTextField(Forms_Submit_Request.PROGRAM_NAME, 0, "Create Accounting");
		oracleFinancialsLibrary.forms_setFlexWindow("Ledger", "Accident Compensation Corp");
		oracleFinancialsLibrary.forms_setFlexWindow("End Date", "30-04-2017");
		oracleFinancialsLibrary.forms_setFlexWindow("General Ledger Batch Name", "MTCashMgmtToGL01");
		oracleFinancialsLibrary.forms_flexWindowClickOK();
		
		oracleFinancialsLibrary.forms_clickButton(Forms_Submit_Request.SUBMIT_BUTTON);

		String req_id = oracleFinancialsLibrary.forms_getSubmitRequestIDByIndex(Forms_Requests.JOB_JD, 0) ;
		this.getLogger().info("request id : " + req_id) ;
		oracleFinancialsLibrary.forms_waitForStatusChangedByRequestID(Forms_Requests.JOB_PHASE_STATUS, req_id, 120, 10, "Completed") ;
		
		oracleFinancialsLibrary.forms_closeWindow("JOBS");
		
		oracleFinancialsLibrary.forms_invokeResponsibility();
		oracleFinancialsLibrary.forms_selectResponsibility("CORP General Ledger Superuser");
		
		oracleFinancialsLibrary.forms_selectNavigator_function("Journals");
		oracleFinancialsLibrary.forms_selectNavigator_function("Journals|Post");
		
		
	}

	public void finish() throws Exception {
	}
}
