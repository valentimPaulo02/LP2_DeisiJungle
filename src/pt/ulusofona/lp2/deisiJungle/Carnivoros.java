package pt.ulusofona.lp2.deisiJungle;

public abstract class Carnivoros extends Specie{

    @Override
    public int eat(char foodId, int initialEnergy, int turn){

        if(foodId == 'e'){
            return -20;
        }

        if(foodId == 'a'){
            return 15;
        }

        if(foodId == 'c'){
            if(turn > 12){
                return -50;
            }else{
                return 50;
            }
        }
        return -1;
    }
}
