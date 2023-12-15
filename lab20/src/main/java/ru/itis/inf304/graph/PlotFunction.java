package ru.itis.inf304.graph;
import javax.swing.*;
import java.awt.*;

public class PlotFunction extends JFrame{

    private IFunction function;

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    public PlotFunction(IFunction function) {
        super("График функции");
        this.function = function;

        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.repaint();
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font currentFont = g2d.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize()*3F);
        g2d.setFont(newFont);
        g2d.setColor(new Color(5, 4, 4));
        g2d.drawString("Парабола", 50 , 100);
        // ордината
        for(int i = 0; i < 800; i++) {
            g2d.fillRect(i, 300, 2, 2);
        }
        // абсцисса
        for(int i = 0; i < 600; i++) {
            g2d.fillRect(400, i, 2, 2);
        }

        for(int i = 0; i < 800; i += 5) {
            g2d.fillRect(i, 299, 1, 5);
        }

        for(int i = 0; i < 600; i += 5) {
            g2d.fillRect(399, i, 5, 1);
        }

        g2d.setColor(new Color(255, 0, 0));
        for(int i = 0; i < 800; i++) {
            g2d.fillRect(i, (int) function.calculate(i), 2, 2);
        }
    }
}
