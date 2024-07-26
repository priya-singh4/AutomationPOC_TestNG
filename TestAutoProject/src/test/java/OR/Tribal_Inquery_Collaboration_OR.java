package OR;

public class Tribal_Inquery_Collaboration_OR {

	public static String tribalInqueryCollaborationTitle = "//p[text()='Tribal Inquiry & Collaboration']";
	
	public static String contact_date = "//label[text()='Date']//parent::div//input[@name='contactDate']";
	public static String contact_time = "//input[@name='contactTime']";
	public static String participantTypeDD = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String onBehalfOfChild = "//label[text()='On Behalf of Child']//parent::div//input";
	public static String methodDD = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String contactPurposeDD = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String contactStatusDD = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String initial_ICWA_InqueryDD = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	
	public static String location = "//lightning-base-combobox-item[@exportparts='option']//span[text()='Label']";
    public static String narrative = "//span[text()='Narrative']//parent::span//parent::div/div/div/div[2]/div[1]";
	public static String memberOfTribale_AlaskaNativeVillage = "//lightning-base-combobox-item[@exportparts='option']//span[text()='&s']";
	public static String isParentMemberIsTribe = "//lightning-base-combobox-item[@exportparts='option']//span[text()='Label']";
	public static String receiveService_BenefitFromTribe = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String residence_ReservationTribalLand = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String anyFamilyIdentifiedAncestryHeritage = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
	public static String receiveFederalServiceTribalBenefit = "//lightning-base-combobox-item[@exportparts='option']//span[text()='%s']";
   
	public static String saveAndProceedBtn = "(//button[text()='Save and Proceed'])[4]";
	
}
