package tortilla.nexuiz;

/**
 * A Nexuiz player object.
 * For each player in a server, show name, ping, score.
 * @author dmaz
 */
public class Player {

    private String name;
    private String coloredName;
    private int score;
    private int ping;
//    private int team;

    /**
     * Whether this player is a bot.
     * @return True if player is a bot.
     */
    public boolean isBot() {
        return getPing() == 0;
    }

    /**
     * Whether this player is spectating.
     * @return true if player is spectating.
     */
    public boolean isSpec() {
        return getScore() == -666;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param newName
     */
    public void setName(String newName) {
        String plainName = PlayerUtils.sanitizeName(PlayerUtils.decolorName(newName));
        if (plainName.equals(newName)) {  // Name is uncolored
            this.setColoredName(newName);
        } else {
            this.setColoredName(PlayerUtils.nexuizColorsToHtml(PlayerUtils.sanitizeName(newName)));
        }
        this.name = plainName;
    }

    /**
     * 
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * 
     * @param score
     */
    public void setScore(String score) {
        this.score = Integer.parseInt(score);
    }

    /**
     * 
     * @return
     */
    public int getPing() {
        return ping;
    }

    /**
     * 
     * @param ping
     */
    public void setPing(String ping) {
        this.ping = Integer.parseInt(ping);
    }

//    /**
//     * Sets this player's team.
//     * 0 - Spec
//     * 1 - Red
//     * 2 - Blue
//     * TODO: Find other team indices.
//     * @param index of team
//     */
//    public void setTeam(String index) {
//        this.team = Integer.parseInt(index);
//    }
    

    /**
     * @return the coloredName
     */
    public String getColoredName() {
        return coloredName;
    }

    /**
     * @param coloredName the coloredName to set
     */
    public void setColoredName(String coloredName) {
        this.coloredName = coloredName;
    }
}
