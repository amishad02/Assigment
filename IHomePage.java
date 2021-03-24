package property;

import org.openqa.selenium.By;

import utility.WebPageElements;

public interface IHomePage {

	String Summary_Relationship = "(//span[text()='Summary']//following::span[text()='Relationship'])[3]";
	WebPageElements summary_relationship_option = new WebPageElements("Relationship option Summary", "xpath",
			Summary_Relationship);

	String ReportingPeriod_DDL = "(//span[text()='Reporting Period']//following::div[contains(@id,'trigger-picker')])[1]";
	WebPageElements reportinhperiod_ddl = new WebPageElements("Reporting Period Trigger Picker", "xpath",
			ReportingPeriod_DDL);

	String BusinessDay_DDL1 = "(//span[text()='Business Day']//following::div[contains(@id,'trigger-picker')])[1]";
	WebPageElements businessday_ddl1 = new WebPageElements("Business Day Trigger Picker", "xpath", BusinessDay_DDL1);

	String Filter_Btn = "//span[text()='Filter']";
	WebPageElements filter_btn = new WebPageElements("Filter Button", "xpath", Filter_Btn);

	String Search_Btninside = "//span[text()='Search']";
	WebPageElements search_btninside = new WebPageElements("Search Button", "xpath", Search_Btninside);

	String EXPAND_BTN1 = "(//img[contains(@class,'x-tree-expander')])[1]";
	WebPageElements expand_btn1 = new WebPageElements("Expand Button Summary", "xpath", EXPAND_BTN1);

	String EXPAND_BTN = "(//img[contains(@class,'x-tree-expander')])[2]";
	WebPageElements expand_btn = new WebPageElements("Work-Stream Drill-Down [Exposure]Expand Button", "xpath",
			EXPAND_BTN);

	String EXPOSURE_RELATIONSHIP = "//span[text()='Work-Stream Drill-Down [Exposure]']//following::span[text()='Relationship']";
	WebPageElements exposure_relationship_option = new WebPageElements("Relationship option Exposure", "xpath",
			EXPOSURE_RELATIONSHIP);

	String CAGID_DDL = "//input[@name='searchCagid']";
	WebPageElements cagid_ddl = new WebPageElements("CAGID", "xpath", CAGID_DDL);

	String GEOGRAPHY_DDL = "//div[input[@name='displayManagedGeography']]/following-sibling::div[2]";
	WebPageElements geography_ddl = new WebPageElements("Managed Geography", "xpath", GEOGRAPHY_DDL);

	String GEOGRAPHY_EXPAND = "//span[text()='Total Citi Geography']/preceding-sibling::img[2]";
	WebPageElements geography_expand = new WebPageElements("Total Citi Geography Expand", "xpath", GEOGRAPHY_EXPAND);

	String APPLY_BTN = "//span[text()='Apply']";
	WebPageElements apply_btn = new WebPageElements("Apply", "xpath", APPLY_BTN);

	String BUSINESS_ORG_DDL = "(//div[input[@name='displayBusinessOrganization']]/following-sibling::div)[2]";
	WebPageElements business_org_ddl = new WebPageElements("Business Organization", "xpath", BUSINESS_ORG_DDL);

	String BUSINESS_ORG_DDL2 = "//*[@name='displayBusinessOrganization']//div";
	WebPageElements business_org_ddl2 = new WebPageElements("Business Organization text", "xpath", BUSINESS_ORG_DDL2);

	String RELATIONSHIP_MANAGED_DDL = "//div[input[@name='displayRiskManagementIndustry']]/following-sibling::div[2]";
	WebPageElements relationship_managed_ddl = new WebPageElements("Relationship Managed Industry", "xpath",
			RELATIONSHIP_MANAGED_DDL);

	String MANAGED_SEGMENT_DDL = "//div[input[@name='displayManagedSegment']]/following-sibling::div[2]";
	WebPageElements managed_segment_ddl = new WebPageElements("Managed Segment", "xpath", MANAGED_SEGMENT_DDL);

