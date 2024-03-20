package pokedigi;

import pokedigi2.Digimon;
import pokedigi2.Pokemon;
import pokedigi2.Fighter;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Jose
 */
public class FightSimulator {

    //array of fighters
    private Fighter[] fighters;
    //number of fighters (capacity of array)
    private int maxFighters; 
    //actual number of fighters
    private int numFighters;
    //scoreboard
    private int[] scores;
    
    public static void main(String[] args) {
        FightSimulator ap = new FightSimulator();
        ap.startLeague2();
    }

    private void startLeague2() {
        //instantiate array of fighters
        maxFighters = 8; 
        fighters = new Fighter[maxFighters];
        //create fighters
        setUpFighters();
        //display fighters
        displayFighters();
        //start competition. Random selection, one-to-one fight and elimination of looser one.
        Random rnd = new Random();
        do {
            //randomly select two fighters (two indexs in array of fighters)
            int index1 = rnd.nextInt(numFighters);
            int index2;
            do {
                index2 = rnd.nextInt(numFighters);
            } while (index1 == index2);
            //get fighters (fetch from array)
            Fighter f1 = fighters[index1];
            Fighter f2 = fighters[index2];
            //display fighter information
            printFighterState(f1);
            printFighterState(f2);
            //do fight
            Fighter winner = doFight(f1, f2);
            //eliminate looser from array of fighters
            int indexToRemove = (winner == f1) ? index2 : index1; 
            numFighters = compactArray(
                    fighters, 
                    numFighters, 
                    indexToRemove);
            //display fighters
            displayFighters();
        } while (numFighters > 1);  //it can only be one!
        System.out.println("Game over!");
    }
    
    private void startLeague1() {
        //instantiate array of fighters
        maxFighters = 8; 
        fighters = new Fighter[maxFighters];
        //create fighters
        setUpFighters();
        //display fighters
        displayFighters();
        //create scoreboard and set initial scores to 0
        scores = new int[maxFighters];
        Arrays.fill(scores, 0);
        //start competition (all fight against all once)
        for (int i = 0; i < numFighters-1; i++) {
            for (int j = i+1; j < numFighters; j++) {
                //get fighters
                Fighter fighter1 = fighters[i];
                Fighter fighter2 = fighters[j];
                //do fight
                Fighter winner = doFight(fighter1, fighter2);
                //increase winner's score
                int winnerIndex = (winner == fighter1) ? i : j;
                scores[winnerIndex]++;
                //reset fighters to initial state
                setUpFighters();
            }
        }
        displayScores();
    }

    private void setUpFighters() {
        numFighters = 0;
        fighters[0] = new Pokemon("poke01", 300); numFighters++;
        fighters[1] = new Digimon("digi01"); numFighters++;
        fighters[2] = new Pokemon("poke02", 600); numFighters++;
//        fighters[3] = new Digimon("digi02"); numFighters++;
//        fighters[4] = new Digimon("digi03"); numFighters++;
//        fighters[5] = new Pokemon("poke03", 700); numFighters++;
//        fighters[6] = new Pokemon("poke04", 800); numFighters++;
//        fighters[7] = new Digimon("digi04"); numFighters++;
    }    
    
    /**
     * displays fighter's information
     */
    private void displayFighters() {
        for (int i = 0; i < numFighters; i++) {
            System.out.println(fighters[i].toString());
        }
//        for (Fighter fighter : fighters) {
//            System.out.println(fighter.toString());
//        }
    }

    /**
     * displays scoreboard
     */
    private void displayScores() {
        System.out.println("===Scoreboard===");
        for (int i=0; i<numFighters; i++) {
            System.out.format("%s: %d points\n", fighters[i].getName(), scores[i]);
        }
    }    
    
    /**
     * implements fight between two fighters
     *
     * @param f1 the 1st fighter
     * @param f2 the 2nd fighter
     * @return the fighter who wins
     */
    private Fighter doFight(Fighter f1, Fighter f2) {
        System.out.format("==Fight: %s versus %s==\n", f1.getName(), f2.getName());
        Fighter winner = null;
        boolean f1Alive = true;
        boolean f2Alive = true;
        do {
            //f1 attacks f2
            f2Alive = doAttackTo(f1, f2);
            if (f2Alive) { //if still alive
                //f2 attacks f1
                f1Alive = doAttackTo(f2, f1);
            }
            //display state of each fighter
            printFighterState(f1);
            printFighterState(f2);
        } while (f1Alive && f2Alive);  //keep on fighting while both are alive
        winner = (f1Alive ? f1 : f2);  //get the winner (the one who is still alive)
        System.out.println(">The winner is: " + winner.toString()); 
        return winner;
    }

    /**
     * implements attack from 'source' to 'target'
     *
     * @param source the one who attacks
     * @param target the one who receives attack
     * @return true if target is alive, false otherwise
     */
    private boolean doAttackTo(Fighter source, Fighter target) {
        int force = source.attack();
        boolean alive = target.receiveAttack(force);
        return alive;
    }

    /**
     * prints state data of fighter
     *
     * @param f reference to fighter
     */
    private void printFighterState(Fighter f) {
        System.out.format(
                "type:%s, name:%s, lifePoints:%d\n",
                f.getClass().getSimpleName(),
                f.getName(),
                f.getLifePoints());
    }

    /**
     * remove element at 'indexToRemove' from array and compacts array
     * @param array the array to compact
     * @param numElements actual number of elements in array
     * @param indexToRemove the index of the elemento to be removed
     * @return actual number of elements in array
     */
    private int compactArray(
            Fighter[] array, 
            int numElements,
            int indexToRemove) {
        
        for (int i = indexToRemove+1; i < numElements; i++) {
            array[i-1] = array[i];
        }
        numElements--;
        return numElements;
    }
    
}
