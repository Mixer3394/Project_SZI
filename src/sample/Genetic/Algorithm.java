package sample.Genetic;

/**
 * Created by mariusz on 20/05/16.
 */
public class Algorithm {

    // parametry algorytmu
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.01;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

   // Rozwiń populację
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Zachowaj najlepszego osobnika
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Skrzyżuj populację
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Przejdź przez wszystkie osobniki populacji i skrzyżuj je
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }
        for (int i = 0; i < 20; i++) {
            Individual mut1 = tournamentSelection(pop);
            Individual mut2 = tournamentSelection(pop);
            Individual newMut = crossover(mut1, mut2);
            newPopulation.saveMutant(i, newMut);
        }

        // Zmutuj populację
        for (int i = 0; i < newPopulation.size(); i++) {
            if (Math.random() <= mutationRate) {
                int randomId = (int) (Math.random() * 20);
                mutate(newPopulation.getIndividual(i), newPopulation.getMutant(randomId));
                System.out.print("");
                System.out.print(" Mutation!");
            }
        }

        return newPopulation;
    }

    // Krzyżuj populację
    // Jeżeli wylosowana liczba jest mniejsza od 0.5 weź gen pierwszego osobnika w przeciwnym razie weź gen drugiego
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Zmutuj osobnika
    private static void mutate(Individual indiv, Individual randomIndiv) {
        // Przejdź po wszystkich genach osobnika
        for (int i = 0; i < indiv.size(); i++) {
          //  if (Math.random() <= mutationRate) {
                // Jeżeli wylosowana liczba jest mniejsza od mutation rate losuj gen 0 lub 1
            int firstGen = (int) (Math.random() * 15);
            int secondGen = (int) (Math.random() * 15);
                byte tempGen = indiv.getGene(firstGen);
                indiv.setGene(firstGen, randomIndiv.getGene(secondGen));
                randomIndiv.setGene(secondGen,tempGen);

          //  }
        }
    }

    // losowanie 100 paczek rozlowosanie do 8 skrzyń, mutacje za pozostałych paczek
    // Wybierz osobniki do skrzyżowania
    private static Individual tournamentSelection(Population pop) {
        // Stwórz populację do konkursu
        Population tournament = new Population(tournamentSize, false);
        // Dla każdego z miejsc w konkursie weź losowego osobnika
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Zwróć najlepiej przystosowanego osobnika
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}