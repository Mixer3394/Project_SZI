package sample.Genetic;

/**
 * Created by mariusz on 20/05/16.
 */
public class Start {
    public static double estimation = 0.0;
    public static String BLACK_AREA = "010100101100100";
    public static String BLUE_AREA = "001000010001011";
    public static String GREEN_AREA ="100010100000011";
    public static String BROWN_AREA ="001010101100100";
    public static String YELLOW_AREA ="100000110010001";
    public static String RED_AREA ="010100100001110";
    public static void main(String[] args) {

        startAlgorithm(BLACK_AREA);
        startAlgorithm(BLUE_AREA);


    }
    public static void startAlgorithm(String area) {
        // Set solution
        FitnessCalc.setSolution(area);

        // Create an initial population
        Population myPop = new Population(5, true);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + "      Fittest: " + myPop.getFittest().getFitness() + "       Case genes: " + getCaseGenes(myPop.getFittest()) + "      Estimation: " + calculateEstimation(myPop.getFittest(), area));
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Case genes:");
        System.out.println(getCaseGenes(myPop.getFittest()));
        System.out.println( "Final Estimation: " + calculateEstimation(myPop.getFittest(), area));

    }

    public static double calculateEstimation(Individual genesArray, String solution) {
        estimation = 0.0;

        if( genesArray.getGene(0) == 1 ||  genesArray.getGene(1) == 1 || genesArray.getGene(2) == 1)
            if(genesArray.getGene(0) == Byte.parseByte(solution.substring(0,1)) &&
                    genesArray.getGene(1) == Byte.parseByte(solution.substring(1,2)) &&
                    genesArray.getGene(2) == Byte.parseByte(solution.substring(2,3))) {
                estimation += 50;
            }
        if( genesArray.getGene(3) == 1 ||  genesArray.getGene(4) == 1 || genesArray.getGene(5) == 1)
            if(genesArray.getGene(3) == Byte.parseByte(solution.substring(3,4)) &&
                    genesArray.getGene(4) == Byte.parseByte(solution.substring(4,5)) &&
                    genesArray.getGene(5) == Byte.parseByte(solution.substring(5,6))) {
                estimation += 20;
            }

        if( genesArray.getGene(6) == 1 ||  genesArray.getGene(7) == 1)
            if(genesArray.getGene(6) == Byte.parseByte(solution.substring(6,7)) &&
                    genesArray.getGene(7) == Byte.parseByte(solution.substring(7,8))) {
                estimation += 10;
            }
        if( genesArray.getGene(8) == 1 ||  genesArray.getGene(9) == 1 || genesArray.getGene(10) == 1 ||
                genesArray.getGene(11) == 1 ||  genesArray.getGene(12) == 1 ||
                    genesArray.getGene(13) == 1 || genesArray.getGene(14) == 1)
            if(genesArray.getGene(8) == Byte.parseByte(solution.substring(8,9)) &&
                    genesArray.getGene(9) == Byte.parseByte(solution.substring(9,10)) &&
                    genesArray.getGene(10) == Byte.parseByte(solution.substring(10,11)) &&
                    genesArray.getGene(11) == Byte.parseByte(solution.substring(11,12)) &&
                    genesArray.getGene(12) == Byte.parseByte(solution.substring(12,13)) &&
                    genesArray.getGene(13) == Byte.parseByte(solution.substring(13,14)) &&
                    genesArray.getGene(14) == Byte.parseByte(solution.substring(14,15))) {
                estimation += 5;
            }
        return estimation;
    }
    public static String getCaseGenes(Individual genesArray) {
        String[] genesString = new String[15];
        String genes = "[ ";
        if(genesArray.getGene(0) == 1) {
            genesString[0] = "light";
        }
        if(genesArray.getGene(1) == 1) {
            genesString[1] = "heavy";
        }
        if(genesArray.getGene(2) == 1) {
            genesString[2] = "middleweight";
        }
        if(genesArray.getGene(3) == 1) {
            genesString[3] = "metal";
        }
        if(genesArray.getGene(4) == 1) {
            genesString[4] = "wooden";
        }
        if(genesArray.getGene(5) == 1) {
            genesString[5] = "paper";
        }
        if(genesArray.getGene(6) == 1) {
            genesString[6] = "solid";
        }
        if(genesArray.getGene(7) == 1) {
            genesString[7] = "liquid";
        }
        if(genesArray.getGene(8) == 1) {
            genesString[8] = "gray";
        }
        if(genesArray.getGene(9) == 1) {
            genesString[9] = "brown";
        }
        if(genesArray.getGene(10) == 1) {
            genesString[10] = "white";
        }
        if(genesArray.getGene(11) == 1) {
            genesString[11] = "red";
        }
        if(genesArray.getGene(12) == 1) {
            genesString[12] = "black";
        }
        if(genesArray.getGene(13) == 1) {
            genesString[13] = "blue";
        }
        if(genesArray.getGene(14) == 1) {
            genesString[14] = "yellow";
        }
        for(int i=0; i< 15; i++) {
            if(genesString[i] != null)
            genes += genesString[i] + " ";
        }
        genes += "]";
        return genes;

    }


}
