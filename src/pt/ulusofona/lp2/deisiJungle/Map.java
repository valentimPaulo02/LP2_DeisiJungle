package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Map {

    ArrayList<Square> map = new ArrayList<>();

    public Map(int size){
        generateMap(size);
    }

    ArrayList<Square> generateMap(int squares){

        for(int i=1; i<=squares; i++){
            if(i==squares) {
                map.add(new Square(i, "Meta", "finish.png"));

            }else if(i==squares/2+1){
                map.add(new Square(i, "Meio", "unknownPiece.png"));

            }else{
                map.add(new Square(i, "Vazio", "blank.png"));
            }
        }

        return map;
    }

    Square getSquare(int number){
        return map.get(number-1);
    }

    int getSize(){
        return map.size();
    }

    boolean isSquareValid(int number){
        if(number<1 || number>map.size()){
            return false;
        }
        return true;
    }
}
