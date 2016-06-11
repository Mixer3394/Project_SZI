package sample;

import java.util.*;

/**
 * Created by Rafał on 2016-05-15.
 */
public class CandidateEleminationLearningStrategy implements LearningStrategy {
    private List<String> blackArea;
    private List<String> blueArea;
    private List<String> greenArea;
    private List<String> brownArea;
    private List<String> yellowArea;
    private List<String> redArea;

    private int numberOfProperties;


    public CandidateEleminationLearningStrategy() {
        System.out.println("Using CandidateEliminationLearningStrategy");
        numberOfProperties = 4;
        learn();
    }

    private void learn() {
        Map<List<String>, Boolean> blackAreaLearningSet = prepareBlackAreaLearningSet();
        Map<List<String>, Boolean> blueAreaLearningSet = prepareBlueAreaLearningSet();
        Map<List<String>, Boolean> greenAreaLearningSet = prepareGreenAreaLearningSet();
        Map<List<String>, Boolean> brownAreaLearningSet = prepareBrownAreaLearningSet();
        Map<List<String>, Boolean> yellowAreaLearningSet = prepareYellowAreaLearningSet();
        Map<List<String>, Boolean> redAreaLearningSet = prepareRedAreaLearningSet();

        blackArea = learnArea(blackAreaLearningSet);
        blueArea = learnArea(blueAreaLearningSet);
        greenArea = learnArea(greenAreaLearningSet);
        brownArea = learnArea(brownAreaLearningSet);
        yellowArea = learnArea(yellowAreaLearningSet);
        redArea = learnArea(redAreaLearningSet);


        System.out.println("Black area " + blackArea);
        System.out.println("Blue area " + blueArea);
        System.out.println("Green area " + greenArea);
        System.out.println("Brown area " + brownArea);
        System.out.println("Yellow area " + yellowArea);
        System.out.println("Red area " + redArea);

    }

    private Map<List<String>, Boolean> prepareBlueAreaLearningSet() {
        //should be liquid and metal
        Map<List<String>, Boolean> propertyDecision = new HashMap<>();

        List<String> properties;

        properties = new ArrayList<>();
        properties.add("white");
        properties.add("metal");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("metal");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("paper");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("heavy");
        properties.add("liquid");
        propertyDecision.put(properties, true);
        return propertyDecision;
    }

    private Map<List<String>, Boolean> prepareGreenAreaLearningSet() {
        //Should be transparent, light, solid
        Map<List<String>, Boolean> propertyDecision = new HashMap<>();

        List<String> properties;

        properties = new ArrayList<>();
        properties.add("white");
        properties.add("transparent");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("transparent");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("paper");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("paper");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("transparent");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);

