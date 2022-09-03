package orgn.example.LockedMe.Panels;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import orgn.example.LockedMe.services.DirectoryService;
import orgn.example.LockedMe.services.PanelService;


public class MainPanel implements Panel {
	private String CompanyName = "***** Lockers Pvt. Ltd *****\n";
    private String welcomeText = "Hello and welcome to LockedMe.com!";
    private String developerName = "Application Developed By: Nitin Mudgal";

    private ArrayList<String> options = new ArrayList<>();


    public MainPanel() {
        options.add("1. Show file names in an ascending order");
        options.add("2. Show Business-level operations Menu(Add/Delete/Search)");
        options.add("3. Close the application");

    }
    
    public void introWS() {
    	System.out.println(CompanyName);
    	System.out.println(welcomeText);
        System.out.println(developerName);
        System.out.println("\n");
        Show();
    }
    
    
    
    @Override
    public void Show() {
    	System.out.println("==================================================================");
    	System.out.println("Main Menu:");
    	System.out.println("Please select an option from below: \n");
        for (String s : options)  {
            System.out.println(s);
        }
        //System.out.println("==================================================================");
    }

    public void GetUserInput() {
        int selectedOption  = 0;
        while ((selectedOption = this.getOption()) != 3) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option) {
        switch(option) {

            case 1: // Show Files in Directory
                this.ShowFiles();
                
                this.Show();
                
                break;
                
            case 2: // Show File Options menu
            	PanelService.setCurrentScreen(PanelService.FileOptionsPanel);
                PanelService.getCurrentScreen().Show();
                PanelService.getCurrentScreen().GetUserInput();
                
                this.Show();
                
                break;
                
            default:
                System.out.println("Oops this is an invalid Option");
                break;
        }
        
    }

    public void ShowFiles() {
        System.out.println("List of Files: ");
    	DirectoryService.PrintFiles();

    }
    
    private int getOption() {
        Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {

        }
        return returnOption;

    }
}
