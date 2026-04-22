package zvezdo4et.core;

import javax.sound.sampled.*;
import java.util.Arrays;
import java.util.List;

public class DeviceManager {
    public static List<Mixer.Info> getOutputDevices() {
        return Arrays.stream(AudioSystem.getMixerInfo())
                .filter(info -> {
                    Mixer mixer = AudioSystem.getMixer(info);
                    Line.Info[] sourceLines = mixer.getSourceLineInfo();
                    return sourceLines.length > 0;
                })
                .toList();
    }

    public static TargetDataLine getSystemCaptureLine() throws LineUnavailableException {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : mixerInfos) {
            String name = info.getName().toLowerCase();
            if (name.contains("cable") && name.contains("output")) {
                Mixer mixer = AudioSystem.getMixer(info);
                for (Line.Info lineInfo : mixer.getTargetLineInfo()) {
                    if (TargetDataLine.class.isAssignableFrom(lineInfo.getLineClass())) {
                        return (TargetDataLine) mixer.getLine(lineInfo);
                    }
                }
            }
        }
        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
        return AudioSystem.getTargetDataLine(format);
    }
}