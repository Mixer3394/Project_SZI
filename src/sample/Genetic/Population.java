package sample.Genetic;

/**
 * Created by mariusz on 20/05/16.
 */
public class Population {

    Individual[] individuals;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
        //    for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();

                newIndividual.generateIndividual("110101101011010");
                saveIndividual(0, newIndividual);

                newIndividual = new Individual();
                newIndividual.generateIndividual("101010010100101");
                saveIndividual(1, newIndividual);

                newIndividual = new Individual();
                newIndividual.generateIndividual("110010101100100");
                saveIndividual(2, newIndividual);

                newIndividual = new Individual();
                newIndividual.generateIndividual("100001100010011");
                saveIndividual(3, newIndividual);

                newIndividual = new Individual();
                newIndividual.generateIndividual("010100101100101");
                saveIndividual(4, newIndividual);
           // }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}