package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Square {

    int number;
    String type, image;
    ArrayList<Player> players = new ArrayList<>();


    public Square(){
    }

    public Square(int number, String type, String image) {
        this.number = number;
        this.type = type;
        this.image = image;
    }

    int getNumber(){
        return number;
    }

    String getType(){
        return type;
    }

    String getImage(){
        return image;
    }

    ArrayList<Player> getPlayers(){
        return players;
    }

    String getIdPlayersInString(){
        String result = "";

        for(int i=0; i<players.size(); i++){

            if(i== players.size()-1){
                result += ""+players.get(i).getId();
            }else{
                result+= ""+players.get(i).getId()+",";
            }
        }
        return result;
    }

    void addPlayer(Player player){
        players.add(player);
    }

    void removePlayer(Player player){
        players.remove(player);
    }

}
