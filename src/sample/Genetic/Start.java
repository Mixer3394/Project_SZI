package sample.Genetic;

import com.sun.org.apache.bcel.internal.generic.POP;
import sample.CasesBase;

import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by mariusz on 20/05/16.
 */
public class Start {
    public static int estimation = 0;
    public static String BLACK_AREA = "010100101100100";
    public static String BLUE_AREA = "001000010001011";
    public static String GREEN_AREA ="100010100000011";
    public static String BROWN_AREA ="001010101100100";
    public static String YELLOW_AREA ="100000100010001";
    public static String RED_AREA ="010100100001110";
    public static int finalEstimation = 0;
    public static int numberOfGenerations = 0;
    public static CasesBase casesBase;
    public static Population myPop;
    public static String area;
    public static Population myPopBlack,myPopBlue,myPopGreen,myPopBrown,myPopYellow,myPopRed;
    public Start() {

        startAlgorithm(BLACK_AREA);
    }

    public static void main(String[] args) {
    }
    public static void startAlgorithm(String area) {
        // Ustaw oczekiwane rozwiązanie (genotyp odpowiedniej strefy)

        //  for(int i=0; i<20; i++) {
        FitnessCalc.setSolution(area);

        // Stwórz losowo lub z wcześniej zdefiniowanych osobników nową populację
        myPopBlack = new Population(50, true);
        getResults(myPopBlack, area);
        myPopBlack.deleteBestIndividual();
        myPop = myPopBlack;
    }

    public static void rewritePopulation(Population myPopOld, Population myPopNew) {
        for (int x = 0; x < myPopOld.size(); x++) {
            myPopNew.saveIndividual(x, myPopOld.getIndividual(x));
        }
    }

    public static void rewriteMutants(Population myPopOld, Population myPopNew) {
        for (int x = 0; x < 20; x++) {
            myPopNew.saveMutant(x, myPopOld.getMutant(x));
        }
    }
        // Liczba generacji

        public static void getResults(Population myPop, String area2) {
            area = area2;
                numberOfGenerations++;
                System.out.println("");

                System.out.println("Generation: " + numberOfGenerations + "      Fittest: " + myPop.getFittest().getFitness() + "       Best current case genes: " + getCaseGenes(myPop.getFittest(),numberOfGenerations) + "      Estimation: " + calculateEstimation(myPop.getFittest(), area)+"/"+calculateSolutionEstimation(area));

                //następna generacja
                myPop = Algorithm.evolvePopulation(myPop);
    }

    public static void getResults() {
            numberOfGenerations++;
            System.out.println("");
            System.out.println("Generation: " + numberOfGenerations + "      Fittest: " + myPop.getFittest().getFitness() + "       Best current case genes: " + getCaseGenes(myPop.getFittest(),numberOfGenerations) + "      Estimation: " + calculateEstimation(myPop.getFittest(), area)+"/"+calculateSolutionEstimation(area));

            //następna generacja

        if((calculateEstimation(myPop.getFittest(), area) >= calculateSolutionEstimation(area))) {
            setBlueArea();
        }
        if((calculateEstimation(myPop.getFittest(), area) >= calculateSolutionEstimation(area))) {
            setGreenArea();
        }
        if((calculateEstimation(myPop.getFittest(), area) >= calculateSolutionEstimation(area))) {
            setBrownArea();
        }
        if((calculateEstimation(myPop.getFittest(), area) >= calculateSolutionEstimation(area))) {
            setYellowArea();
        }
        if((calculateEstimation(myPop.getFittest(), area) >= calculateSolutionEstimation(area))) {
            setRedArea();
        }
        myPop = Algorithm.evolvePopulation(myPop);


    }

