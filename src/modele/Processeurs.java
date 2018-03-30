package modele;

import java.util.ArrayList;
import java.util.Iterator;

public class Processeurs implements Iterable<Tache>{
    private ArrayList<Tache> taches;

    public Processeurs(){
        taches = new ArrayList<>();
    }

    public void removeTache(Tache t){
        taches.remove(t);
    }

    public Tache getTaches(int index) {
        return taches.get(index);
    }

    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }

    public void addTache(Tache tache){
        this.taches.add(tache);
    }

    public int getCout(){
        int cout = 0;
        for (Tache i : taches){
            cout += i.getValue();
        }
        return cout;
    }

    public int nbTaches(){
        return taches.size();
    }

    public Iterator<Tache> iterator(){
        return taches.iterator();
    }
}
