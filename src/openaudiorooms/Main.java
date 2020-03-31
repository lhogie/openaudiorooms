package openaudiorooms;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        JList inputList = new JList();
        Vector v = new Vector();

        AudioFormat audioFmt = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                44100, 16, 2, 4, 44100, false);

        for (Mixer.Info info : AudioSystem.getMixerInfo()) {
            Mixer mixer = AudioSystem.getMixer(info);
            try {
                Line.Info sdlLineInfo = new DataLine.Info(SourceDataLine.class, audioFmt);
                SourceDataLine sdl = (SourceDataLine) mixer.getLine(sdlLineInfo);
                v.add(info.getName() + " <> " + info.getDescription());
            } catch (LineUnavailableException e) {
                //e.printStackTrace();
                System.out.println("Mixer rejected, Line Unavailable: " + info);
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
                System.out.println("Mixer rejected, Illegal Argument: " + info);
            }


        }

        inputList.setListData(v);

        JFrame f = new JFrame("Open Audio Rooms");

        JPanel contentPane = new JPanel(new GridLayout(1, 2));
        JPanel audioPanel = new JPanel();
        JTextField roonName = new JTextField();
        JPanel roomPanel = new JPanel();
        roomPanel.add(roonName);
        roonName.setPreferredSize(new Dimension(200, 50));
        roonName.setBorder(new TitledBorder("Enters the name of a room:"));
        roomPanel.add(roonName);
        f.setSize(800, 600);
        f.setContentPane(contentPane);
        contentPane.add(roomPanel);
        contentPane.add(inputList);
        f.setVisible(true);

    }
}