	String MANAGED_EXPAND = "//span[text()='Total Citi [L1]']/preceding-sibling::img[2]";
	WebPageElements managed_expand = new WebPageElements("Managed Segment", "xpath", MANAGED_EXPAND);

	String ACCOUNTING_TREATMENT_DDL = "(//div[input[@name='displayAccountingTreatment']]/following-sibling::div)[2]";
	WebPageElements accounting_treatment_ddl = new WebPageElements("Accounting Treatment", "xpath",
			ACCOUNTING_TREATMENT_DDL);

	String VIEWOPTIONS = "//table[contains(@class,'x-grid-item')]//span";
	WebPageElements viewOptions = new WebPageElements("Views", "xpath", VIEWOPTIONS);

	String VIEWOPTIONS2 = "//table[contains(@class,'x-grid-item')]//*[@class=' x-tree-icon x-tree-icon-parent ']";
	WebPageElements viewOptions2 = new WebPageElements("Views", "xpath", VIEWOPTIONS2);

	String SUMMARYCOLLAPSE = "(//img[contains(@class,'x-tree-expander')])[1]";
	WebPageElements summaryCollapse = new WebPageElements("Summary Collapse Button", "xpath", SUMMARYCOLLAPSE);

	String DATAVISUALIZATIONHEADER = "//div[text()='Data Visualization Criteria']";
	WebPageElements dataVisualizationHeader = new WebPageElements("Data Visualization Criteria Header", "xpath",
			DATAVISUALIZATIONHEADER);

	String SCCLOptionsHEADER = "//div[text()='SCCL Options']";
	WebPageElements sccloptionsheader = new WebPageElements("SCCL Options HEADER ", "xpath", SCCLOptionsHEADER);

	String DATAVISUALIZATIONFIELDS = "//div[contains(@class,'x-abs-layout-item')]//*[contains(@class,'x-title-text') or contains(@class,'x-form-item-label-inner')]";
	WebPageElements dataVisualizationFields = new WebPageElements("Data Visualization Criteria filter", "xpath",
			DATAVISUALIZATIONFIELDS);

	String DATAVISUALIZATIONFIELDSNew = "(//div[contains(@class,'x-abs-layout-ct x-panel-body-citiriskfixedpanel')])[1]//div[contains(@class,'x-abs-layout-item')]//*[contains(@class,'x-title-text') or contains(@class,'x-form-item-label-inner')]";
	WebPageElements dataVisualizationFieldsnew = new WebPageElements("Data Visualization Criteria filter new", "xpath",
			DATAVISUALIZATIONFIELDSNew);

	String DATAVISUALIZATIONFIELDSONLY = "(//div[contains(@class,'x-panel-body x-panel-body-citiriskfixedpanel x-abs-layout-ct x-panel-body-citiriskfixedpanel')])[1]//*[contains(@class,'x-title-text') or contains(@class,'x-form-item-label-inner')]";
	WebPageElements dataVisualizationFieldsonly = new WebPageElements("Data Visualization Criteria filter", "xpath",
			DATAVISUALIZATIONFIELDSONLY);

	String SUMMARYFIELDSONLY = "(//div[contains(@class,'x-panel-body x-panel-body-citiriskfixedpanel x-abs-layout-ct x-panel-body-citiriskfixedpanel')])[2]//*[contains(@class,'x-title-text') or contains(@class,'x-form-item-label-inner')]";
	WebPageElements summaryfieldsonly = new WebPageElements("Data Visualization Criteria filter", "xpath",
			SUMMARYFIELDSONLY);

	String ExposureSUMMARYFIELD = "//div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
	WebPageElements exposuresummaryfield = new WebPageElements("Exposure Summary filter", "xpath",
			ExposureSUMMARYFIELD);

	String CLEARALL = "//span[text()='Clear All']";
	WebPageElements clearAll = new WebPageElements("Clear All", "xpath", CLEARALL);

