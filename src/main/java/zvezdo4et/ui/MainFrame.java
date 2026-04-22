package zvezdo4et.ui;

import zvezdo4et.core.DeviceManager;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private final List<DeviceControlPanel> panels = new ArrayList<>();

    public MainFrame() {
        setTitle("Miksher");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        DeviceManager.getOutputDevices().forEach(info -> {
            if (!info.getName().toLowerCase().contains("port")) {
                DeviceControlPanel dp = new DeviceControlPanel(info);
                panels.add(dp);
                container.add(dp);
            }
        });

        add(new JScrollPane(container), BorderLayout.CENTER);

        startSystemCapture();
    }

    private void startSystemCapture() {
        new Thread(() -> {
            try {
                AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
                TargetDataLine systemIn = DeviceManager.getSystemCaptureLine();

                systemIn.open(format);
                systemIn.start();

                byte[] buffer = new byte[1024];
                while (true) {
                    int bytesRead = systemIn.read(buffer, 0, buffer.length);
                    if (bytesRead > 0) {
                        for (DeviceControlPanel panel : panels) {
                            if (panel.isSelected()) {
                                panel.getPlayer().stream(format, buffer, bytesRead);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(this, "Ошибка захвата: " + e.getMessage()));
            }
        }).start();
    }
}