    public static void setBlueArea() {
        FitnessCalc.setSolution(BLUE_AREA);
        myPopBlue = new Population(50, false);
        rewritePopulation(myPopBlack, myPopBlue);
        rewriteMutants(myPopBlack, myPopBlue);
        getResults(myPopBlue, BLUE_AREA);
        myPopBlue.deleteBestIndividual();
        myPop = myPopBlue;
    }
    public static void setGreenArea() {
        FitnessCalc.setSolution(GREEN_AREA);
        myPopGreen = new Population(50, false);
        rewritePopulation(myPopBlue, myPopGreen);
        rewriteMutants(myPopBlue, myPopGreen);
        getResults(myPopGreen, GREEN_AREA);
        myPopGreen.deleteBestIndividual();
        myPop = myPopGreen;
    }
    public static void setBrownArea() {
        FitnessCalc.setSolution(BROWN_AREA);
        myPopBrown = new Population(50, false);
        rewritePopulation(myPopGreen, myPopBrown);
        rewriteMutants(myPopGreen, myPopBrown);
        getResults(myPopBrown, BROWN_AREA);
        myPopBrown.deleteBestIndividual();
        myPop = myPopBrown;
    }
    public static void setYellowArea() {

        FitnessCalc.setSolution(YELLOW_AREA);
        myPopYellow = new Population(50, false);
        rewritePopulation(myPopBrown, myPopYellow);
        rewriteMutants(myPopBrown, myPopYellow);
        getResults(myPopYellow, YELLOW_AREA);
        myPopYellow.deleteBestIndividual();
        myPop = myPopYellow;

    }
    public static void setRedArea() {
        FitnessCalc.setSolution(RED_AREA);
        myPopRed = new Population(50, false);
        rewritePopulation(myPopYellow,myPopRed);
        rewriteMutants(myPopYellow,myPopRed);
        getResults(myPopRed,RED_AREA);
        myPopRed.deleteBestIndividual();
        myPop = myPopRed;

    }
//}


    public static double calculateEstimation(Individual genesArray, String solution) {
        estimation = 0;

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

    public static int calculateSolutionEstimation(String solution) {
        finalEstimation = 0;
        boolean blockColor = false;
        boolean blockWeight = false;
        boolean blockMaterial = false;
        boolean blockState = false;
        for(int i = 0; i< solution.length(); i++) {
            if(Byte.parseByte(solution.substring(i,i+1)) == 1) {
                if(i<3) {
                    finalEstimation += 50;
                    blockWeight = true;
                }
                if(i>2 && i<6) {
                    finalEstimation += 20;
                    blockMaterial = true;
                }
                if(i>5 && i<8) {
                    finalEstimation += 10;
                    blockState = true;
                }
                if(i>7 && i<15 && !blockColor) {
                    finalEstimation += 5;
                    blockColor = true;
                }
            }
        }
        return finalEstimation;
    }
    public static String getCaseGenes(Individual genesArray, int it) {
        String[] genesString = new String[15];
        casesBase = new CasesBase();
        String genes = "[ ";
        if(genesArray.getGene(0) == 1) {
            genesString[0] = "light";
            casesBase.addData(it,"light");
        }
        if(genesArray.getGene(1) == 1) {
            genesString[1] = "heavy";
            casesBase.addData(it,"heavy");
        }
        if(genesArray.getGene(2) == 1) {
            genesString[2] = "middleweight";
            casesBase.addData(it,"middleweight");
        }
        if(genesArray.getGene(3) == 1) {
            genesString[3] = "metal";
            casesBase.addData(it,"metal");
        }
        if(genesArray.getGene(4) == 1) {
            genesString[4] = "wooden";
            casesBase.addData(it,"wooden");
        }
        if(genesArray.getGene(5) == 1) {
            genesString[5] = "paper";
            casesBase.addData(it,"paper");
        }
        if(genesArray.getGene(6) == 1) {
            genesString[6] = "solid";
            casesBase.addData(it,"solid");
        }
        if(genesArray.getGene(7) == 1) {
            genesString[7] = "liquid";
            casesBase.addData(it,"liquid");
        }
        if(genesArray.getGene(8) == 1) {
            genesString[8] = "gray";
            casesBase.addData(it,"gray");
        }
        if(genesArray.getGene(9) == 1) {
            genesString[9] = "brown";
            casesBase.addData(it,"brown");
        }
        if(genesArray.getGene(10) == 1) {
            genesString[10] = "white";
            casesBase.addData(it,"white");
        }
        if(genesArray.getGene(11) == 1) {
            genesString[11] = "red";
            casesBase.addData(it,"red");
        }
        if(genesArray.getGene(12) == 1) {
            genesString[12] = "black";
            casesBase.addData(it,"black");
        }
        if(genesArray.getGene(13) == 1) {
            genesString[13] = "blue";
            casesBase.addData(it,"blue");
        }
        if(genesArray.getGene(14) == 1) {
            genesString[14] = "yellow";
            casesBase.addData(it,"yellow");
        }
        for(int i=0; i< 15; i++) {
            if(genesString[i] != null)
            genes += genesString[i] + " ";
        }
        genes += "]";
        return genes;

    }


}
