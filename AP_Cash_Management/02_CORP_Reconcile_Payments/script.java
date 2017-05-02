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
		oracleFinancialsLibrary.forms_selectResponsibility("CORP Cash Management User");
		
		oracleFinancialsLibrary.forms_selectNavigator_function("Bank Statements");
		oracleFinancialsLibrary.forms_selectNavigator_function("Bank Statements|Bank Statements and Reconciliation");
		oracleFinancialsLibrary.forms_clickButton("//forms:button[(@name='CONTROL_FOLDER_REVIEW_B_0')]");
		
		oracleFinancialsLibrary.forms_clickButton("//forms:button[(@name='CONTROL_HEADERS_LINES_B_0')]");
				
		oracleFinancialsLibrary.forms_closeWindow("LINES");
		oracleFinancialsLibrary.forms_closeWindow("HEADERS");
		oracleFinancialsLibrary.forms_closeWindow("HEADERS_FOLDER");
		
		oracleFinancialsLibrary.forms_invokeResponsibility();
		oracleFinancialsLibrary.forms_selectResponsibility("CORP Payables Manager");
		
		oracleFinancialsLibrary.forms_selectNavigator_function("Invoices");
		oracleFinancialsLibrary.forms_selectNavigator_function("Invoices|Entry");
		oracleFinancialsLibrary.forms_selectNavigator_function("Invoices|Entry|Invoices");
		
		oracleFinancialsLibrary.forms_query_mode(Constant.INVOICES_MAIN_WINDOW);		
		oracleFinancialsLibrary.forms_setTextField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_NUM_%')]", 0, "MT INVOICE3") ;		
		oracleFinancialsLibrary.forms_run(Constant.INVOICES_MAIN_WINDOW);
		
		oracleFinancialsLibrary.forms_query_mode(Constant.INVOICES_MAIN_WINDOW);		
		oracleFinancialsLibrary.forms_setTextField("//forms:textField[(@name='INV_SUM_FOLDER_INVOICE_NUM_%')]", 0, "MT INVOICE4") ;		
		oracleFinancialsLibrary.forms_run(Constant.INVOICES_MAIN_WINDOW);
		
		oracleFinancialsLibrary.forms_closeWindow("INVOICES_SUM_FOLDER_WINDOW");
				
		oracleFinancialsLibrary.forms_selectNavigator_function("Payments");
		oracleFinancialsLibrary.forms_selectNavigator_function("Payments|Entry");
		oracleFinancialsLibrary.forms_selectNavigator_function("Payments|Entry|Payments");
		
		
	}

	public void finish() throws Exception {
	}
}
