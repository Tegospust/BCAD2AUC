/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ounis.bcad2auc;

/**
 *
 * @author AndroidDev
 */
public class YearDescription {
    
    private int year;
    public int getYear() {
        return this.year;
    }
    
    private String description;
    public String getDescription() {
        return this.description;
    }
    
    
    public YearDescription(int aYear, String aDescription) {
        this.year = aYear;
        this.description = aDescription;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
    
}
