package modele;

import java.util.ArrayList;
import java.util.Random;

public class Etat {
    private ArrayList<Processeurs> processeurs;
    private int energie;

    public Etat(ArrayList<Processeurs> procs){
        processeurs = procs;
    }

    public Etat(Etat toCopy){
        processeurs = (ArrayList<Processeurs>)toCopy.getProcesseurs().clone();
    }

    public void calculEnergie(){
        int cPtemp = 0;

        for (Processeurs p : processeurs) {
            cPtemp = Math.max(cPtemp, p.getCout());
        }
        energie = cPtemp;
    }

    public void fluctuation(){
        Random rand = new Random();
        int indexProc = rand.nextInt(getNbProc());
        while (processeurs.get(indexProc).nbTaches() == 0)
            indexProc = rand.nextInt(getNbProc());
        int indexTache;
        indexTache = rand.nextInt(processeurs.get(indexProc).nbTaches());
        Tache t = processeurs.get(indexProc).getTaches(indexTache);
        processeurs.get(indexProc).removeTache(t);
        int nextProc = rand.nextInt(getNbProc());
        while (nextProc == indexProc)
            nextProc = rand.nextInt(getNbProc());
        processeurs.get(nextProc).addTache(t);
    }

    public int getEnergie() {
        calculEnergie();
        return energie;
    }

    public int getNbProc(){
        return processeurs.size();
    }

    public void addProc(Processeurs p){
        processeurs.add(p);
    }

    public void attributTache(int index, Tache tache){
        processeurs.get(index).addTache(tache);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("");
        int compt = 0;
        for (Processeurs p : processeurs){
            sb.append("Processeur "+(compt+1)+" : \n");
            for (Tache i : p){
                sb.append("[Tache "+i.getIndex()+" : "+i.value+"]");
            }
            sb.append("\n");
            compt++;
        }
        return sb.toString();
    }

    public ArrayList<Processeurs> getProcesseurs() {
        return processeurs;
    }
}
