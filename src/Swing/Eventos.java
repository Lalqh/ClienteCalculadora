package Swing;

import Funciones.CalculatorClient;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Eventos implements ActionListener {
    private JTextField pantalla;
    private String numero1 = "", operacionPendiente = "";
    private CalculatorClient client;

    public Eventos(JTextField pantalla, CalculatorClient client) {
        this.pantalla = pantalla;
        this.client = client;
    }

    private void agregarAText(String numero) {
        String textoPantalla = pantalla.getText();
        if (textoPantalla.equals("0")) {
            pantalla.setText(numero);
        } else {
            pantalla.setText(textoPantalla + numero);
        }
    }


    @Override

    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        String textoBoton = boton.getText();

        if (textoBoton.equals("=")) {
            if (!numero1.isEmpty() && !operacionPendiente.isEmpty()) {
                try {
                    double num = Double.parseDouble(numero1);
                    client.sendNumberAndOperation(num, operacionPendiente);
                    pantalla.setText(String.valueOf(client.receiveResult()));
                } catch (IOException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        } else if (textoBoton.equals("+") || textoBoton.equals("-") || textoBoton.equals("*")
                || textoBoton.equals("/") || textoBoton.equals("%")  || textoBoton.equals("^")) {
            if (!numero1.isEmpty()) {
                operacionPendiente = textoBoton;
                agregarAText(textoBoton);
            }
        } else if (textoBoton.equals("C")) {
            numero1 = "";
            operacionPendiente = "";
            pantalla.setText("0");
        } else {
            numero1 += textoBoton;
            agregarAText(textoBoton);
        }
    }
}