	String REPORTING_PERIOD_DDL = "//input[@name='fiscalYearAccountingPeriod']";
	WebPageElements reporting_period_ddl = new WebPageElements("Reporting Period", "xpath", REPORTING_PERIOD_DDL);

	String SUMMARYFIELDS = "//div[contains(text(),'Summary')]/ancestor::div[contains(@class,'x-panel-header')]/following-sibling::div//span";
	WebPageElements summaryFields = new WebPageElements("Summary Fields", "xpath", SUMMARYFIELDS);

	String RISKMETRIC_DDL = "(//span[text()='Risk Metric']//following::div[contains(@id,'trigger-picker')])[1]";
	WebPageElements riskmetric_ddl = new WebPageElements("Risk Metric Trigger Picker", "xpath", RISKMETRIC_DDL);

	String SortName = "(//input[contains(@name,'textfield')])[2]";
	WebPageElements sortname = new WebPageElements("Sort Name Filter 2", "xpath", SortName);

	String SortName1 = "(//input[contains(@name,'textfield')])[1]";
	WebPageElements sortname1 = new WebPageElements("Sort Name Filter 1", "xpath", SortName1);

	String SortName2 = "(//input[contains(@name,'textfield')])[3]";
	WebPageElements sortname2 = new WebPageElements("Sort Name Filter 3", "xpath", SortName2);

	// CCAR
	String RunDescription_DDL = "(//span[text()='Run Description']//following::div[contains(@id,'trigger-picker')])[1]";
	WebPageElements rundescription_ddl = new WebPageElements("Run Description Trigger Picker", "xpath",
			RunDescription_DDL);

	// Operational Reports

	String EXPAND_OPTReport = "(//img[contains(@class,'x-tree-expander')])[3]";
	WebPageElements expand_optreport = new WebPageElements("Operational Reports Button", "xpath", EXPAND_OPTReport);

	String Blue_Button = "//a[contains(@class,'x-abs-layout-item x-btn-primarybutton-small')]";
	WebPageElements blue_button = new WebPageElements("Primary Button", "xpath", Blue_Button);

	String SubField = "//a[@class='x-tab x-unselectable x-box-item x-tab-secondary-tabs-elements x-top x-tab-top x-tab-secondary-tabs-elements-top']";
	WebPageElements subfield = new WebPageElements("Sub table feild filter", "xpath", SubField);

	String AllTableFIELD = "//div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']//span//span//span";
	WebPageElements alltablefeild = new WebPageElements("All table feild filter", "xpath", AllTableFIELD);

	String AllFIELRiskMeasure = "//div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']//span//span//span";
	WebPageElements allfeilriskmeasure = new WebPageElements("All field filter", "xpath", AllFIELRiskMeasure);

	String TableFieldFilterHeader = " //label[contains(@class,'x-component custom-header x-box-item x-component-default x-component-before-title')]";
	WebPageElements tablefieldfilterheader = new WebPageElements("Table field filter header", "xpath",
			TableFieldFilterHeader);
	String FilterCiretia = "//div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']//span//span//span";
	WebPageElements filterciretia = new WebPageElements("All feild filter", "xpath", FilterCiretia);

	String INPUTTEXTSORT = "(//input[contains(@name,'textfield')])";
	WebPageElements inputtextsort = new WebPageElements("Input text field for Sort and Filter", "xpath", INPUTTEXTSORT);

	String INPUTNumberSORT = "(//input[contains(@name,'numberfield')])";
	WebPageElements inputnumbersort = new WebPageElements("Input text field for Sort and Filter", "xpath",
			INPUTNumberSORT);

	String SORTGETTEXT = "(//*[@class='x-grid-cell-inner '])";
	WebPageElements sortgettext = new WebPageElements("Sort and Get table text", "xpath", SORTGETTEXT);

	// Demand
	String EXPAND_BTNOPReport = "(//img[contains(@class,'x-tree-expander')])[3]";
	WebPageElements expand_btnopreport = new WebPageElements("Operational Reports Expand Button", "xpath",
			EXPAND_BTNOPReport);

