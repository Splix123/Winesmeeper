package PR1.Studienaufgabe.PR1_Moritz_Ruehm.Winesmeeper.src;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Button extends JButton {
    private Linksklick linksklick = new Linksklick();
    private Rechtsklick rechtsklick = new Rechtsklick();
    private int id;
    private boolean hasFlagge, isBombe;
    static Color buttonFarbe[] = { new Color(209, 175, 145), new Color(225, 186, 151),  //0/1 == Braun
        new Color(153, 202, 78), new Color(161, 209, 84), new Color(66, 106, 45) };     //2/3 == Grün, 4 == Dunkelgrün

    Button(int id, Container container, Font font) {
        this.id = id;
        //Schachbrettmuster
        if (id % 2 == 0) {
            this.setBackground(buttonFarbe[2]);
        } else {
            this.setBackground(buttonFarbe[3]);
        }
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setFont(font);
        this.addActionListener(linksklick);
        this.addMouseListener(rechtsklick);
        container.add(this);
    }

    // Linksklick == aufdecken
    public class Linksklick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent links) {
            if (Game.getAufgedeckteFelder() == 0) {
                Game.erstesFeld(id);
                Game.aufdecken(id);
            } else {
                Game.aufdecken(id);
            }
        }
    }

    // Rechtsklick == Flagge
    public class Rechtsklick extends MouseAdapter {
        public void mouseClicked(MouseEvent rechts) {
            if (rechts.getButton() == MouseEvent.BUTTON3) {
                Game.setFlagge(GUI.flagge, id);
            }
        }
    }

    public void setBombe(boolean is) {
        if (is) {
            isBombe = true;
        } else {
            isBombe = false;
        }
    }

    public void aufgedeckt(int id) {
        //Schachbrettmuster
        if (id % 2 == 0) {
            this.setBackground(buttonFarbe[0]);
        } else {
            this.setBackground(buttonFarbe[1]);
        }
    }

    public void setNumber(int nr) {
        //Entschiedung der Farbe für jede Nummer
        switch (nr) {
            case 1:
                this.setForeground(Color.BLUE);
                break;
            case 2:
                this.setForeground(Color.GREEN);
                break;
            case 3:
                this.setForeground(Color.RED);
                break;
            case 4:
                this.setForeground(Color.MAGENTA);
                break;
            case 5:
                this.setForeground(Color.ORANGE);
                break;
            case 6:
                this.setForeground(Color.YELLOW);
                break;
            default:
                break;
        }
        this.setText(String.valueOf(nr));
    }

    public boolean getIsBombe() {
        return isBombe;
    }

    public boolean getHasFlagge() {
        return hasFlagge;
    }

    public void setHasFlagge(boolean has) {
        hasFlagge = has;
    }
}