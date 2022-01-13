package PR1.Studienaufgabe.Minesweeper.src;

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

    static int option;
    static Container container;
    JFrame frame;
    static JLabel schwierigkeit, flaggenCounter, flaggenImage;
    JLabel leeresFeld[] = new JLabel[6];
    static ImageIcon flagge = new ImageIcon("PR1/Studienaufgabe/Minesweeper/res/flagIcon.png"), 
    bombe = new ImageIcon("PR1/Studienaufgabe/Minesweeper/res/bombIcon.png");
    Font font = new Font("Arial", Font.PLAIN, 35);
    static ArrayList<Button> spielfeld = new ArrayList<>();

    public GUI() {
        container = getContentPane();
        container.setLayout(new GridLayout(0, 9));

        //®Bedienelemente
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

        //Hinzufügen zum Container
        //Top-row
        container.add(leeresFeld[0]);
        container.add(leeresFeld[1]);
        container.add(flaggenImage);
        container.add(flaggenCounter);
        container.add(leeresFeld[2]);
        container.add(leeresFeld[3]);
        container.add(schwierigkeit);
        container.add(leeresFeld[4]);
        container.add(leeresFeld[5]);

        //Field
        for (int i = 0; i < 81; i++) {
            Button b = new Button(i, container, font);
            spielfeld.add(b);
        }

        //Fenster
        frame = new JFrame("Minsweeper by Moritz");
        frame.setSize(800, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(true);
        //frame.setAlwaysOnTop(true);
        frame.add(container);
        frame.setVisible(true);

        //Schweirigkeitsabfrage
        option = JOptionPane.showOptionDialog(null,
                "Auf welchem Schwierigkeitsgrad willst du spielen?",
                "Schwierigkeit!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                new String[] { "Schwer", "Mittel", "Leicht" }, "Mittel");

        //Set Minen und Schwierigkeit NACH Abfrage
        flaggenCounter.setText(String.valueOf(getBombenAnzahl()));
        schwierigkeit.setText(getSchwierigkeit());
    }

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

    public static String getSchwierigkeit() {
        String schwierigkeit;
        if (GUI.option == JOptionPane.CANCEL_OPTION) {
            schwierigkeit = "Leicht";
        } else if (GUI.option == JOptionPane.YES_OPTION) {
            schwierigkeit = "Schwer";
        } else {
            schwierigkeit = "Mittel";
            ;
        }
        return schwierigkeit;
    }

    public static void verloren() {
        Game.allesAufdecken(bombe);
        JOptionPane.showConfirmDialog(null,
        "Du hast verloren!", 
        "Verloren!", 
        JOptionPane.DEFAULT_OPTION);
        System.exit(0);
    }

    public static void gewonnen() {
        Game.allesAufdecken(bombe);
        JOptionPane.showConfirmDialog(null,
        "Du hast gewonnen!", 
        "Gewonnen!", 
        JOptionPane.DEFAULT_OPTION);
        System.exit(0);
    }
}