	String EXPAND_BTNTools = "(//img[contains(@class,'x-tree-expander')])[4]";
	WebPageElements expand_btnotools = new WebPageElements("Tools Expand Button", "xpath", EXPAND_BTNTools);

	String DemandRevHeader = "//label[text()='On Demand Revaluation Request']";
	WebPageElements demandrevheader = new WebPageElements("On Demand Revaluation Request Header", "xpath",
			DemandRevHeader);

	String ONDEMANDREQUESTFIELDSONLY = "(//label[text()='On Demand Revaluation Request']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//following-sibling::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
	WebPageElements ondemandrequestfieldsonly = new WebPageElements("On Demand Revaluation Request Criteria filter",
			"xpath", ONDEMANDREQUESTFIELDSONLY);

	/*
	 * String ONDEMANDREQUESTFIELDSONLY =
	 * "(//div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//div[contains(@class,'x-box-item x-column-header-default x-unselectable')]"
	 * ; WebPageElements ondemandrequestfieldsonly = new
	 * WebPageElements("On Demand Revaluation Request Criteria filter", "xpath",
	 * ONDEMANDREQUESTFIELDSONLY);
	 */

	String DemandTransactionsHeader = "//label[text()='On Demand BAU Transactions']";
	WebPageElements demandtransactionsheader = new WebPageElements("On Demand BAU Transactions Header", "xpath",
			DemandTransactionsHeader);

	String DemandTransactionsFIELDSONLY = "(//label[text()='On Demand BAU Transactions']//following::div[@class='x-grid-header-ct x-docked x-grid-header-ct-default x-docked-top x-grid-header-ct-docked-top x-grid-header-ct-default-docked-top x-box-layout-ct'])[1]//following-sibling::div[contains(@class,'x-column-header grid-header-ellipses-cls x-column-header') and contains(@style,'auto') and not(contains(@style,'display: none'))]//div[@class='x-column-header-inner x-leaf-column-header']";
	WebPageElements demandtransactionsfieldsonly = new WebPageElements("On Demand BAU Transactions Criteria filter",
			"xpath", DemandTransactionsFIELDSONLY);

	String ONDEMANDCRETEREQUEST = "//div[text()='ON DEMAND CREATE REQUEST']";
	WebPageElements ondemandcreaterequest = new WebPageElements("ON DEMAND CREATE REQUEST", "xpath",
			ONDEMANDCRETEREQUEST);

	String LabelCreateRequest = "//label[contains(@class,'mandatoryLabel')]";
	WebPageElements labelcreaterequest = new WebPageElements("Label for on DEMAND CREATE REQUEST", "xpath",
			LabelCreateRequest);
	// What if

	String AddSCENARIOName = "//div/input[@name='scenarioName']";
	WebPageElements addscenarioname = new WebPageElements(" Scenario Name", "xpath", AddSCENARIOName);

	String AddDesc = "//div/input[@name='desc']";
	WebPageElements adddesc = new WebPageElements(" Description", "xpath", AddDesc);

	String SearchInput = "//div/input[@name='searchInput']";
	WebPageElements searchinput = new WebPageElements(" Search Input", "xpath", SearchInput);

	String SearchButton = "//span[@class='x-btn-icon-el x-btn-icon-el-primarybutton-small  ']//following::span[text()='Search']";
	WebPageElements searchbutton = new WebPageElements("Search Button", "xpath", SearchButton);

	String SelectButton = "//span[@class='x-btn-icon-el x-btn-icon-el-primarybutton-small  ']//following::span[text()='Select']";
	WebPageElements selectbutton = new WebPageElements(" Select Button", "xpath", SelectButton);

	String SortScenarioSearchResultid = "(//div[text()='SEARCH SCENARIO']//following::span[text()='Scenario ID']//following::input)[1]";
	WebPageElements sortscenariosearchresultid = new WebPageElements("Sort/filter Scenario Search Result", "xpath",
			SortScenarioSearchResultid);

