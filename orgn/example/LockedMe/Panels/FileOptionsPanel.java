package orgn.example.LockedMe.Panels;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import orgn.example.LockedMe.Directory.Directory;
import orgn.example.LockedMe.services.PanelService;


public class FileOptionsPanel implements Panel {
	
	private Directory dir = new Directory();
	
	private ArrayList<String> options = new ArrayList<>();

    public FileOptionsPanel() {
    	
    	options.add("1. Add a File");
        options.add("2. Delete A File");
        options.add("3. Search A File");
        options.add("4. Return to Main Menu");
        
    }
    
    @Override
    public void Show() {
    	System.out.println("--------------------------------------");
    	System.out.println("File Options Menu: ");
    	System.out.println("Please select an option from below: \n");
        for (String s : options) {
            System.out.println(s);
        }
        //System.out.println("--------------------------------------");
    }

    public void GetUserInput() {
        int selectedOption;
        while ((selectedOption = this.getOption()) != 4) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option) {
        
    	switch(option) {

            case 1:
                this.AddFile();
                
                this.Show();
                break;
            case 2:
                this.DeleteFile();
                
                this.Show();
                break;
            case 3:
                this.SearchFile();
                this.Show();
                break;
            
            default:
                System.out.println("Invalid Option");
                break;
                
                
        }

    }
    
    

    public void AddFile() {
        System.out.println("Please Enter the Filename:");
        String fileName = this.getInputString();
        System.out.println("Please Enter the File Extension:");
        String fileExt = this.getInputString();

        System.out.println("You are adding a file named: '" + fileName + "." + fileExt + "'\n");
        
		try {
			Path path = FileSystems.getDefault().getPath(Directory.name + fileName + "." + fileExt).toAbsolutePath();
			File file = new File(dir.getName() + fileName + "." + fileExt);
			
		      if (file.createNewFile()) {
		    	  System.out.println("File created: '" + file.getName() + "'\n");
		    	  dir.getFiles().add(file);
		    	  
		      } else {
		        System.out.println("The file already exists in the directory");
		      }
		}catch (IOException e){
			System.out.println(e);
		}
	}
        
    
    
    public void DeleteFile() {
    	
    	System.out.println("Please Enter the Filename:");

        String fileName = this.getInputString();

        System.out.println("Caution!! You are deleting a file named: '" + fileName + "'\n");
        
		Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
		File file = path.toFile();
	      if (file.delete()) {
	    	  System.out.println("Deleted File: '" + file.getName() + "'");
	    	  dir.getFiles().remove(file);
	      } else {
	        System.out.println("OOPS, Failed to delete file: '" + fileName + "', file was not found.");
	      }
    }
    
    public void SearchFile() {
    	
    	Boolean found = false;
    	
    	System.out.println("Please Enter the Filename:");

        String fileName = this.getInputString();

        System.out.println("You are searching for a file named: '" + fileName + "'\n");
        
        ArrayList<File> files = dir.getFiles();
       
        
        for(int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().equals(fileName)) {
				System.out.println("Result: Found '" + fileName + "'\n");
				found = true;
			}
        }
        if (found == false) {
        	System.out.println("Result: File not found\n");
        }
    }
    
    private String getInputString() {

        Scanner in = new Scanner(System.in);
        return(in.nextLine());

    }
    
    private int getOption() {
        Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {
        	System.out.println("OOps !! Invalid input");
        }
        return returnOption;

    }

}
