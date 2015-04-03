/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasingpoint;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Damian
 */
public final class Screen extends JPanel implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final double rangeMin = 0.0;
    public static final double rangeMax = 600.0;
    public static final int RANGE = 10000;
    public static final double speed = 1.0D;
    public static final double p1_speed_X = -0.5;
    public static final double p1_speed_Y = 0.5;

    private Thread thread;              //watek programu
    private boolean running = false;    //flaga czy program sie zakonczyl
    private int ticks = 0;              //liczba tikow

    private Point p1;              //ostatni punkt krzywej 1
    private Point p2;              //ostatni punkt krzywej 2
    private final ArrayList<Point> krzywa1;   //punkty krzywej 1
    private final ArrayList<Point> krzywa2;   //punkty krzywej 2

    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        krzywa1 = new ArrayList<>();
        krzywa2 = new ArrayList<>();
        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this, "LOOP");
        thread.start();
    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    private void tick() {
        // if ( krzywe nic nie zawieraja to tworzymy nowy punkty) 
        if (krzywa1.isEmpty() && krzywa2.isEmpty()) {

            p2 = new Point("b", generateRandom(), generateRandom());
            p1 = new Point("a", generateRandom(), generateRandom());

            krzywa1.add(p1);
            krzywa2.add(p2);
        }
        ticks++;
        if (ticks > 100000) {

            double distance = p2.obliczOdleglosc(p1);
            distance = (double) Math.round(distance * 10000) / 10000;

            double shiftX = speed * (p1.getX() - p2.getX()) / distance;
            double shiftY = speed * (p1.getY() - p2.getY()) / distance;

            //zmieniamy wsp punktu krzywej 2 wedlug wzoru
            shiftX = (double) Math.round(shiftX * 10000) / 10000;
            shiftY = (double) Math.round(shiftY * 10000) / 10000;

            //zmieniamy wsp punktu krzywej 1 wedlug wzoru
            p1.setX(p1.getX() + p1_speed_X);
            p1.setY(p1.getY() + p1_speed_Y);

            krzywa1.add(new Point("a", p1.getX(), p1.getY()));
            krzywa2.add(new Point("b", p2.getX(), p2.getY()));

            p2.przesunX(shiftX);
            p2.przesunY(shiftY);

//            Random r = new Random();
//            p1_speed_X = (-1.0 + 2.0 * r.nextDouble());
//            p1_speed_Y = (-1.0 + 2.0 * r.nextDouble());
            ticks = 0;
            //Sprawdzanie czy punkty sie dogonily 
            //Trzeba wziac pod uwage wielkosc kulki!!!
            //System.out.println(p2.obliczOdleglosc(p1));
            if (p1.obliczOdleglosc(p2) < 20.0) {
                running = false;
                System.out.println("Koniec");
            }
        }
    }

    @Override
    public void paint(Graphics g) {

//        p1.draw(g);
//        p2.draw(g);
        for (int i = 0; i<krzywa1.size(); ++i) {
            krzywa1.get(i).draw(g);
        }
        for (int i = 0; i<krzywa2.size(); ++i) {
            krzywa2.get(i).draw(g);
        }
    }

    public double generateRandom() {
        Random r = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        randomValue = (double) Math.round(randomValue * 10000) / 10000;
        return randomValue;
    }
}
