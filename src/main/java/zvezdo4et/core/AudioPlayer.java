package zvezdo4et.core;

import javax.sound.sampled.*;

public class AudioPlayer {
    private final Mixer.Info mixerInfo;
    private SourceDataLine outLine;
    private FloatControl volumeControl;

    public AudioPlayer(Mixer.Info mixerInfo) {
        this.mixerInfo = mixerInfo;
    }

    public void stream(AudioFormat format, byte[] buffer, int bytesRead) {
        try {
            if (outLine == null || !outLine.isOpen()) {
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

                outLine = (SourceDataLine) AudioSystem.getMixer(mixerInfo).getLine(info);
                outLine.open(format);
                outLine.start();

                if (outLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    volumeControl = (FloatControl) outLine.getControl(FloatControl.Type.MASTER_GAIN);
                }
            }
            outLine.write(buffer, 0, bytesRead);
        } catch (Exception e) {
            if (outLine != null) {
                outLine.close();
                outLine = null;
            }
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            try {
                float min = volumeControl.getMinimum();
                float max = volumeControl.getMaximum();

                float targetValue = min + (max - min) * volume;

                float safeValue = Math.max(min, Math.min(max, targetValue));

                volumeControl.setValue(safeValue);
            } catch (Exception e) {
                System.err.println("Не удалось изменить громкость: " + e.getMessage());
            }
        }
    }

    public void stop() {
        if (outLine != null) {
            try {
                outLine.stop();
                outLine.flush();
                outLine.close();
                outLine = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}