        return propertyDecision;

    }

    private Map<List<String>, Boolean> prepareBrownAreaLearningSet() {
        //Should be labelled - rest doesn't matter
        Map<List<String>, Boolean> propertyDecision = new HashMap<>();

        List<String> properties;

        properties = new ArrayList<>();
        properties.add("white");
        properties.add("labelled");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("labelled");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("labelled");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("paper");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("labelled");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("labelled");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, true);

        return propertyDecision;
    }

    private Map<List<String>, Boolean> prepareYellowAreaLearningSet() {
        //Should be light and paper - rest doesn't matter
        Map<List<String>, Boolean> propertyDecision = new HashMap<>();

        List<String> properties;

        properties = new ArrayList<>();
        properties.add("white");
        properties.add("paper");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("paper");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("paper");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("paper");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("paper");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, true);

        return propertyDecision;
    }

    private Map<List<String>, Boolean> prepareRedAreaLearningSet() {
        //Should be brown and wooden
        Map<List<String>, Boolean> propertyDecision = new HashMap<>();

        List<String> properties;

        properties = new ArrayList<>();
        properties.add("brown");
        properties.add("wooden");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("metal");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("metal");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("brown");
        properties.add("wooden");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("brown");
        properties.add("wooden");
        properties.add("light");
        properties.add("liquid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("metal");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, false);

        return propertyDecision;
    }

    private Map<List<String>, Boolean> prepareBlackAreaLearningSet() {
        //Should be heavy, solid and metal - color doesn't matter
        Map<List<String>, Boolean> propertyDecision = new HashMap<>();

        List<String> properties;

        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("yellow");
        properties.add("metal");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, true);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("light");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("metal");
        properties.add("heavy");
        properties.add("liquid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("gray");
        properties.add("wood");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, false);
        properties = new ArrayList<>();
        properties.add("red");
        properties.add("metal");
        properties.add("heavy");
        properties.add("solid");
        propertyDecision.put(properties, true);

        return propertyDecision;
    }

    private List<String> learnArea(Map<List<String>, Boolean> blackAreaLearningSet) {
        List<List<String>> generalHypothesis = new ArrayList<>();
        List<List<String>> specificHypothesis = new ArrayList<>();
        generalHypothesis.add(new ArrayList<>());
        specificHypothesis.add(new ArrayList<>());

        for (int i = 0; i < 4; i++) {
            generalHypothesis.get(0).add("?");
            specificHypothesis.get(0).add("0");
        }

        for (List<String> example : blackAreaLearningSet.keySet()) {
            parseExample(blackAreaLearningSet, example, generalHypothesis, specificHypothesis);
        }

//        System.out.println(generalHypothesis);
//        System.out.println(specificHypothesis);

        return specificHypothesis.get(0);
    }

    private void parseExample(Map<List<String>, Boolean> blackAreaLearningSet, List<String> example, List<List<String>> generalHypothesis, List<List<String>> specificHypothesis) {
        if (exampleIsPositive(example, blackAreaLearningSet)) {
            parsePositiveExample(blackAreaLearningSet, example, generalHypothesis, specificHypothesis);
        } else {
            parseNegativeExample(blackAreaLearningSet, example, generalHypothesis, specificHypothesis);
        }
    }

    private void parsePositiveExample(Map<List<String>, Boolean> blackAreaLearningSet, List<String> example, List<List<String>> generalHypothesis, List<List<String>> specificHypothesis) {
        for (List<String> hypothesis : generalHypothesis) {
            if (!hypothesisDoesCover(example, hypothesis)) {
                System.out.println(hypothesis + " doesn't cover " + example + " so it's deleted (general positive)");
                removeHypothesis(generalHypothesis, hypothesis);
            } else {
                System.out.println(hypothesis + " does cover " + example + " (general positive)");
            }
//            for (int i = 0; i < 4; i++) {
//                if (hypothesisDoesntCover(example, hypothesis, i)) {
//                    System.out.println(hypothesis + " doesn't cover " + example + " so it's deleted (general)");
//                    removeHypothesis(generalHypothesis, hypothesis);
//                    break;
//                } else {
//                    System.out.println(hypothesis + " does cover " + example + " (general)");
//                }
//            }
        }

        for (List<String> hypothesis : specificHypothesis) {
            if (!hypothesisDoesCover(example, hypothesis)) {
                System.out.println(hypothesis + " doesn't cover " + example + " so it's deleted (specific positive)");
                removeHypothesis(specificHypothesis, hypothesis);
                addMinimalGeneralizations(specificHypothesis, hypothesis, generalHypothesis, example);
                deleteMoreGeneralHypothesis(specificHypothesis, hypothesis);
            } else {
                System.out.println(hypothesis + " does cover " + example + " (specific positive)");
            }
//            for (int i = 0; i < 4; i++) {
//                if (hypothesisDoesntCover(example, hypothesis, i)) {
//            System.out.println(hypothesis + " doesn't cover " + example + " so it's deleted (specific)");
//            removeHypothesis(specificHypothesis, hypothesis);
//            addMinimalGeneralizations(specificHypothesis, hypothesis, generalHypothesis, example);
//                    break;
//                } else {
//            System.out.println(hypothesis + " does cover " + example + " (specificl)");
//                }
//            }
        }
    }

    private void removeHypothesis(List<List<String>> specificHypothesis, List<String> hypothesis) {
        specificHypothesis.remove(hypothesis);
    }

    private boolean hypothesisDoesCover(List<String> example, List<String> hypothesis) {
        for (int i = 0; i < 4; i++) {
            if (!hypothesis.get(i).equals(example.get(i)) && !hypothesis.get(i).equals("?"))
                return false;
        }
        return true;
    }

