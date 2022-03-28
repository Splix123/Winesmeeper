package PR1.Studienaufgabe.PR1_Moritz_Ruehm.Winesmeeper.src;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUI extends JFrame {
    private static Container container;
    private static JFrame frame;
    private static JLabel schwierigkeit, flaggenImage;
    static JLabel flaggenCounter;
    private static JLabel leeresFeld[] = new JLabel[6];
    static ImageIcon flagge = new ImageIcon("PR1/Studienaufgabe/PR1_Moritz_Ruehm/Winesmeeper/res/flagIcon.png"),
        bombe = new ImageIcon("PR1/Studienaufgabe/PR1_Moritz_Ruehm/Winesmeeper/res/bombIcon.png");
    private Font font = new Font("Arial", Font.PLAIN, 35);
    static ArrayList<Button> spielfeld = new ArrayList<>();
    private static int option;

    public GUI() {
        container = getContentPane();
        container.setLayout(new GridLayout(0, 9));

        //Erstellen der Bedienelemente
        flaggenImage = new JLabel(flagge);
            flaggenImage.setBackground(Button.buttonFarbe[4]);
            flaggenImage.setOpaque(true);
        flaggenCounter = new JLabel("0", JLabel.CENTER);
            flaggenCounter.setBackground(Button.buttonFarbe[4]);
            flaggenCounter.setForeground(Color.WHITE);
            flaggenCounter.setFont(font);
            flaggenCounter.setOpaque(true);
        schwierigkeit = new JLabel("", JLabel.CENTER);
            schwierigkeit.setBackground(Button.buttonFarbe[4]);
            schwierigkeit.setForeground(Color.WHITE);
            schwierigkeit.setFont(font);
            schwierigkeit.setOpaque(true);
        for (int i = 0; i < leeresFeld.length; i++) {
            leeresFeld[i] = new JLabel("");
            leeresFeld[i].setBackground(Button.buttonFarbe[4]);
            leeresFeld[i].setOpaque(true);
        }

        //Leere Felder (oben) zum container hinzufügen
        container.add(leeresFeld[0]);
        container.add(leeresFeld[1]);
        container.add(flaggenImage);
        container.add(flaggenCounter);
        container.add(leeresFeld[2]);
        container.add(leeresFeld[3]);
        container.add(schwierigkeit);
        container.add(leeresFeld[4]);
        container.add(leeresFeld[5]);

        //Feld erstellen (Buttons werden in ihrer Klasse schon zum conatiner hinzugefügt)
        for (int i = 0; i < 81; i++) {
            Button b = new Button(i, container, font);
            spielfeld.add(b);
        }

        //Fenster erstellen
        frame = new JFrame("Minsweeper by Moritz");
        frame.setSize(800, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(true);
        //frame.setAlwaysOnTop(true);
        frame.add(container);
        frame.setVisible(true);

        //Set Minen und Schwierigkeit NACH Schwierigkeitsabfrage
        schwierigkeit.setText(getSchwierigkeit());
        flaggenCounter.setText(String.valueOf(getBombenAnzahl()));
    }

    //Schwierigkeitsabfrage
    public static String getSchwierigkeit() {
        String schwierigkeit;
        option = JOptionPane.showOptionDialog(null,
                "Auf welchem Schwierigkeitsgrad willst du spielen?",
                "Schwierigkeit!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                new String[] { "Hard", "Default", "Easy" }, "Default");

        if (GUI.option == JOptionPane.CANCEL_OPTION) {
            schwierigkeit = "Easy";
        } else if (GUI.option == JOptionPane.YES_OPTION) {
            schwierigkeit = "Hard";
        } else {
            schwierigkeit = "Def";
            ;
        }
        return schwierigkeit;
    }

    //Bombenanzhal basierend auf Schwierigkeitsabfrage
    public static int getBombenAnzahl() {
        int bombenanzahl;
        if (GUI.option == JOptionPane.CANCEL_OPTION) {
            bombenanzahl = 12;
        } else if (GUI.option == JOptionPane.YES_OPTION) {
            bombenanzahl = 20;
        } else {
            bombenanzahl = 16;
        }
        return bombenanzahl;
    }

    //Popup wenn verloren
    public static void verloren() {
        Game.allesAufdecken(bombe);
        option = JOptionPane.showConfirmDialog(null,
                "Du hast verloren!\nNochmal spielen?",
                "Verloren!",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            reset();
        } else if (option == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    //Popup wenn gewonnen
    public static void gewonnen() {
        Game.allesAufdecken(bombe);
        option = JOptionPane.showConfirmDialog(null,
                "Du hast gewonnen!\nNochmal spielen?",
                "Gewonnen!",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            reset();
        } else if (option == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    //Feld Reset bei erneuten Spielen
    public static void reset() {
        for (int id = 0; id < spielfeld.size(); id++) {
            spielfeld.get(id).setBombe(false);
            spielfeld.get(id).setIcon(null);
            spielfeld.get(id).setText(null);
            if (id % 2 == 0) {
                spielfeld.get(id).setBackground(Button.buttonFarbe[2]);
            } else {
                spielfeld.get(id).setBackground(Button.buttonFarbe[3]);
            }
        }
        Game.setAufgedeckteFelder(0);
        schwierigkeit.setText(getSchwierigkeit());
        flaggenCounter.setText(String.valueOf(getBombenAnzahl()));
    }
}