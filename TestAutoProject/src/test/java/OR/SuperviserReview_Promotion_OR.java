package OR;

public class SuperviserReview_Promotion_OR {
	
	public static String approvalDetails_Title = "//p[text()='Approval Details']";
	public static String approvalSupervisor = "//label[text()='Approval Supervisor']//parent::lightning-grouped-combobox//input";
	
    public static String approvalSupersiorList = "//lightning-base-combobox-item[@exportparts='option']//parent::div/ul/li";
   
    public static String saveAndProceedBtn = "//button[text()='Save and Proceed']";
}