	String SortScenarioSearchResultname = "(//div[text()='SEARCH SCENARIO']//following::span[text()='Name']//following::input)[1]";
	WebPageElements sortscenariosearchresultname = new WebPageElements("Sort/filter Scenario Search Result", "xpath",
			SortScenarioSearchResultname);

	String SortScenarioSearchResultnamebbk = "(//div[text()='SEARCH SCENARIO']//following::span[text()='Name']//following::input)[3]";
	WebPageElements sortscenariosearchresultnamebbk = new WebPageElements("Sort/filter Scenario Search Result", "xpath",
			SortScenarioSearchResultnamebbk);

	String Save_Btninside = "//span[text()='SAVE' or text()='Save' ]";
	WebPageElements save_btninside = new WebPageElements("Save button under Edit Scenario", "xpath", Save_Btninside);

	String Save_Btninsidecap = "//span[text()='save']";
	WebPageElements save_btninsidecap = new WebPageElements("Save button", "xpath", Save_Btninsidecap);

	String Reset_Btninsidecap = "//span[text()='Reset']";
	WebPageElements reset_btninsidecap = new WebPageElements("Reset button", "xpath", Reset_Btninsidecap);

	String CloseIcon = "//div[@class='x-tool x-box-item x-tool-default x-window-close-btn x-tool-after-title']";
	WebPageElements closeicon = new WebPageElements("Close Icon", "xpath", CloseIcon);

	String CounterPartySearch = "//input[@name='counterpartySearch']";
	WebPageElements counterpartysearch = new WebPageElements("Counterparty Search", "xpath", CounterPartySearch);

	String Add_Btninside = "//span[text()='Add']";
	WebPageElements add_btninside = new WebPageElements("Add button ", "xpath", Add_Btninside);

	String GFCIDInput = "//div/input[@name='searchInput']";
	By GFCIDInputby = By.xpath(GFCIDInput);
	WebPageElements gfcidInput = new WebPageElements(" GFCId Input", "xpath", GFCIDInput);

	String PortoflioInput = "//div/input[@name='portoflioId']";
	By PortoflioInputby = By.xpath(PortoflioInput);
	WebPageElements portoflioinput = new WebPageElements(" portoflioId Input", "xpath", PortoflioInput);

	String COBSelect = "//div[text()='SEARCH']//following::span[text()='COB']//following::div[contains(@id,'trigger-picker')]";
	WebPageElements cobselect = new WebPageElements("Select COB ", "xpath", COBSelect);

	//
	String CloneIconeExp = "(//*[text()='Exposure']//following::div[@class='icon icon-copy'])[1]";
	WebPageElements cloneiconexp = new WebPageElements("Clone icon for ", "xpath", CloneIconeExp);

	String CloneIconeColl = "(//*[text()='Collateral']//following::div[@class='icon icon-copy'])[1]";
	WebPageElements cloneiconcoll = new WebPageElements("Clone icon for ", "xpath", CloneIconeColl);

	String LegalEnforceabilityCertainty = "(//div[text()='Legal Enforceability Certainty']/following::span[text()='Y'])[1]/ancestor::a";
	WebPageElements legalenforceabilitycertainty = new WebPageElements("Legal Enforceability Certainty = Y", "xpath",
			LegalEnforceabilityCertainty);

	String IsFirstLiened = "(//div[text()='Is First Liened']/following::span[text()='Y'])[1]/ancestor::a";
	WebPageElements isfirstliened = new WebPageElements("Is First Liened = Y ", "xpath", IsFirstLiened);

	String SEARCHRESULTS = "(//label[text()='Search Results']//following::td)[4]";
	WebPageElements searchresults = new WebPageElements("Search Results", "xpath", SEARCHRESULTS);

	// Basel OTC

