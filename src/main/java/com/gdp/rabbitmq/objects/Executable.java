package com.gdp.rabbitmq.objects;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zerosign
 */
public abstract class Executable implements Runnable {
    protected abstract void execute();

    @Override
    public void run() {
        execute();
    }
    
}
