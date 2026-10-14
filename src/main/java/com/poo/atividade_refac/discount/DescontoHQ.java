package com.poo.atividade_refac.discount;

import org.springframework.stereotype.Component;

@Component
public class DescontoHQ implements DescontoStrategy {

    @Override
    public boolean aplica(String categoria) {
        return "HQ".equals(categoria);
    }

    @Override
    public double aplicar(double preco) {
        return preco * 0.6;
    }
}
