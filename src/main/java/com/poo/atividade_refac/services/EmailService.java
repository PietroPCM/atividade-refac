package com.poo.atividade_refac.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviarPromocional() {
        System.out.println("Email promocional enviado");
    }
}
