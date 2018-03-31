package modele;

import java.util.ArrayList;
import java.util.Iterator;

public class Processeurs implements Iterable<Tache>{
    private ArrayList<Tache> taches;
    private int index;

    public Processeurs(int index){
        taches = new ArrayList<>();
        this.index = index;
    }

    public Processeurs(Processeurs p){
        taches = new ArrayList<>();
        this.index = p.getIndex();
        for (Tache t : p.taches){
            taches.add(new Tache(t.index, t.value));
        }
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

    public int getIndex() {
        return index;
    }

    public Iterator<Tache> iterator(){
        return taches.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Tache t : taches){
            sb.append("["+t.getIndex()+"]="+t.getValue());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Processeurs p = (Processeurs)obj;
        int incr = 0;
        for (Tache t : taches){
            if (p.nbTaches() > 0) {
                for (int i = 0; i < p.nbTaches(); i++) {
                    Tache eq = p.getTaches(i);
                    if (t.getIndex() == eq.getIndex())
                        incr++;
                }
            }
        }
        return incr == nbTaches();
    }
}
