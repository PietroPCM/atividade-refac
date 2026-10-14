package com.poo.atividade_refac.discount;

import org.springframework.stereotype.Component;

@Component
public class DescontoTecnologia implements DescontoStrategy {

    @Override
    public boolean aplica(String categoria) {
        return "TECNOLOGIA".equals(categoria);
    }

    @Override
    public double aplicar(double preco) {
        return preco * 0.8;
    }
}
