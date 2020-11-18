package ServerClient;

import java.awt.*;

public class TrayIconDemo {

    public static void main(String[] args) throws AWTException {
        sendNotification("Chart downloader", "New charts are done downloading!", "Fred.png");
    }

    public static void sendNotification(String title, String subtitle, String pathToIcon) {
        SystemTray mainTray = SystemTray.getSystemTray();
        Image trayIconImage = Toolkit.getDefaultToolkit().getImage(pathToIcon);
        TrayIcon mainTrayIcon = new TrayIcon(trayIconImage);
        mainTrayIcon.setImageAutoSize(true);
        try {
            mainTray.add(mainTrayIcon);
            mainTrayIcon.displayMessage(title,  subtitle, TrayIcon.MessageType.NONE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}