//    private boolean hypothesisDoesntCover(List<String> example, List<String> hypothesis, int i) {
//        return !hypothesis.get(i).equals(example.get(i)) && !hypothesis.get(i).equals("?");
//    }

    private void addMinimalGeneralizations(List<List<String>> specificHypothesis, List<String> hypothesis, List<List<String>> generalHypothesis, List<String> example) {
        List<List<String>> possibleMinimalGeneralizations;
        possibleMinimalGeneralizations = findPossibleMinimalGeneralizations(hypothesis, example);

        for (List<String> possibleGeneralization : possibleMinimalGeneralizations) {
            if (generalHasMoreGeneralHypothesis(possibleGeneralization, generalHypothesis)) {
                specificHypothesis.add(possibleGeneralization);
            }
        }
    }

    private List<List<String>> findPossibleMinimalGeneralizations(List<String> hypothesis, List<String> example) {
        List<List<String>> possibleGeneralizations = new ArrayList<>();

        List<String> current = new ArrayList<>();
        for (int i = 0; i < numberOfProperties; i++) {
            if (!hypothesis.get(i).equals(example.get(i)) && !hypothesis.get(i).equals("0"))
                current.add("?");
            else
                current.add(example.get(i));
        }

        possibleGeneralizations.add(current);

        return possibleGeneralizations;
    }

    private boolean generalHasMoreGeneralHypothesis(List<String> hypothesis, List<List<String>> generalHypothesis) {
        return true;
//        for (List<String> singleGeneralHypothesis : generalHypothesis) {
//            boolean has = true;
//            for (int i = 0; i < numberOfProperties; i++) {
//                if (singleGeneralHypothesis.get(i).equals(hypothesis.get(i)))
//                    has = false;
//            }
//            if (has) return true;
//        }
//        return false;
    }

    private void deleteMoreGeneralHypothesis(List<List<String>> specificHypothesis, List<String> hypothesis) {
    }

    private void parseNegativeExample(Map<List<String>, Boolean> blackAreaLearningSet, List<String> example, List<List<String>> generalHypothesis, List<List<String>> specificHypothesis) {
//        for (List<String> hypothesis : specificHypothesis) {
//                if (hypothesisDoesCover(example, hypothesis)) {
//                    System.out.println(hypothesis + " doesn't cover " + example + " so it's deleted (specific negative)");
//                    removeHypothesis(specificHypothesis, hypothesis);
//                    addMinimalGeneralizations(specificHypothesis, hypothesis, generalHypothesis, example);
//                } else {
//                    System.out.println(hypothesis + " does cover" + example + " (specific negative)");
//                }
//        }

//        Iterator<List<String>> generalHypothesisIterator = generalHypothesis.iterator();
//        while (generalHasMoreGeneralHypothesis())
//        for (List<String> hypothesis : generalHypothesis) {
//                if (hypothesisDoesCover(example, hypothesis)) {
//                    System.out.println(hypothesis + " doesn't cover " + example + " so it's deleted (general negative)");
//                    removeHypothesis(generalHypothesis, hypothesis);
//                    addMinimalSpecializations(generalHypothesis, hypothesis);
//                    deleteMoreSpecificHypothesis(generalHypothesis, hypothesis);
//                } else {
//                    System.out.println(hypothesis + " does cover " + example + " (general negative)");
//                }
//        }
    }

    private void addMinimalSpecializations(List<List<String>> generalHypothesis, List<String> hypothesis) {
    }

    private void deleteMoreSpecificHypothesis(List<List<String>> generalHypothesis, List<String> hypothesis) {

    }

    private boolean exampleIsPositive(List<String> example, Map<List<String>, Boolean> learningSet) {
        Boolean aBoolean = learningSet.get(example);
        return aBoolean;
    }

    @Override
    public int[] findDestinationPlace(KnowledgeBase knowledgeBase, String caseName, Map<String, List<int[]>> areasData) {
        //destinationPlace[0] is x and destinationPlace[1] is y

        List<String> properties = knowledgeBase.getKnowledgeBase().get(caseName);
     //   System.out.println("Current case properties: " + properties);

        int[] destinationPlace = new int[2];


        if (hypothesisDoesCover(properties, blackArea)) {
            destinationPlace = findNextPlaceInArea("black", areasData);
            System.out.println("Black");
        }
        if (hypothesisDoesCover(properties, blueArea)) {
            destinationPlace = findNextPlaceInArea("blue", areasData);
            System.out.println("Blue");
        }
        if (hypothesisDoesCover(properties, greenArea)) {
            destinationPlace = findNextPlaceInArea("green", areasData);
            System.out.println("Green");
        }
        if (hypothesisDoesCover(properties, yellowArea)) {
            destinationPlace = findNextPlaceInArea("yellow", areasData);
            System.out.println("Yellow");
        }
        if (hypothesisDoesCover(properties, brownArea)) {
            destinationPlace = findNextPlaceInArea("brown", areasData);
            System.out.println("Brown");
        }
        if (hypothesisDoesCover(properties, redArea)) {
            destinationPlace = findNextPlaceInArea("red", areasData);
            System.out.println("Red");
        }

        return destinationPlace;
    }

    private int[] findNextPlaceInArea(String area, Map<String, List<int[]>> areasData) {
        int[] result;
        List<int[]> areaPoints = areasData.get(area);
        result = choosePlaceRoundRobin(areaPoints);

        return result;
    }

    private int[] choosePlaceRoundRobin(List<int[]> areaPoints) {
        //popping element and pushing it to the end
        int[] result = areaPoints.remove(0);
        areaPoints.add(result);
        return result;
    }
}
