package modele;

import java.util.*;

public class Modele extends Observable{
    private int tache_size;
    private Tache[] taches;
    private Etat etatCourrant;
    private float initTemp;
    private float finalTemps;
    private int energieSystem;
    private int meilleure;
    private boolean changeVal;
    private boolean finRecuit;
    private boolean finTaboue;
    private String affichageRecuit;
    private String affichageTaboue;
    private float saveTemp;
    private ArrayList<Etat> listtaboue;
    private Etat etatPrecedent;
    private int iteration;
    private int tailleTaboue;
    private int nbProcs;

    public Modele(){
        tache_size = 6;
        taches = new Tache[tache_size];
        nbProcs = 2;
        for (int i = 0; i < tache_size; i++)
            taches[i] = new Tache(i, 0);
        etatCourrant = new Etat(new ArrayList<Processeurs>(nbProcs));
        etatCourrant.addProc(new Processeurs(etatCourrant.getNbProc()));
        etatCourrant.addProc(new Processeurs(etatCourrant.getNbProc()));
        etatPrecedent = etatCourrant;
        saveTemp = 100f;
        finRecuit = false;
        finTaboue = false;
        finalTemps = 0.1f;
        energieSystem = 0;
        changeVal = false;
        affichageRecuit = new String("");
        affichageTaboue = new String("");
        listtaboue = new ArrayList<>();
        tailleTaboue = 10;
    }

    public void reset(){
        iteration = 0;
        tailleTaboue = 10;
        listtaboue.clear();
        etatCourrant = new Etat(new ArrayList<Processeurs>(nbProcs));
        for (int i = 0; i < nbProcs; i++)
            etatCourrant.addProc(new Processeurs(etatCourrant.getNbProc()));
        etatPrecedent = etatCourrant;
        initTemp = saveTemp;
        for (Tache t : taches)
            meilleure += t.value;
    }

    public void setInitial(){
        reset();
        Random rand = new Random();
        int index;
        for (int i = 0; i < tache_size; i++){
            index = rand.nextInt(nbProcs);
            etatCourrant.attributTache(index, taches[i]);
        }
        energieSystem = etatCourrant.getEnergie();
    }

    public ArrayList<Processeurs> copyProcesseur(ArrayList<Processeurs> proc2){
        ArrayList<Processeurs> newpro = new ArrayList<>(proc2.size());
        for (Processeurs p : proc2){
            Processeurs newp = new Processeurs(p.getIndex());
            for (Tache t : p){
                newp.addTache(new Tache(t));
            }
            newpro.add(newp);
        }
        return newpro;
    }

    public Etat fluctuation(){
        ArrayList<Processeurs> newpro = copyProcesseur(etatCourrant.getProcesseurs());
        Etat next = new Etat(newpro);
        next.fluctuation();
        return next;
    }

    public void algoTaboue(){
        setInitial();
        int compt = 0;
        RechercheTaboue rt = new RechercheTaboue(this);
        while (compt < 150){
            if (rt.boucleTaboue()){
                compt = 0;
            } else {
                compt++;
                rt.swap();
            }
            iteration++;
        }
        affichageTaboue = "";
        affichageTaboue += "Terminé en "+iteration+" itérations\n\n";
        affichageTaboue += "Configuration optimal :\n";
        affichageTaboue += etatCourrant.toString();
        affichageTaboue += "\nValeur Objectif (CMAX) : "+energieSystem+"\n";
        finTaboue = true;
        setChanged();
        notifyObservers();
        finTaboue = false;
    }

    public void algoRecuit(){
        setInitial();
        RecuitSimule rs = new RecuitSimule(this);
        rs.recuitSimule();
        finRecuit = true;
        setChanged();
        notifyObservers();
        finRecuit = false;
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
        if (tache_size >= 2) {
            tache_size--;
            Tache[] tmp = taches.clone();
            taches = new Tache[tache_size];
            for (int i = 0; i < tache_size; i++)
                taches[i] = tmp[i];
            setChanged();
            notifyObservers();
        }
    }

    public void incrProcs(){
        nbProcs++;
        reset();
        setChanged();
        notifyObservers();
    }

    public void decrProc(){
        if (nbProcs > 2){
            nbProcs--;
            reset();
            setChanged();
            notifyObservers();
        }
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
        return nbProcs;
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

    public boolean isFinTaboue() {
        return finTaboue;
    }

    public String getAffichageTaboue() {
        return affichageTaboue;
    }

    public void setAffichageTaboue(String affichageTaboue) {
        this.affichageTaboue = affichageTaboue;
    }

    public void setAffichageRecuit(String affichageRecuit) {
        this.affichageRecuit = affichageRecuit;
    }

    public Etat getEtatCourrant() {
        return etatCourrant;
    }

    public float getInitTemp() {
        return initTemp;
    }

    public int getMeilleure() {
        return meilleure;
    }

    public int getTailleTaboue() {
        return tailleTaboue;
    }

    public void setTailleTaboue(int tailleTaboue) {
        this.tailleTaboue = tailleTaboue;
        setChanged();
        notifyObservers();
    }
}
