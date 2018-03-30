package vue;

import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueAlgo extends JPanel implements Observer {
    private Modele m;
    private JTextArea proc1;
    private JTextArea proc2;

    public VueAlgo(Observable o) {
        super();
        this.m = (Modele)o;
        o.addObserver(this);
        proc1 = new JTextArea(m.getP1().toString());
        this.add(proc1, BorderLayout.CENTER);
        proc2 = new JTextArea(m.getP2().toString());
        this.add(proc2, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        proc1.setText(m.getP1().toString());
        proc2.setText(m.getP2().toString());
    }
}