	String PerspectiveComboBox = "((//div[@class='x-form-trigger-wrap x-form-trigger-wrap-toolbar'])[1]//following::div)[1]";
	WebPageElements perspectivecombbox = new WebPageElements("perspective ComboBox", "xpath", PerspectiveComboBox);

	String Plusicon = "(//*[@class='x-btn-icon-el x-btn-icon-el-primary-tabs-small icon-plus ']/ancestor::a)";
	WebPageElements plus = new WebPageElements("plus", "xpath", Plusicon);

	String SFTPlusicon = "(//*[@class='x-btn-icon-el x-btn-icon-el-primary-tabs-small icon-plus ']/ancestor::a)[3]";
	WebPageElements sftplus = new WebPageElements("plus", "xpath", SFTPlusicon);

	String UserComboBox = "//div[text()='User']/child::a";
	WebPageElements usercombobox = new WebPageElements("User drop down", "xpath", UserComboBox);

	String TEXTBOX = "//*[@name='comboTextNormal']";
	WebPageElements textbox = new WebPageElements("Text Box", "xpath", TEXTBOX);

	String NumaricTEXTBOX = "//*[@name='textFieldNormal']";
	WebPageElements numarictextbox = new WebPageElements("Text Box", "xpath", NumaricTEXTBOX);

	String BETWEENTEXTBOX = "//*[@name='textFieldNormal']";
	WebPageElements betweentextbox = new WebPageElements("Text Box", "xpath", BETWEENTEXTBOX);

	String TEXTBOXIN = "//*[@name='comboTextIn']";
	WebPageElements textboxin = new WebPageElements("Text Box IN", "xpath", TEXTBOXIN);

	String DELETEALL = "//*[text()='Delete All']";
	WebPageElements deleteall = new WebPageElements("DELETEALL Button", "xpath", DELETEALL);

	String OperationPLUSICON = "//*[@name='comboTextIn']/ancestor::div[contains(@class,'filterMenuComboCls')]/following-sibling::a[contains(@class,'icon-plus')]";
	WebPageElements operationplusicon = new WebPageElements("operation plus icon", "xpath", OperationPLUSICON);

	String OPERATIONCombobox = "//*[@name='operationCombo']/parent::div";
	WebPageElements operationcombobox = new WebPageElements("Text Box", "xpath", OPERATIONCombobox);

	String APPLYBTN = "//*[text()='Apply']";
	WebPageElements applybtn = new WebPageElements("Apply Button", "xpath", APPLYBTN);

	String CLEARBTN = "//*[text()='Clear']";
	WebPageElements clearbtn = new WebPageElements("Apply Button", "xpath", CLEARBTN);

	String LEGENDBOX = "//*[@class='x-horizontal x-legend x-box-item x-legend-default']";
	WebPageElements legendbox = new WebPageElements("horizontal legend", "xpath", LEGENDBOX);

	String Topbox = "//*[@class='x-form-field x-form-text x-form-text-default ']/ancestor::div[@class='x-form-item-body x-form-item-body-default x-form-text-field-body x-form-text-field-body-default  ']";
	WebPageElements topbox = new WebPageElements("Search Results", "xpath", Topbox);

	String Applychanges = "//*[@class='x-btn-icon-el x-btn-icon-el-toolbutton-small icon icon-check ']";
	WebPageElements applychanges = new WebPageElements("Apply changes", "xpath", Applychanges);

	String TopUP = "//*[@class='x-form-spinner x-form-spinner-default x-form-spinner-up x-form-spinner-up-default ']";
	WebPageElements topup = new WebPageElements("Search Results", "xpath", TopUP);

	String TopDOWN = "//*[@class='x-form-spinner x-form-spinner-default x-form-spinner-down x-form-spinner-down-default ']";
	WebPageElements topdown = new WebPageElements("Search Results", "xpath", TopDOWN);

	String Filter = "//*[contains(text(),'Exposure Breakdown')]/ancestor::a[contains(@data-qtip,'Exposure Breakdown')]/following-sibling::a[contains(@data-qtip,'Filter')]";
	WebPageElements filter = new WebPageElements("filter icon", "xpath", Filter);

