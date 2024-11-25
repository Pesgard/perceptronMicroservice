package com.example.redesNeuronales;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perceptron")
public class PerceptronController {

    private Perceptron perceptron;

    public PerceptronController() {
        perceptron = new Perceptron(2);  // Asumiendo que se trata de un perceptrón para 2 entradas
        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] salidasEsperadas = {0, 0, 0, 1};
        perceptron.entrenar(entradas, salidasEsperadas, 1000);
    }

    @PostMapping("/predecir")
    public int predecir(@RequestBody double[] entrada) {
        return perceptron.predecir(entrada);
    }
}