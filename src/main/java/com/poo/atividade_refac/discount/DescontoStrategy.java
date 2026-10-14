package com.poo.atividade_refac.discount;

public interface DescontoStrategy {
    boolean aplica(String categoria);

    double aplicar(double preco);
}
