/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasingpoint;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Damian
 */
public class Punkt {

    private final String name;
    private double x;
    private double y;

    public Punkt(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Punkt(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void przesunX(double deltaX) {
        x = x + deltaX;
        x = (double) Math.round(x * 1000) / 1000;
    }

    public void przesunY(double deltaY) {
        y = y + deltaY;
        y = (double) Math.round(y * 10000) / 10000;
    }

    public double obliczOdleglosc(double Xa, double Ya) {
        double wynik;

        wynik = Math.sqrt((Xa - this.x) * (Xa - this.x) + (Ya - this.y) * (Ya - this.y));

        return (wynik);
    }

    public double obliczOdleglosc(Punkt p) {
        double wynik;

        wynik = Math.sqrt((p.getX() - this.x) * (p.getX() - this.x) + (p.getY() - this.y) * (p.getY() - this.y));

        return (wynik);
    }

    public void draw(Graphics g) {

        if (this.name.equals("a")) {
            g.setColor(Color.red);
        }
        else{
            g.setColor(Color.green);
        }

        g.fillOval((int) this.x , (int) this.y, 20, 20);

    }
}
