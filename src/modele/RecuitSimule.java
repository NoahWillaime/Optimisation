package modele;

import java.util.Random;

public class RecuitSimule {
    private Modele m;
    private String affichageRecuit;
    private float initTemp;
    private float finalTemps;
    private int iteration;
    private Etat etatCourrant;
    private int energieSystem;
    private int meilleure;

    public RecuitSimule(Modele m){
        this.m = m;
        this.iteration = 0;
        this.initTemp = m.getInitTemp();
        this.finalTemps = m.getFinalTemps();
        this.etatCourrant = m.getEtatCourrant();
        this.energieSystem = etatCourrant.getEnergie();
        this.meilleure = m.getMeilleure();
    }

    public void recuitSimule(){
        Etat next;
        while (initTemp > finalTemps){
            next = m.fluctuation();
            metropolis(next);
            initTemp *= 0.99f;
            iteration++;
        }
        affichageRecuit = "";
        affichageRecuit += "Terminé en "+iteration+" itérations\n\n";
        affichageRecuit += "Configuration optimal :\n";
        affichageRecuit += etatCourrant.toString();
        affichageRecuit += "\nValeur Objectif (CMAX) : "+energieSystem+"\n";
        m.setAffichageRecuit(affichageRecuit);
    }

    public void metropolis(Etat next){
        int energieNext;
        energieNext = next.getEnergie();
        float delta = energieNext - energieSystem;
        if (delta <= 0){
            etatCourrant = next;
            energieSystem = etatCourrant.getEnergie();
            meilleure = next.getEnergie();
        } else {
            Random rand = new Random();
            float proba = rand.nextFloat();
            double div = Math.exp(Math.abs(-delta/initTemp));
            if (proba <= div){
                if (next.getEnergie() < meilleure) {
                    etatCourrant = next;
                    energieSystem = etatCourrant.getEnergie();
                    meilleure = next.getEnergie();
                }
            }
        }
    }
}
