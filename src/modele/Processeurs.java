package modele;

import java.util.ArrayList;
import java.util.Iterator;

public class Processeurs implements Iterable{
    private ArrayList<Integer> taches;

    public Processeurs(){
        taches = new ArrayList<>();
    }

    public ArrayList<Integer> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Integer> taches) {
        this.taches = taches;
    }

    public void addTache(int tache){
        this.taches.add(tache);
    }

    public int getCout(){
        int cout = 0;
        for (int i : taches){
            cout += i;
        }
        return cout;
    }

    public Iterator<Integer> iterator(){
        return taches.iterator();
    }
}
