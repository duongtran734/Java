import java.util.NoSuchElementException;
/**
 * Spinner Class, allowing the spinner to do all interaction method that a spinner can do.
 * @author Steve Tran
 */
public class Spinner {
    //you may NOT add any additional instance variables or methods
    //to the Spinner, all needed instance variables are created in
    //the DO NOT EDIT section
    /**
     * Hand class.
     */
    private static class Hand {
        //add any additional instance variables here
        //no additional methods (not even private ones)
        /**
         * Empty plate.
         */
        private Plate plate = null;
        /**
         * Catch the plate.
         * @param plate Object plate
         */
        public void catchPlate(Plate plate) {
            //if trying to catch a plate when full, throw a RuntimeException
            //with the message "Catching hand not empty"
            if(this.hasPlate()){
                throw new RuntimeException("Catching hand not empty");
            }
            //if trying to catch a null plate, throw a RuntimeException
            //with the message "Can't catch a plate that doesn't exist!"
            if(plate == null){
                throw new RuntimeException("Can't catch a plate that doesn't exist!");
            }
            else{
                this.plate=plate;
            }
            //otherwise keep the plate in this hand
        }
        /**
         * Remove the plate from hand.
         * @return plate removed
         */
        public Plate tossPlate() {
            //if trying to throw a plate when this hand does not have
            //a plate, throw a RuntimeException with the message
            //"Tossing hand was empty"
            if(this.plate == null){
                throw new RuntimeException("Tossing hand was empty");
            }
            else{
                Plate value = this.plate;
                this.plate = null;
                return value;
            }
            //otherwise remove the plate from this hand
            //and return the plate you removed
        }
        /**
         * Check to see if has plate or not.
         * @return true for yes, false for no
         */
        public boolean hasPlate() {
            if(this.plate==null){
                return false;
            }
            return true;
            //return true if you have a plate, false otherwise
        }

        /**
         * String representation of the plate holding or not holding.
         * @return three empty spaces for no plate, and plate value as string for has plate
         */
        public String toString() {
            //if this hand is empty, return the string "   " (three spaces)
            //otherwise return the plate's string value
            if(this.hasPlate()==false){
                String empty = "   ";
                return empty;
            }
            else {
                return plate.toString();
            }
        }
    }

    /**
     * Hand 1 catch plate from hand 2.
     */
    public void passPlate() {
        //put a plate from hand 2 and pass it to hand 1
        //this can be done with a single line of code
        hands[0].catchPlate(hands[1].tossPlate());
    }
    /**
     * Push plate from hand 1 into bin.
     */
    public void putDownPlate() {
        //put a plate from hand 1 and put it in the bin
        //this can be done with a single line of code
        bin.push(hands[0].tossPlate());
    }
    /**
     * Pop from bin into hand 1.
     */
    public void pickUpPlate() {
        if(bin.isEmpty()){
            throw new RuntimeException("Can't pickup a plate that doesn't exist!");
        }else{
            hands[0].catchPlate(bin.pop());}
        //take a plate out of the bin and put it in hand 1

        //if there are no plates in the bin, throw a RuntimeException
        //with the message "Can't pickup a plate that doesn't exist!"
    }
    /**
     * Enqueue the plate from hand 1 into air.
     */
    public void spinPlate() {
        if(!air.enqueue(hands[0].plate)){
            throw new RuntimeException("Too many plates in the air!");
        }else{
            hands[0].tossPlate();}
        //take a plate from hand 1 and put it in the air

        //if the air can't hold anymore plates, throw a RuntimeException
        //with the message "Too many plates in the air!"
    }
    /**
     * Dequeue plate from air into hand 2.
     */
    public void catchPlate() {
        if(air.isEmpty()){
            throw new RuntimeException("Can't catch a plate that doesn't exist!");
        }
        else{
            hands[1].catchPlate(air.dequeue());
        }
        //take a plate out of the air and put it in hand 2

        //if there are no plates in the air, throw a RuntimeException
        //with the message "Can't catch a plate that doesn't exist!"
    }

    // --------------------------------------------------------
    // testing code goes here... edit this as much as you want!
    // --------------------------------------------------------
    /**
     * Run the tests cases for the Spinner class.
     * @param args argument array String
     */
    public static void main(String[] args) {
        Spinner spin = new Spinner(5);
        spin.pickUpPlate();
        spin.spinPlate();
        spin.pickUpPlate();
        spin.spinPlate();
        spin.pickUpPlate();
        spin.spinPlate();
        spin.pickUpPlate();
        spin.spinPlate();
        spin.pickUpPlate();
        spin.spinPlate();
        spin.catchPlate();
        spin.passPlate();
        spin.spinPlate();
        spin.catchPlate();
        spin.passPlate();
        spin.putDownPlate();
        spin.catchPlate();
        spin.passPlate();
        spin.putDownPlate();
        spin.pickUpPlate();
        spin.spinPlate();

        System.out.println(spin);

    }

    // --------------------------------------------------------
    // DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
    // --------------------------------------------------------
    /**
     * Create air from air class to hold plates in the air.
     */
    private final Air air = new Air();
    /**
     * Create bin from bin class to hold plates in the bin.
     */
    private final Bin bin = new Bin();
    /**
     * Two hands to hold plates.
     */
    private final Hand[] hands = new Hand[2];

    //spinners have two hands and starts with a bin full of plates
    /**
     * A spinner has two hands.
     * @param totalPlates for the bin to hold
     */
    public Spinner(int totalPlates) {
        hands[0] = new Hand();
        hands[1] = new Hand();

        for(int i = totalPlates; i > 0; i--) {
            this.bin.push(new Plate(i));
        }
    }

    //this does the beautiful ascii art :)
    /**
     * Allowing us the see the spinner in action.
     * @return result String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String[] personParts = {"   "+air.toString()+"\n", "\n", "    "+hands[0].toString()+"( )"+hands[1].toString()+"\n", "     \\__|__/\n", "        |\n", "        |\n", "       / \\\n", "      /   \\\n"
        };

        String[] stackParts = this.bin.toString().split("[|]");

        int total = (this.bin.size() < personParts.length) ? personParts.length : this.bin.size();
        for(int i = total; i >= 0; i--) {
            sb.append((this.bin.size()-1 < i) ? "   " : stackParts[stackParts.length-i-1]);
            if(i < personParts.length) {
                sb.append(personParts[personParts.length-i-1]);
            }
            else {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
