package OR;

public class Folio_OR {
	
	public static String screeningsTitle = "//span[text()='Folio'][1]";
	public static String searchBox = "//input[@name='Case-search-input']";
	public static String folioIdSelected = "//a[contains(@title,'FOL')]";
	public static String folioNameText= "//p[text()='Folio Name']";
	public static String investigationDetails= "//a[text()='Investigation Details']";	
	public static String basicDetails= "//span[text()='Basic Details']";
	public static String keyDates= "//span[text()='Key Dates']";
	public static String investigationSummary= "//span[text()='Investigation Summary'][1]";
	public static String moreTabs= "//button[@title='More Tabs']"; //Approval/Audit
	public static String approvalDetails= "//span[text()='Approval Details']";
	public static String approvalSupervisorTitle= "//span[text()='Approval Supervisor']";
	public static String editApprovalSupervisor = "//span[text()='Edit Approval Supervisor']";
	public static String submittedForApprovalDate= "//span[text()='Submitted for Approval Date/Time']/following::dd/div[1]";
	public static String allegatioPartialLink = "//a[contains(@href,'Allegations')]";
	public static String dispositionPartialLink= "//a[contains(@href,'Disposition')]";
	public static String dispositionRecord= "//slot[contains(text(),'DIS')]//parent::span";
	public static String allegationConclusionDrpDwn= "//button[@aria-label='Allegation Conclusion']"; 
	public static String allegationRecord= "//slot[contains(text(),'ALG')]//parent::span";
	public static String allegationConclusionRationale= "//label[text()='Allegation Conclusion Rationale']//following-sibling::div/input";
	public static String editDispostion= "//button[@title='Edit Disposition']";
	public static String closureDate= "//label[text()='Closure Date']//following-sibling::div/input";
	public static String rationale= "//label[text()='Rationale']//following-sibling::div/textare";
	public static String dispositionDrpDwn= "//button[@aria-label='Disposition']";
	public static String contactBtn= "//button[contains(@name,'Case.Contact')]";
	public static String newInvestigationContact= "//h2[text()='New: Investigation Contact']";
	public static String contactType= "//span[text()='%s']";//Structured Investigation Contact
	public static String nextBtn= "//span[text()='Next'][1]";
	public static String contactDetailsText= "//h1[text()='Contact Details']";
	public static String saveBtn= "//button[text()='Save']";
	

}
