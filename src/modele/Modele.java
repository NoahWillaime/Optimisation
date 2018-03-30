package modele;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Modele extends Observable{
    private int tache_size;
    private Tache[] taches;
    private Etat etatCourrant;
    private float initTemp;
    private float finalTemps;
    private int energieSystem;
    private int procSize;
    private int meilleure;
    private boolean changeVal;
    private boolean finRecuit;
    private String affichageRecuit;
    private float saveTemp;

    public Modele(){
        tache_size = 6;
        taches = new Tache[tache_size];
        for (int i = 0; i < tache_size; i++)
            taches[i] = new Tache(i, 0);
        etatCourrant = new Etat(new ArrayList<Processeurs>(2));
        etatCourrant.addProc(new Processeurs());
        etatCourrant.addProc(new Processeurs());
        etatCourrant.addProc(new Processeurs());
        saveTemp = 100f;
        finRecuit = false;
        procSize = etatCourrant.getNbProc();
        finalTemps = 0.1f;
        energieSystem = 0;
        changeVal = false;
        affichageRecuit = new String("");
    }

    public void reset(){
        etatCourrant = new Etat(new ArrayList<Processeurs>(2));
        etatCourrant.addProc(new Processeurs());
        etatCourrant.addProc(new Processeurs());
        etatCourrant.addProc(new Processeurs());
        procSize = etatCourrant.getNbProc();
        initTemp = saveTemp;
        for (Tache t : taches)
            meilleure += t.value;
    }

    public void setInitial(){
        reset();
        Random rand = new Random();
        int index;
        for (int i = 0; i < tache_size; i++){
            index = rand.nextInt(procSize);
            etatCourrant.attributTache(index, taches[i]);
        }
        energieSystem = etatCourrant.getEnergie();
    }

    public Etat fluctuation(){
        ArrayList<Processeurs> proc2 = etatCourrant.getProcesseurs();
        ArrayList<Processeurs> newpro = new ArrayList<>(proc2.size());
        for (Processeurs p : proc2){
            Processeurs newp = new Processeurs();
            for (Tache t : p){
                newp.addTache(new Tache(t));
            }
            newpro.add(newp);
        }
        Etat next = new Etat(newpro);
        next.fluctuation();
        return next;
    }

    public void algoRecuit(){
        setInitial();
        recuitSimule();
        finRecuit = true;
        setChanged();
        notifyObservers();
        finRecuit = false;
    }

    public void recuitSimule(){
        Etat next;
        while (initTemp > finalTemps){
            next = fluctuation();
            metropolis(next);
            initTemp *= 0.99f;
        }
        affichageRecuit = "";
        affichageRecuit += "Configuration optimal :\n";
        affichageRecuit += etatCourrant.toString();
        affichageRecuit += "Valeur Objectif (CMAX) : "+energieSystem+"\n";
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

    public void incrTacheSize(){
        tache_size++;
        Tache[] tmp = taches.clone();
        taches = new Tache[tache_size];
        int i = 0;
        while (i < tmp.length){
            taches[i] = tmp[i];
            i++;
        }
        for (int j = i; j < tache_size; j++)
            taches[j] = new Tache(j, 0);
        setChanged();
        notifyObservers();
    }

    public void decrTacheSize(){
        tache_size--;
        Tache[] tmp = taches.clone();
        taches = new Tache[tache_size];
        for (int i = 0; i < tache_size; i++)
            taches[i] = tmp[i];
        setChanged();
        notifyObservers();
    }

    public void setTaches(int index, int value){
        taches[index].setValue(value);
        changeVal = true;
        setChanged();
        notifyObservers();
        changeVal = false;
    }

    public void setInitTemp(float initTemp) {
        this.saveTemp = initTemp;
        setChanged();
        notifyObservers();
    }

    public float getFinalTemps() {
        return finalTemps;
    }

    public Tache[] getTaches() {
        return taches;
    }

    public int getTachesize(){
        return tache_size;
    }

    public boolean isChangeVal() {
        return changeVal;
    }

    public int getProcSize() {
        return procSize;
    }

    public float getSaveTemp() {
        return saveTemp;
    }

    public String getAffichageRecuit() {
        return affichageRecuit;
    }

    public boolean isFinRecuit() {
        return finRecuit;
    }
}
