package com.example.redesNeuronales;

public class Perceptron {
    private double[] pesos;
    private double tasaDeAprendizaje = 0.1;
    private int numeroDeEntradas;

    // Constructor
    public Perceptron(int numeroDeEntradas) {
        this.numeroDeEntradas = numeroDeEntradas;
        this.pesos = new double[numeroDeEntradas + 1];  // +1 para el sesgo
        // Inicializa los pesos aleatoriamente entre -1 y 1
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = Math.random() * 2 - 1;  // valores entre -1 y 1
        }
    }

    // Función de activación (escalón)
    public int activacion(double suma) {
        return (suma >= 0) ? 1 : 0;
    }

    // Método para entrenar el perceptrón
    public void entrenar(double[][] entradas, int[] salidasEsperadas, int epocas) {
        for (int epoca = 0; epoca < epocas; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                double[] entrada = entradas[i];
                int salidaEsperada = salidasEsperadas[i];
                // Calcula la salida del perceptrón
                int salidaCalculada = predecir(entrada);
                // Calcula el error
                int error = salidaEsperada - salidaCalculada;
                // Ajusta los pesos
                for (int j = 0; j < pesos.length - 1; j++) {
                    pesos[j] += tasaDeAprendizaje * error * entrada[j];
                }
                // Ajusta el sesgo
                pesos[pesos.length - 1] += tasaDeAprendizaje * error;
            }
        }
    }

    // Método para predecir la salida del perceptrón
    public int predecir(double[] entrada) {
        double suma = 0;
        for (int i = 0; i < entrada.length; i++) {
            suma += entrada[i] * pesos[i];
        }
        suma += pesos[pesos.length - 1]; // Añadir sesgo
        return activacion(suma);
    }

    public static void main(String[] args) {
        // Entradas para AND
        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] salidasEsperadas = {0, 0, 0, 1};  // Salidas para AND

        Perceptron perceptron = new Perceptron(2);
        perceptron.entrenar(entradas, salidasEsperadas, 1000);

        // Probar el modelo
        System.out.println("Predicción para (1, 1): " + perceptron.predecir(new double[]{1, 1}));
        System.out.println("Predicción para (0, 1): " + perceptron.predecir(new double[]{0, 1}));
    }
}
