package property;

import utility.WebPageElements;

public interface IHomePage {
	String BtnAddRule = "//span[text()='ADD RULE']";
	WebPageElements btnaddrule = new WebPageElements("Button Add Rule", "xpath", BtnAddRule);
	
	String IconForward = "//i[contains(text(),' arrow_forward_ios')]";
	WebPageElements iconforward = new WebPageElements("Icon Forward", "xpath", IconForward);
	
	String IconClose = "//button[@class='close pf-mat-dialog-close']";
	WebPageElements iconclose = new WebPageElements("Close Icon Add Rule Pop Up", "xpath", IconClose);
	
	String IconAdd = "//i[text()='add_circle']";
	WebPageElements iconadd = new WebPageElements("Add Icon", "xpath", IconAdd);
	
	String BtnClose = "//button[@class='close pf-mat-dialog-close']";//span[text()='Close']";
	WebPageElements btnclose = new WebPageElements("Button Close Pop Up", "xpath", BtnClose);
	
	String IconReview = "//i[text()='assignment_turned_in']";
	WebPageElements iconreview = new WebPageElements("Review Icon", "xpath", IconReview);
	
	String LabelTop10Exposures = "//label[text()=' Top Exposures']";
	WebPageElements labeltop10expsoures = new WebPageElements("Top Exposures pop up header", "xpath", LabelTop10Exposures);
	
	String SideNav = "//i[@class='fa fa-bars']";
	WebPageElements sidenavbutton = new WebPageElements("Side Nav Button", "xpath", SideNav);
	
	String ViewCopyPortfolio = "//strong[text()='View / Copy Portfolio']";
	WebPageElements viewcopyportfolio = new WebPageElements("View Copy Portfolio link", "xpath", ViewCopyPortfolio);
	
	String SavePopUp = "//p[text()='Do you want to save the changes?']";
	WebPageElements savepopup = new WebPageElements("Save confirmation dailog", "xpath", SavePopUp);
	
	String SaveButtonPopUp = "//span[text()='Save']";
	WebPageElements savebuttonpopup = new WebPageElements("Save Button confirmation dailog", "xpath", SaveButtonPopUp);
		
	String NoThnksButtonPopUp = "//span[text()='No Thanks']";
	WebPageElements nothnksbuttonpopup = new WebPageElements("Save Button confirmation dailog", "xpath", NoThnksButtonPopUp);
	
	String PortfoliosHeader = "//label[contains(text(),'Portfolios')]";
	WebPageElements porfoliosheader = new WebPageElements("Portfolios Header", "xpath", PortfoliosHeader);
	
	String FilterSearch = "//div[@class='pf-dialog-searchIcon']//input";
	WebPageElements filtersearch = new WebPageElements("Filter Search", "xpath", FilterSearch);
	
	String ArrowBack = "//i[contains(text(),'arrow_back')]";
	WebPageElements arrowback = new WebPageElements("Arrow Back", "xpath", ArrowBack);
	
	String BuildPortfolioHeader = "//div[contains(text(),'Build your portfolio')]";
	WebPageElements buildportfolioheader = new WebPageElements("Build Portfolio Header", "xpath", BuildPortfolioHeader);
	
	String PageNumTextbox = "//input[@class='pf-pageNo-textbox']";
	WebPageElements pagenumtextbox = new WebPageElements("Page Number Input text box", "xpath", PageNumTextbox);
	
	String ManagePorfolio = "//strong[text()='Manage Portfolios']";
	WebPageElements manageportfolio = new WebPageElements("Manage Portfolios Link", "xpath", ManagePorfolio);
	
	String ApprovePorfolio = "//strong[text()='Approve Portfolios']";
	WebPageElements approveportfolio = new WebPageElements("Approve Portfolios Link", "xpath", ManagePorfolio);
		
	String TextArea = "//textarea[contains(@id,'mat-input')]";
	WebPageElements textarea = new WebPageElements("Home page textarea", "xpath", TextArea);
}


