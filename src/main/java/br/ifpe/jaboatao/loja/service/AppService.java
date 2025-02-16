package br.ifpe.jaboatao.loja.service;

import org.springframework.stereotype.Service;

@Service
public class AppService {

    public String App(String name)
    {
        return "app " + name;
    }
}
