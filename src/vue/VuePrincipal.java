package vue;

import ecouteurs.EcouteurTache;
import modele.Modele;
import modele.Tache;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VuePrincipal extends JTabbedPane implements Observer {
    private Modele m;
    private VueRecuit vueR;
    private VueTaboue vueT;

    public VuePrincipal(Observable o) {
        super();
        this.m = (Modele)o;
        o.addObserver(this);
        vueR = new VueRecuit(m);
        vueT = new VueTaboue(m);
        this.add("Recuit simulé", vueR);
        this.add("Recherche Taboue", vueT);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
