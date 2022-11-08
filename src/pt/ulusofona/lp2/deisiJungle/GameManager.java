package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {

    //SPECIES
    Specie elephant = new Specie("Elefante","elephant.png", 'E');
    Specie lion = new Specie("Leão", "lion.png",'L' );
    Specie tarzan = new Specie("Tarzan", "tarzan.png", 'Z');
    Specie turtle = new Specie("Tartaruga", "turtle.png",'T');
    Specie bird = new Specie("Pássaro", "bird.png",'P');

    ArrayList<Specie> species = new ArrayList<>()
    {
        {
            add(elephant);
            add(lion);
            add(tarzan);
            add(turtle);
            add(bird);
        }
    };

    //PLAYERS
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Integer, Player> playersById = new HashMap<>();

    //OTHERS
    Map map;
    int dice;



    //MANDITORY FUNCTIONS

    public String[][] getSpecies(){
        String[][] result = new String[species.size()][3];

        for(int i = 0; i < species.size(); i++){

            result[i][0] = species.get(i).getIdentifier()+"";
            result[i][1] = species.get(i).getName();
            result[i][2] = species.get(i).getImage();

        }
        return result;
    }
    //DONE


    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo){

        //O número de jogadores.
        if(playersInfo.length < 2 || playersInfo.length > 4){
            return false;
        }

        //O mapa tem de ter, pelo menos, duas posições por cada jogador que esteja em jogo.
        if (playersInfo.length*2 > jungleSize){
            return false;
        }

        ArrayList<Integer> playerIds = new ArrayList<>();

        for(int i=0; i<playersInfo.length; i++){

            try{
                if(!playerIds.contains(Integer.parseInt(playersInfo[i][0]))){
                    playerIds.add(Integer.parseInt(playersInfo[i][0]));
                }else {
                    return false;
                }
            }catch (NumberFormatException e){
                return false;
            }

            //Verifica se a espécie existe
            boolean validSpecie=false;

            for(Specie specie : species){
                if(specie.getIdentifier() == playersInfo[i][2].charAt(0)){
                    validSpecie=true;
                    break;
                }
            }

            if(!validSpecie){
                return false;
            }

            //Verifica se o nome e valido
            if (playersInfo[i][1] == null || playersInfo[i][1].equals("")) {
                return false;
            }



            //COMO JA PASSOU AS VALIDAÇOES COLOCA AS VARIAVEIS
            int id=Integer.parseInt(playersInfo[i][0]);
            String name=playersInfo[i][1];
            char specieIdentifier=playersInfo[i][2].charAt(0);

            players.add(new Player(id,initialEnergy,getSpicieById(specieIdentifier),name));
            playersById.put(id, new Player(id,initialEnergy,getSpicieById(specieIdentifier),name));

        }
        map = new Map(jungleSize);


        //Preenche a 1 casa
        for(Player player : players){
            map.getSquare(1).addPlayer(player);
        }


        return true;
    }
    //ON GOING


    public int[] getPlayerIds(int squareNr){

        ArrayList<Player> playersInSquare = new ArrayList<>(map.getSquare(squareNr).getPlayers());

        int[] playerIDs = new int[playersInSquare.size()];




        if(playersInSquare.size()==0 || !map.isSquareValid(squareNr)){
            return new int[]{};
        }

        for(int i=0; i<playersInSquare.size(); i++){
            playerIDs[i]=playersInSquare.get(i).getId();
        }

        return playerIDs;
    }
    //DONE


    public String[] getSquareInfo(int squareNr){

        if(!map.isSquareValid(squareNr)){
            return null;
        }

        Square square = map.getSquare(squareNr);
        String[] squareInfo = new String[3];

        squareInfo[0] = square.getImage();
        squareInfo[1] = square.getType();
        squareInfo[2] = square.getIdPlayersInString();

        return squareInfo;
    }
    //DONE


    public String[] getPlayerInfo(int playerId){

        String[] playerInfo = new String[4];

        playerInfo[0] = playersById.get(playerId).getId()+"";
        playerInfo[1] = playersById.get(playerId).getName();
        playerInfo[2] = playersById.get(playerId).getSpecieId()+"";
        playerInfo[3] = playersById.get(playerId).getEnergy()+"";

        return playerInfo;
    }
    //DONE


    public String[] getCurrentPlayerInfo(){
        return null;
    }
    //ON GOING


    public String[][] getPlayersInfo(){

        String[][] playersInfo = new String[players.size()][4];

        for(int i=0; i< players.size();i++){

            playersInfo[i][0] = players.get(i).getId()+"";
            playersInfo[i][1] = players.get(i).getName();
            playersInfo[i][2] = players.get(i).getSpecieId()+"";
            playersInfo[i][3] = players.get(i).getEnergy()+"";
        }

        return playersInfo;
    }
    //DONE

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations){
        return true;
    }

    public String[] getWinnerInfo(){
        return null;
    }

    public ArrayList<String> getGameResults(){
        return new ArrayList<>();
    }

    public JPanel getAuthorsPanel(){
        return null;
    }

    public String whoIsTaborda(){
        return "Wrestling";
    }
    //DONE












    //ADDITIONAL FUNCTIONS
    int spinDice(){
        int random = ThreadLocalRandom.current().nextInt(1,7);

        return dice=random;
    }

    Specie getSpicieById(char id){
        for(Specie specie : species){
            if(specie.getIdentifier() == id){
                return specie;
            }
        }
        return null;
    }
}