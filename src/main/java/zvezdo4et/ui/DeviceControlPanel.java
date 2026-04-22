package zvezdo4et.ui;

import zvezdo4et.core.AudioPlayer;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import java.awt.*;

public class DeviceControlPanel extends JPanel {
    private final JCheckBox enableCheck;
    private final JSlider volumeSlider;
    private final AudioPlayer player;

    public DeviceControlPanel(Mixer.Info info) {
        this.player = new AudioPlayer(info);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEtchedBorder());

        String name = info.getName();
        String shortName = name.length() > 30 ? name.substring(0, 30) : name;

        enableCheck = new JCheckBox(shortName);

        enableCheck.addActionListener(e -> {
            if (!enableCheck.isSelected()) {
                player.stop();
            }
        });

        volumeSlider = new JSlider(0, 150, 75);

        volumeSlider.addChangeListener(e -> player.setVolume(volumeSlider.getValue() / 100f));

        add(enableCheck);
        add(new JLabel("Громкость:"));
        add(volumeSlider);
    }

    public AudioPlayer getPlayer() {
        return player;
    }

    public boolean isSelected() {
        return enableCheck.isSelected();
    }
}