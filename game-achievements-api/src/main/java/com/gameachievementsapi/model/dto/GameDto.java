package com.gameachievementsapi.model.dto;

public class GameDto {

    public GameDto (String displayName){
        this.displayName = displayName;
    }

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
