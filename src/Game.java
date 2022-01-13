package PR1.Studienaufgabe.Winesmeeper.src;

import javax.swing.ImageIcon;

public class Game {

    static int speicher = 0, random = 0, aufgedeckteFelder = 0;

    public Game() {
        if (aufgedeckteFelder == GUI.spielfeld.size() - GUI.getBombenAnzahl()) {
            GUI.gewonnen();
        }
    }

    public static void erstesFeld(int id) {
        int random;
        for (int i = 0; i < GUI.getBombenAnzahl(); i++) {
            do {
                random = (int) (Math.random() * GUI.spielfeld.size());
            } while (random == id);
            GUI.spielfeld.get(random).isBombe = true;
        }
    }

    public static int angrenzendeBomben(int id) {
        int nr = 0;
        //Look Oben
        if (id > 8) {
            if (id % 9 == 0) {
                for (int i = -9; i <= -8; i++) {
                    if (GUI.spielfeld.get(id + i).isBombe == true) {
                        nr++;
                    }
                } 
            } else if ((id + 1) % 9 == 0) {
                for (int i = -10; i <= -9; i++) {
                    if (GUI.spielfeld.get(id + i).isBombe == true) {
                        nr++;
                    }
                } 
            } else {
                for (int i = -10; i <= -8; i++) {
                    if (GUI.spielfeld.get(id + i).isBombe == true) {
                        nr++;
                    }
                } 
            }
        }
        //Look Rechts-Links
        if (id % 9 == 0) {
            if (GUI.spielfeld.get(id + 1).isBombe == true) {
                nr++;
            }
        } else if ((id + 1) % 9 == 0) {
            if (GUI.spielfeld.get(id - 1).isBombe == true) {
                nr++;
            }
        } else {
            for (int i = -1; i <= 1; i += 2) {
                if (GUI.spielfeld.get(id + i).isBombe == true) {
                    nr++;
                }
            } 
        }
        //Look Unten
        if (id < 72) {
            if (id % 9 == 0) {
                for (int i = 9; i <= 10; i++) {
                    if (GUI.spielfeld.get(id + i).isBombe == true) {
                        nr++;
                    }
                } 
            } else if ((id + 1) % 9 == 0) {
                for (int i = 8; i <= 9; i++) {
                    if (GUI.spielfeld.get(id + i).isBombe == true) {
                        nr++;
                    }
                } 
            } else {
                for (int i = 8; i <= 10; i++) {
                    if (GUI.spielfeld.get(id + i).isBombe == true) {
                        nr++;
                    }
                }
            }
        }
        return nr;
    }

    public static void aufdecken(int id) {
        int nr = angrenzendeBomben(id);
        //Flaggen Aufdecken
        if (GUI.spielfeld.get(id).hasFlagge == true) {
            GUI.spielfeld.get(id).setIcon(null);
            int flaggen = Integer.parseInt(GUI.flaggenCounter.getText());
            GUI.flaggenCounter.setText(String.valueOf(flaggen + 1));
            GUI.spielfeld.get(id).hasFlagge = false;
        }
        //Normales Aufdecken
        GUI.spielfeld.get(id).aufgedeckt(id);
        //nullenAufdecken(id);
        if (GUI.spielfeld.get(id).isBombe == false) {
            GUI.spielfeld.get(id).setNumber(nr);
        }
        aufgedeckteFelder++;
        //nullenAufdecken(id);
        //Verloren
        if (GUI.spielfeld.get(id).isBombe == true) {
            GUI.spielfeld.get(id).setIcon(GUI.bombe);
            GUI.verloren();
        }
    }
    public static void nullenAufdecken(int id) {
        int nr = angrenzendeBomben(id);
        if (nr == 0) {
            //Look Oben
            if (id > 8) {
                if (id % 9 == 0) {
                    for (int i = -9; i <= -8; i++) {
                        if (angrenzendeBomben(id + i) == 0) {
                            GUI.spielfeld.get(id).aufgedeckt(id + i);
                            //nullenAufdecken(id + i);
                        }
                    } 
                } else if ((id + 1) % 9 == 0) {
                    for (int i = -10; i <= -9; i++) {
                        if (angrenzendeBomben(id + i) == 0) {
                            GUI.spielfeld.get(id).aufgedeckt(id + i);
                            //nullenAufdecken(id + i);
                        }
                    } 
                } else {
                    for (int i = -10; i <= -8; i++) {
                        if (angrenzendeBomben(id + i) == 0) {
                            GUI.spielfeld.get(id).aufgedeckt(id + i);
                            //nullenAufdecken(id + i);
                        }
                    } 
                }
            }
            //Look Rechts-Links
            if (id % 9 == 0) {
                if (angrenzendeBomben(id + 1) == 0) {
                    GUI.spielfeld.get(id).aufgedeckt(id + 1);
                    //nullenAufdecken(id + 1);
                }
            } else if ((id + 1) % 9 == 0) {
                if (angrenzendeBomben(id - 1) == 0) {
                    GUI.spielfeld.get(id).aufgedeckt(id - 1);
                    //nullenAufdecken(id - 1);
                }
            } else {
                for (int i = -1; i <= 1; i += 2) {
                    if (angrenzendeBomben(id + i) == 0) {
                        GUI.spielfeld.get(id).aufgedeckt(id + i);
                        //nullenAufdecken(id + i);
                    }
                } 
            }
            //Look Unten
            if (id < 72) {
                if (id % 9 == 0) {
                    for (int i = 9; i <= 10; i++) {
                        if (angrenzendeBomben(id + i) == 0) {
                            GUI.spielfeld.get(id).aufgedeckt(id + i);
                            //nullenAufdecken(id + i);
                        }
                    } 
                } else if ((id + 1) % 9 == 0) {
                    for (int i = 8; i <= 9; i++) {
                        if (angrenzendeBomben(id + i) == 0) {
                            GUI.spielfeld.get(id).aufgedeckt(id + i);
                            //nullenAufdecken(id + i);
                        }
                    } 
                } else {
                    for (int i = 8; i <= 10; i++) {
                        if (angrenzendeBomben(id + i) == 0) {
                            GUI.spielfeld.get(id).aufgedeckt(id + i);
                            //nullenAufdecken(id + i);
                        }
                    }
                }
            }
        } else {
            GUI.spielfeld.get(id).setNumber(nr);
        }
    }

    public static void  allesAufdecken(ImageIcon img) {
        for (int id = 0; id < GUI.spielfeld.size(); id++) {
            if (GUI.spielfeld.get(id).isBombe) {
                GUI.spielfeld.get(id).aufgedeckt(id);
                GUI.spielfeld.get(id).setIcon(img);
            } else {
                GUI.spielfeld.get(id).aufgedeckt(id);
            }
        }
    }

    public static void setFlagge(ImageIcon img, int id) {
        if (GUI.spielfeld.get(id).hasFlagge == false) {
            GUI.spielfeld.get(id).setIcon(img);
            int flaggen = Integer.parseInt(GUI.flaggenCounter.getText());
            GUI.flaggenCounter.setText(String.valueOf(flaggen - 1));
            GUI.spielfeld.get(id).hasFlagge = true;
        } else {
            GUI.spielfeld.get(id).setIcon(null);
            int flaggen = Integer.parseInt(GUI.flaggenCounter.getText());
            GUI.flaggenCounter.setText(String.valueOf(flaggen + 1));
            GUI.spielfeld.get(id).hasFlagge = false;
        }
    }
}