package PR1.Studienaufgabe.PR1_Moritz_Ruehm.Winesmeeper.src;

import javax.swing.ImageIcon;

public class Game {
    private static int aufgedeckteFelder = 0;

    // Erzeugt die Bomben erst sobald das erste Feld aufgedeckt ist
    // --> Erstes Feld ist nie eine Bombe
    // TODO: Erstes Feld sollte eine 0 sein
    public static void erstesFeld(int id) {
        int random = id;
        for (int i = 0; i < GUI.getBombenAnzahl(); i++) {
            while (random == id || GUI.spielfeld.get(random).getIsBombe()) {
                random = (int) (Math.random() * GUI.spielfeld.size());
            }
            GUI.spielfeld.get(random).setBombe(true);
        }
    }

    // TODO: Suchalgorithmus sollte auf x und y koordinaten basieren
    public static void angrenzendeBomben(int id) {
        int nr = 0;
        // Look Oben
        if (id > 8) {
            if (id % 9 == 0) {
                for (int i = -9; i <= -8; i++) {
                    if (GUI.spielfeld.get(id + i).getIsBombe()) {
                        nr++;
                    }
                }
            } else if ((id + 1) % 9 == 0) {
                for (int i = -10; i <= -9; i++) {
                    if (GUI.spielfeld.get(id + i).getIsBombe()) {
                        nr++;
                    }
                }
            } else {
                for (int i = -10; i <= -8; i++) {
                    if (GUI.spielfeld.get(id + i).getIsBombe()) {
                        nr++;
                    }
                }
            }
        }
        // Look Rechts-Links
        if (id % 9 == 0) {
            if (GUI.spielfeld.get(id + 1).getIsBombe()) {
                nr++;
            }
        } else if ((id + 1) % 9 == 0) {
            if (GUI.spielfeld.get(id - 1).getIsBombe()) {
                nr++;
            }
        } else {
            for (int i = -1; i <= 1; i += 2) {
                if (GUI.spielfeld.get(id + i).getIsBombe()) {
                    nr++;
                }
            }
        }
        // Look Unten
        if (id < 72) {
            if (id % 9 == 0) {
                for (int i = 9; i <= 10; i++) {
                    if (GUI.spielfeld.get(id + i).getIsBombe()) {
                        nr++;
                    }
                }
            } else if ((id + 1) % 9 == 0) {
                for (int i = 8; i <= 9; i++) {
                    if (GUI.spielfeld.get(id + i).getIsBombe()) {
                        nr++;
                    }
                }
            } else {
                for (int i = 8; i <= 10; i++) {
                    if (GUI.spielfeld.get(id + i).getIsBombe()) {
                        nr++;
                    }
                }
            }
        }
        if (nr != 0) {
            GUI.spielfeld.get(id).setNumber(nr);
        }
    }

    public static void aufdecken(int id) {
        // Felder mit Flaggen zurücksetzen
        if (GUI.spielfeld.get(id).getHasFlagge() == true) {
            GUI.spielfeld.get(id).setIcon(null);
            int flaggen = Integer.parseInt(GUI.flaggenCounter.getText());
            GUI.flaggenCounter.setText(String.valueOf(flaggen + 1));
            GUI.spielfeld.get(id).setHasFlagge(false);
        }
        // Aufdecken
        GUI.spielfeld.get(id).aufgedeckt(id);
        aufgedeckteFelder++;
        if (aufgedeckteFelder == GUI.spielfeld.size() - GUI.getBombenAnzahl()) { // Alles aufgedeckt = Gewonnen
            GUI.gewonnen();
        }
        // Bomben-check
        if (GUI.spielfeld.get(id).getIsBombe()) {
            GUI.spielfeld.get(id).setIcon(GUI.bombe);
            GUI.verloren();
        } else { // nummer geben
            angrenzendeBomben(id);
        }

    }

    public static void allesAufdecken(ImageIcon img) {
        for (int id = 0; id < GUI.spielfeld.size(); id++) {
            if (GUI.spielfeld.get(id).getIsBombe()) {
                GUI.spielfeld.get(id).aufgedeckt(id);
                GUI.spielfeld.get(id).setIcon(img);
            } else {
                GUI.spielfeld.get(id).aufgedeckt(id);
            }
            GUI.spielfeld.get(id).setHasFlagge(false);
        }
    }

    public static void setFlagge(ImageIcon img, int id) {
        if (GUI.spielfeld.get(id).getHasFlagge()== false) {
            GUI.spielfeld.get(id).setIcon(img);
            GUI.flaggenCounter.setText(String.valueOf(Integer.parseInt(GUI.flaggenCounter.getText()) - 1));
            GUI.spielfeld.get(id).setHasFlagge(true);
        } else {
            GUI.spielfeld.get(id).setIcon(null);
            GUI.flaggenCounter.setText(String.valueOf(Integer.parseInt(GUI.flaggenCounter.getText()) + 1));
            GUI.spielfeld.get(id).setHasFlagge(false);
        }
    }

    public static int getAufgedeckteFelder() {
        return aufgedeckteFelder;
    }

    public static void setAufgedeckteFelder(int anzahl) {
        aufgedeckteFelder = anzahl;
    }
}