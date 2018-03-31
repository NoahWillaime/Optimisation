package vue;

import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueOutils extends JToolBar implements Observer{
  //  private JToolBar toolBar;
    private Modele mod;
    private JButton plus;
    private JButton moins;
    private JLabel tailleTache;
    private JButton addProc;
    private JButton minProc;
    private JLabel taillProc;

    public VueOutils(Observable o){
        mod = (Modele)o;
        o.addObserver(this);
     //   toolBar = new JToolBar();
        plus = new JButton("Ajouter une tâche");
        moins = new JButton("Enlever une tâche");
        plus.addActionListener(event -> mod.incrTacheSize());
        moins.addActionListener(event -> mod.decrTacheSize());
        this.tailleTache = new JLabel("Tâches : "+mod.getTaches().length);
        this.add(tailleTache);
        this.add(plus);
        this.add(moins);
        this.taillProc = new JLabel("Processeurs : "+mod.getProcSize());
        this.add(taillProc);
        addProc = new JButton("Ajouter un processeur");
        addProc.addActionListener(e -> mod.incrProcs());
        minProc = new JButton("Enlever un processeur");
        minProc.addActionListener(e -> mod.decrProc());
        this.add(addProc);
        this.add(minProc);
    }

    @Override
    public void update(Observable o, Object arg) {
        tailleTache.setText("Tâches :"+mod.getTaches().length);
        taillProc.setText("Processeurs : "+mod.getProcSize());
    }
}
