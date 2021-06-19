package com.ahs.football;

public class PlayerModel {

    String ShirtNumber , PlayerName, Position;



    public PlayerModel(String shirtNumber, String playerName, String position) {
        ShirtNumber = shirtNumber;
        PlayerName = playerName;
        Position = position;
    }

    public String getShirtNumber() {
        return ShirtNumber;
    }

    public void setShirtNumber(String shirtNumber) {
        ShirtNumber = shirtNumber;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }
}
