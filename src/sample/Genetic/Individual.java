package sample.Genetic;

/**
 * Created by mariusz on 20/05/16.
 */
public class Individual {

    static int defaultGeneLength = 15;
    private byte[] genes = new byte[defaultGeneLength];
  //  private byte[] mutantGenes = new byte[defaultGeneLength];
    private int fitness = 0;

    // Stwórz losowego lub zadanego osobnika
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    public void generateIndividual(String learningGenes) {
        for (int i = 0; i < size(); i++) {
            byte gene = Byte.parseByte(learningGenes.substring(i,i+1));

            genes[i] = gene;
        }
    }



  //  public static void setDefaultGeneLength(int length) {
   //     defaultGeneLength = length;
   // }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }


    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
