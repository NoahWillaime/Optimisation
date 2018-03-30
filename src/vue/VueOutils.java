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
    private JButton play;

    public VueOutils(Observable o){
        mod = (Modele)o;
        o.addObserver(this);
     //   toolBar = new JToolBar();
        plus = new JButton("PLUS");
        moins = new JButton("MOINS");
        plus.addActionListener(event -> mod.incrTacheSize());
        moins.addActionListener(event -> mod.decrTacheSize());
        this.add(plus);
        this.add(moins);
        play = new JButton("Lancer !");
        play.addActionListener(event -> mod.algoRecuit());
        this.add(play);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
