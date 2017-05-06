package GameData;


/* Class handling game data */


public class GameData {

    private int Avaible;
    protected int PlayerNO;
    private String P1;
    private String P2;
    
    //0: empty     1: x     2: O
    private int[] Map={0,0,0,0,0,0,0,0,0}; 
    private  String ErrorStr;
    private String serverurl = "http://midas.ktk.bme.hu/poti/";
    private int Refresh;

    /*
    map:

    [0  1   2
     3  4   5
     6  7   8]

     */

    //variable which describes the state of the game
    public int getAvaible() {
        return Avaible;
    }

    public void setAvaible(int avaible) {
        Avaible = avaible;
    }

    //name of player 1
    public String getP1() {
        return P1;
    }

    public void setP1(String p1) {
        P1 = p1;
    }

    //name of player 2
    public String getP2() {
        return P2;
    }

    public void setP2(String p2) {
        P2 = p2;
    }

    //the map shows the state of the game,
    //where X and O-s are currently
    public int[] getMap() {
        return Map;
    }

    public void setMap(int[] map) {
        Map = map;
    }

    //set the individual values of the map
    public  void setCell(int cell, int val) { Map[cell] = val; }

    public String getErrorStr() { return ErrorStr; }

    public void setErrorStr(String errorStr) { 
    	ErrorStr = errorStr; 
    	}

    public int getPlayerNO() {
        return PlayerNO;
    }

    public void setPlayerNO(int avaible) {
        if (avaible == 0 ) { PlayerNO = 1; }
        else if (avaible == 1 ) { PlayerNO = 2;}
        else {PlayerNO = 0; }
    }

    public void setPlayerNO_d(int playerNO) {
        PlayerNO = playerNO;
    }
    
    public int getRefresh() {
    	return Refresh;
    }
    
    public void setRefresh(int refresh) {
    	Refresh = refresh;
    }
    
    // method to get the coordinates of cells
    // in order to draw a line on the winning row/column/diagonal
    public int[] getLine_coord() {
    	int[] coords={0,0};
    	for (int i = 0; i < 3; i++) {
            {
                if (Map[3*i] != 0 && Map[3*i] == Map[3*i+1] && 
                		Map[3*i] == Map[3*i+2]) {
                	coords[0]=3*i;
                	coords[1]=3*i+2;
                    
                } //check rows
                if (Map[i] != 0 && Map[i] == Map[i+3] &&
                		Map[i] == Map[i+6])
                {
                	coords[0]=i;
                	coords[1]=i+6;
                } //check columns
            }
        }
    	if (Map[4] != 0 && Map[4] == Map[0] && Map[4] == Map[8]) {
            coords[0]=0;
            coords[1]=8;
        } //check diagonals
    	if (Map[4]!=0 && Map[4] == Map[2] && Map[4] == Map[6]) {
    		coords[0]=2;
            coords[1]=6;
    	}
    	return coords;
    }
    
    // method to generate the game data from the url string
    public GameData datafromurl(String str) {
        GameData gd = new GameData();

        String[] parts = str.split(";");

        gd.setAvaible(Integer.parseInt(parts[0]));
        gd.setP1(parts[1]);
        gd.setP2(parts[2]);
        int[] asd = new int[9];
        for (int i = 0; i < 9; i++)
        {
            asd[i] = Integer.parseInt(parts[i+3]);
        }
        gd.setMap(asd);
        gd.setRefresh(Integer.parseInt(parts[12]));
        return gd;
    }

    // method to check whether a player has won or not
    // return the player who won
    public int hasSomeoneWon() {
        int winner = 0;
        for (int i = 0; i < 3; i++) {
            {
                if (Map[3*i] != 0 && Map[3*i] == Map[3*i+1] && 
                		Map[3*i] == Map[3*i+2]) {
                    winner = Map[3*i];
                } //check rows
                if (Map[i] != 0 && Map[i] == Map[i+3] &&
                		Map[i] == Map[i+6])
                {
                    winner = Map[i];
                } //check columns
            }
        }
        if ((Map[4] != 0 && (Map[4] == Map[0] && Map[4] == Map[8]) 
        		|| (Map[4] == Map[2] && Map[4] == Map[6]))) {
            winner = Map[4];
        } //check diagonals
        return winner;
    }

    // method to check whether the game has ended
    public boolean isGameEnd() {
        boolean ended = true;
        for (int i = 0; i < 9; i++) {
            if (Map[i] == 0) {
                ended = false;
            }
        } 
        return  ended;
    }
}