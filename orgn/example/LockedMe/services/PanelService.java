package orgn.example.LockedMe.services;

import orgn.example.LockedMe.Directory.Directory;
import orgn.example.LockedMe.Panels.FileOptionsPanel;
import orgn.example.LockedMe.Panels.MainPanel;
import orgn.example.LockedMe.Panels.Panel;


public class PanelService {	
	public static MainPanel MainPanel = new MainPanel();
    public static FileOptionsPanel FileOptionsPanel = new FileOptionsPanel();
    
    

    public static Panel CurrentScreen = MainPanel;

    
    public static Panel getCurrentScreen() {
        return CurrentScreen;
    }

    
    public static void setCurrentScreen(Panel currentScreen) {
        CurrentScreen = currentScreen;
    }
    
    
    
}
