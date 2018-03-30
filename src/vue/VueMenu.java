package vue;

import modele.Modele;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VueMenu extends JMenuBar implements Observer {
    Modele mod;
    private JMenu fichier;
    private JMenuItem quitter;

    public VueMenu(Observable o) {
        mod = (Modele)o;
        o.addObserver(this);
        this.fichier = new JMenu("Optimisation");
        this.add(fichier);
        this.quitter = new JMenuItem("Quitter");
        this.quitter.addActionListener(e -> System.exit(0));
        this.fichier.add(quitter);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
