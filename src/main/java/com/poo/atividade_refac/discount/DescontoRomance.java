package com.poo.atividade_refac.discount;

import org.springframework.stereotype.Component;

@Component
public class DescontoRomance implements DescontoStrategy {

    @Override
    public boolean aplica(String categoria) {
        return "ROMANCE".equals(categoria);
    }

    @Override
    public double aplicar(double preco) {
        return preco * 0.9;
    }
}