	String FULLtenorwindow = "//*[contains(text(),'Choose one date to view full tenors: ')]";
	WebPageElements fulltenorwindow = new WebPageElements("fulltenor window", "xpath", FULLtenorwindow);

	String OK_BTN = "//*[text()='Ok']";
	WebPageElements ok_btn = new WebPageElements(" Click ok button", "xpath", OK_BTN);

	String OKBTN = "//*[@class='x-btn-primarybutton-small x-btn x-unselectable x-box-item x-toolbar-item']/following::span[text()='Ok']";
	WebPageElements okbtn = new WebPageElements(" Click ok button", "xpath", OKBTN);

	String NEXTpage = "//*[contains(text(),'Exposure Breakdown')]/ancestor::a[@data-qtip='Exposure Breakdown']/following-sibling::div/div/div/a[3]";
	WebPageElements nextpage = new WebPageElements("  nextpage button", "xpath", NEXTpage);

	String LASTpage = "//*[contains(text(),'Exposure Breakdown')]/ancestor::a[@data-qtip='Exposure Breakdown']/following-sibling::div/div/div/a[4]";
	WebPageElements lastpage = new WebPageElements(" Click lastpage button", "xpath", LASTpage);

	String PREVIOUSpage = "//*[contains(text(),'Exposure Breakdown')]/ancestor::a[@data-qtip='Exposure Breakdown']/following-sibling::div/div/div/a[2]";
	WebPageElements previouspage = new WebPageElements(" Click previouspage button", "xpath", PREVIOUSpage);

	String FIRSTpage = "//*[contains(text(),'Exposure Breakdown')]/ancestor::a[@data-qtip='Exposure Breakdown']/following-sibling::div/div/div/a[1]";
	WebPageElements firstpage = new WebPageElements(" Click firstpage button", "xpath", FIRSTpage);

	String PAGEnumberbox = "//*[contains(text(),'Exposure Breakdown')]/ancestor::a[@data-qtip='Exposure Breakdown']/following-sibling::div/div/div/div[contains(@class,'x-field x-tbar-page-number ')]";
	WebPageElements pagenumberbox = new WebPageElements("  profilegraph", "xpath", PAGEnumberbox);

	String PROFILEgraph = "//*[text()='View Profile Graph']";
	WebPageElements profilegraph = new WebPageElements("  profilegraph", "xpath", PROFILEgraph);

	String TIMEserieswindow = "//*[text()='Time Series Analysis']";
	WebPageElements timeserieswindow = new WebPageElements("  timeserieswindow", "xpath", TIMEserieswindow);

	String COMPAREvalueswindow = "//*[contains(text(),'Compare Values')]";
	WebPageElements comparevalues = new WebPageElements("  COMPAREvalueswindow", "xpath", COMPAREvalueswindow);

	String COMPAREprofilewindow = "//*[text()='Compare Profiles']";
	WebPageElements compareprofile = new WebPageElements("  COMPAREvalueswindow", "xpath", COMPAREprofilewindow);

	String MovementAnalysis = "//*[text()='Movement Analysis']";
	WebPageElements movementanalysis = new WebPageElements("  COMPAREvalueswindow", "xpath", MovementAnalysis);

	String View_TradeDetails = "//*[text()='Choose required fields to view trade detail:']";
	WebPageElements view_tradedetails = new WebPageElements("  COMPAREvalueswindow", "xpath", View_TradeDetails);

	String OTC_Transaction = "//*[text()='Choose required fields to view otc transaction:']";
	WebPageElements otc_transaction = new WebPageElements("  COMPAREvalueswindow", "xpath", OTC_Transaction);

	String OPEN_Transaction = "//*[text()='Open Transaction Viewer']";
	WebPageElements open_transaction = new WebPageElements("  COMPAREvalueswindow", "xpath", OPEN_Transaction);

