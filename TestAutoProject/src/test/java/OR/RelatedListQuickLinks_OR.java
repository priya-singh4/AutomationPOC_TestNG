package OR;

public class RelatedListQuickLinks_OR {
	
	public static String relatedListQuickLinksTitle = "//h2[text()='Related List Quick Links']";
	
	public static String screeningPerson_link = "//slot[contains(text(),'Screening Persons')]//parent::span//parent::a";
	public static String screeningPersonTitle = "//h1[text()='Screening Persons']";
	
	public static String validatePersonBtn = "//button[text()='Validate Person']";
	
	public static String searchForPersonTitle = "//strong[text()='Search for a Person']";
	public static String searchBtn  = "//button[text()='Search']";
	
	public static String searchResult  = "//span[text()='Search Results']";
	
	public static String newPersonBtn = "//button[text()='New Person']";
	
	public static String NewPersonPopUp = "//h1[text()='New Person']";
	public static String sexAtBirthlabel = "//label[text()='Sex at Birth']";
	public static String sexAtBirthDD = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String saveBtn  = "//button[text()='Save']";
	
	public static String screeningIdLink = "//p[text()='Screening ID']//parent::div//a";
	
	public static String approvalHistory_link = "//slot[contains(text(),'Approval History')]//parent::span//parent::a";
    public static String approvalHistoryTitle = "//h1[@title='Approval History']";
    public static String approvalBtn = "//div[@title='Approve']";
    public static String approvalScreenPopUpHeader = "//h1[text()='Approve Screening']";
    public static String approvalComments = "//div[@class='commentContainer']//textarea[@role='textbox']";
    public static String appovelBtn = "//span[text()='Approve']";
    
    
    
    
    
	
}
