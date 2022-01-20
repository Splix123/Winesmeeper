package PR1.Studienaufgabe.Winesmeeper.src;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Button extends JButton {
    Linksklick linksklick = new Linksklick();
    Rechtsklick rechtsklick = new Rechtsklick();
    int id, nr;
    boolean hasFlagge, isBombe;
    static Color buttonFarbe [] = {new Color(209, 175, 145), new Color(225, 186, 151), new Color(153, 202, 78), new Color(161, 209, 84), new Color(66, 106, 45)};

    Button (int id, Container container, Font font) {
        this.id = id;
        if (id % 2 == 0) {
            this.setBackground(buttonFarbe [2]);
        } else {
            this.setBackground(buttonFarbe [3]);
        }
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setFont(font);
        this.addActionListener(linksklick);
        this.addMouseListener(rechtsklick);
        container.add(this);
    }

    //Linksklick == aufdecken
    public class Linksklick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent links) {
            if (Game.aufgedeckteFelder == 0) {
                Game.erstesFeld(id);
                Game.aufdecken(id);
            } else {
                Game.aufdecken(id);
            }
        }
    }

    //Rechtsklick == Flagge
    public class Rechtsklick extends MouseAdapter {
        public void mouseClicked( MouseEvent rechts) {
            if (rechts.getButton() == MouseEvent.BUTTON3) {
                Game.setFlagge(GUI.flagge, id);
            }
        }
    }

    public void setBombe() {
        isBombe = true;
    }

    public void aufgedeckt(int id) {
        if (id % 2 == 0) {
            this.setBackground(buttonFarbe [0]);
        } else {
            this.setBackground(buttonFarbe [1]);
        }
    }

    public void setNumber(int nr) {
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
}
