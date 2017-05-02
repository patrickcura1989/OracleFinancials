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
		
		oracleFinancialsLibrary.forms_setTextField(Forms_Payments.BANK_ACCONT, 0, "ACC Corporate Account") ;
		oracleFinancialsLibrary.forms_setTextField(Forms_Payments.PAYMENT_DATE, 0, "18-04-2017") ;
		oracleFinancialsLibrary.forms_setTextField(Forms_Payments.SUPPLIER_NAME, 0, "SOLNET SOLUTIONS LTD") ;
		oracleFinancialsLibrary.forms_setTextField(Forms_Payments.PAYMENT_METHOD, 0, "Check") ;
		oracleFinancialsLibrary.forms_setTextField(Forms_Payments.PAYMENT_PROCESS_PROFILE, 0, "ACC Corporate Print Cheque") ;
		
		oracleFinancialsLibrary.forms_setTextField(Forms_Payments.PAYMENT_PROCESS_PROFILE, 0, "ACC Corporate Print Cheque") ;
		oracleFinancialsLibrary.forms_clickButton(Forms_Payments.ENTER_ADJUST_INVOICES);
		
		oracleFinancialsLibrary.forms_setTextField("***CHANGE_ME_INVOICE_NUMBER***", 0, "MT INVOICE4") ;
		oracleFinancialsLibrary.forms_save("***CHANGE_ME_FORM_NAME***");
		oracleFinancialsLibrary.forms_closeWindow("***CHANGE_ME_SELECT_INVOICES_WINDOW***");
		oracleFinancialsLibrary.forms_closeWindow("***CHANGE_ME_PAYMENTS_WINDOW***");
		
		oracleFinancialsLibrary.forms_invokeResponsibility();
		oracleFinancialsLibrary.forms_selectResponsibility("CORP Cash Management User");
		oracleFinancialsLibrary.forms_selectNavigator_function("Bank Statements");
		oracleFinancialsLibrary.forms_selectNavigator_function("Bank Statements|Bank Statements and Reconciliation");
		
		oracleFinancialsLibrary.forms_clickButton("//forms:button[(@name='CONTROL_FOLDER_REVIEW_B_0')]");
		
		oracleFinancialsLibrary.forms_clickButton("//forms:button[(@name='CONTROL_HEADERS_LINES_B_0')]");
		
		// TO DO: Scroll to line number 16 and input 3440987 under the Number column
		
		oracleFinancialsLibrary.forms_save("***CHANGE_ME_FORM_NAME***");
		oracleFinancialsLibrary.forms_closeWindow("***CHANGE_ME_BANK_STATEMENT_LINES_WINDOW***");
		oracleFinancialsLibrary.forms_closeWindow("***CHANGE_ME_BANK_STATEMENT_WINDOW***");
		
		// TO DO: Tools > Auto Reconciliation
		
		oracleFinancialsLibrary.forms_clickButton("***CHANGE_ME_SUBMIT_BUTTON***");
		oracleFinancialsLibrary.forms_clickButton("***CHANGE_ME_OK_BUTTON***");
		oracleFinancialsLibrary.forms_clickButton("***CHANGE_ME_OK_BUTTON***");
		oracleFinancialsLibrary.forms_closeWindow("***CHANGE_ME_RECONCILE_BANK_STATEMENTS_WINDOW***");
		
		oracleFinancialsLibrary.forms_selectNavigator_function("Other");
		oracleFinancialsLibrary.forms_selectNavigator_function("Other|Requests");
		oracleFinancialsLibrary.forms_clickButton(Forms_Find_Requests.FIND_BUTTON);	
		
		oracleFinancialsLibrary.forms_clickButton("***CHANGE_ME_VIEW_OUTPUT_BUTTON***");
		
		// TO DO: Assert on Web Page opened
		
		oracleFinancialsLibrary.forms_closeWindow("JOBS");
		oracleFinancialsLibrary.forms_selectNavigator_function("Bank Statements|Bank Statements and Reconciliation");
		
		oracleFinancialsLibrary.forms_clickButton("//forms:button[(@name='CONTROL_FOLDER_REVIEW_B_0')]");		
		oracleFinancialsLibrary.forms_clickButton("//forms:button[(@name='CONTROL_HEADERS_LINES_B_0')]");
	}

	public void finish() throws Exception {
	}
}
