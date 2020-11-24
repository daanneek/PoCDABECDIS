package ServerClient;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;

public class TrayIconDemo {

    public static void main(String[] args) throws AWTException {
        if (SystemTray.isSupported()) {
            try{
                TrayIconDemo td = new TrayIconDemo();
                td.displayTray();
            }
            catch(Exception e){
                System.err.println(e);
            }
        } else {
            System.err.println("System tray not supported!");
        }
    }
    public void displayTray() throws AWTException, IOException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("images/rijkswaterstaat-squarelogo-1426746034306.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Chart downloader");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Chart downloader");
        tray.add(trayIcon);
        trayIcon.displayMessage("Chart downloader", "All charts have been updated", MessageType.NONE);
    
    }
}