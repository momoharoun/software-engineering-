package com.example.recipico.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {

    EASY("Easy"),
    MODERATE("Moderate"),
    HARD("Hard");

    private String label;
    
    public String getLabel(){
        return label;
    }
}

