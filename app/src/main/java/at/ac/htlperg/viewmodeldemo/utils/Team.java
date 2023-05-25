package at.ac.htlperg.viewmodeldemo.utils;

import at.ac.htlperg.viewmodeldemo.R;
public enum Team {
    RED_BULL("Red Bull", R.color.RedBull),
    FERRARI("Ferrari", R.color.Ferrari),
    MERCEDES("Mercedes", R.color.Mercedes),
    ALPINE("Alpine", R.color.Alpine),
    MCLAREN("McLaren", R.color.McLaren),
    ALFA_ROMEO("Alfa Romeo", R.color.AlfaRomeo),
    ASTON_MARTIN("Aston Martin", R.color.AstonMartin),
    HAAS("Haas", R.color.Haas),
    ALPHA_TAURI("Alpha Tauri", R.color.AlphaTauri),
    WILLIAMS("Williams", R.color.Williams);

    private final String teamName;
    private final int colorResourceId;

    Team(String teamName, int colorResourceId) {
        this.teamName = teamName;
        this.colorResourceId = colorResourceId;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getColorResourceId() {
        return colorResourceId;
    }

    public static Team fromTeamName(String teamName) {
        for (Team team : Team.values()) {
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        return null;
    }
}