	String CSVtextbox = "(//*[@class='x-form-field x-form-text x-form-text-default ']/ancestor::div[@class='x-form-item-body x-form-item-body-default x-form-text-field-body x-form-text-field-body-default  '])[2]";
	WebPageElements csvtextbox = new WebPageElements("  csv textbox", "xpath", CSVtextbox);
	// Right side Icons tool bar

	String GreenLight = "//*[contains(@class,'icon-matrix')]";
	WebPageElements greenlight = new WebPageElements("GreenLight Icon", "xpath", GreenLight);

	String Adhocanalysis = "//*[contains(@class,'icon-reports')]";
	WebPageElements adhocanalysis = new WebPageElements("Adhocanalysis Icon", "xpath", Adhocanalysis);

	String Adjustments = "//*[contains(@class,'icon-audit')]";
	WebPageElements adjustments = new WebPageElements("Adjustments Icon", "xpath", Adjustments);

	String Operationalreports = "//*[contains(@class,'icon-calculator')]";
	WebPageElements operationalreports = new WebPageElements("Operationalreports Icon", "xpath", Operationalreports);

	String OverlaysGateway = "//*[contains(@class,'icon-inbox')]";
	WebPageElements overlaysgateway = new WebPageElements("OverlaysGateway Icon", "xpath", OverlaysGateway);

	String ManagementReporting = "//*[contains(@class,'icon-governance')]";
	WebPageElements managementreporting = new WebPageElements("ManagementReporting Icon", "xpath", ManagementReporting);

	String BaselView = "//*[contains(@class,'icon-view')]";
	WebPageElements baselview = new WebPageElements("BaselView Icon", "xpath", BaselView);

	String BBTCCAR = "//*[contains(@class,'icon-switch')]";
	WebPageElements BankBookTransformCCAR = new WebPageElements("BankBookTransformCCAR Icon", "xpath", BBTCCAR);

	String DataDictionary = "//*[contains(@class,'icon-text-file')]";
	WebPageElements datadictionary = new WebPageElements("Data Dictionary Icon", "xpath", DataDictionary);

	String IconHelp = "//*[contains(@class,'icon-help')]";
	WebPageElements iconhelp = new WebPageElements("Help Icon", "xpath", IconHelp);

	String SumRelation = "//label[contains(text(),'Relationship')]";
	WebPageElements sumrelation = new WebPageElements("Summary Relationship", "xpath", SumRelation);

	String PerspectiveCombo = "//input[@name='perspectiveCombo']";
	WebPageElements perspectivecombo = new WebPageElements("Perspective Combobox", "xpath", PerspectiveCombo);

	String CostOfCredit = "//*[contains(@class,'icon-simulation')]";
	WebPageElements costofcredit = new WebPageElements("CostOfCredit Icon", "xpath", CostOfCredit);

	String GreenlightFields = "(//div[contains(@class,'x-panel-body x-panel-body-citiriskfixedpanel x-abs-layout-ct x-panel-body-citiriskfixedpanel')])[2]//*[contains(@class,'x-title-text') or contains(@class,'x-form-item-label-inner')]";
	WebPageElements greenlightfields = new WebPageElements("Green Light ", "xpath", GreenlightFields);

//SFT User Portal

	String SFTCOBSelect = "//span[text()='Reporting Period']/parent::label[contains(@class,'mandatoryLabel ')]/following-sibling::div[contains(@class,'text-field-body')]//div[contains(@id,'trigger-picker')]";
	WebPageElements sftcobselect = new WebPageElements("Select COB ", "xpath", SFTCOBSelect);

	String ObligorSearch = "//div/input[@name='obligorCombo']";
	WebPageElements obligorsearch = new WebPageElements(" Obligor ID SearchBox", "xpath", ObligorSearch);

	String NettingAggrement = "//div/input[@name='nettingAggreementIdCombo']";
	WebPageElements nettingaggrement = new WebPageElements(" Obligor ID SearchBox", "xpath", NettingAggrement);

}
