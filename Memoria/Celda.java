package Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.awt.event.MouseListener;

    
public class Celda extends JPanel {
    private Color color = Color.WHITE;
    private int valor=0;

    public Celda(int valor){
        this.valor = valor;
        setPreferredSize(new Dimension(15, 10));
        setBackground(color);
        setBorder(new LineBorder(Color.BLACK, 1)); 
        if(getValor()==1){
            color = Color.BLACK;
            setBackground(color);
        }


        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(getValor()==0){
                    color = Color.BLACK;
                    setBackground(color);
                    setValor(1);
                }
                else{
                    color = Color.WHITE;
                    setBackground(color);
                    setValor(0);                    
                }

            }
        });
    }

    public void setValor(int nuevoValor) {
        this.valor = nuevoValor;
    }

    public int getValor() {
        return valor;
    }
}