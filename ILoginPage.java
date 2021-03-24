package property;

import utility.WebPageElements;

public interface ILoginPage {
	String TXT_USERNAME = "//*[@name='USER']";
	WebPageElements txt_username = new WebPageElements("Username", "xpath", TXT_USERNAME);

	String TXT_PASSWORD = "//*[@name='PASSWORD']";
	WebPageElements txt_password = new WebPageElements("Password", "xpath", TXT_PASSWORD);

	String BTN_SIGNON = "//*[@value='Sign On']";
	WebPageElements btn_signon = new WebPageElements("Sign On", "xpath", BTN_SIGNON);

	String DDL_SOEID = "//*[@id='SM_USER']";
	WebPageElements ddl_SOEID = new WebPageElements("SOEID", "xpath", DDL_SOEID);

	String BTN_LOGIN = "//*[@value='login']";
	WebPageElements btn_login = new WebPageElements("Login", "xpath", BTN_LOGIN);

	String BTN_OVERLAY = "(//span[text()='CitiRisk RWA Aggr Overlays'])[3]";
	WebPageElements btn_overylay = new WebPageElements("CitiRisk RWA Aggr Overlays", "xpath", BTN_OVERLAY);

	 String Summary_Relationship = "//span[text()='Summary']";
	WebPageElements summary_relationship_option = new WebPageElements("Summary", "xpath", Summary_Relationship);
		
	String Summary_Relationship_EXPAND = "//span[text()='Summary']/preceding-sibling::img[2]";
	WebPageElements summary_relationship_option_expand = new WebPageElements("Relationship option Summary expand", "xpath", Summary_Relationship_EXPAND);

}

