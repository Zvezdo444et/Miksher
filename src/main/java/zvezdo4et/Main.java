package zvezdo4et;

import zvezdo4et.ui.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            Image image = Toolkit.getDefaultToolkit().createImage("D:\\JavaProject\\Miksher\\zvuk.png");
            frame.setIconImage(image);
            if (!SystemTray.isSupported()) {
                frame.setVisible(true);
                return;
            }

            SystemTray tray = SystemTray.getSystemTray();

            PopupMenu popup = new PopupMenu();
            MenuItem exitItem = new MenuItem("Выход");
            exitItem.addActionListener(e -> System.exit(0));
            popup.add(exitItem);

            TrayIcon trayIcon = new TrayIcon(image, "Miksher", popup);
            trayIcon.setImageAutoSize(true);

            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        frame.setVisible(true);
                        frame.setExtendedState(JFrame.NORMAL);
                    }
                }
            });

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        });
    }
}
