package modele;

public class Tache {
    int index;
    int value;

    public Tache(int ind, int val){
        index = ind;
        value = val;
    }

    public Tache(Tache t){
        index = t.getIndex();
        value = t.getValue();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }
}
