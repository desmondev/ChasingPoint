/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasingpoint;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Damian
 */
public class ChasingPoint extends JFrame {

    public ChasingPoint() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ChasingPoints");
        setResizable(false);
        init();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ChasingPoint();
    }

    private void init() {
        setLayout(new GridLayout(1, 1, 0, 0));
        Screen s = new Screen();
        add(s);
        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}
