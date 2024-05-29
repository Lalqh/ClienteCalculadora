package Swing;

import Funciones.CalculatorClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrameCalculadora extends JFrame {
    private JTextField pantalla;
    private String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "+", "=",
            "C", "%", "^"
    };

    public FrameCalculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        pantalla = new JTextField("0");
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setPreferredSize(new Dimension(300, 50));
        setLayout(new BorderLayout());
        add(pantalla, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4));
        CalculatorClient client = null;

        try {
             client = new CalculatorClient("25.52.81.76", 1234);
        }catch (IOException ex){
            System.err.println("Error: " + ex.getMessage());
        }

        Eventos eventos = new Eventos(pantalla, client);
        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            boton.addActionListener(eventos);
            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);
        setLocationRelativeTo(null);

    }

}
