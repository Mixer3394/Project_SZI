package sample.treeDecision;
/**
 * Created by Kamil on 2016-06-04.
 */
import java.util.ArrayList;
import java.util.List;

public class DecisionTreeMain {

	private final static double PERCENT = 0.1;

	private final static double MINACCURACY = 0.6;
	

	private static List<Element> trainingSet = new ArrayList<Element>();
	private static List<Element> testSet  = new ArrayList<Element>();
	
	public static void main(String[] args) {
		
		// Okno
		Gui gui = new Gui();
		
		double accuracy = 0;	// Dokładność zestaw testowy
		do {
			// Czytanie wszystkich wierszy danych
			FileParser filePaser = new FileParser(System.getProperty("user.dir")+"\\src\\sample\\treeDecision\\Data.txt");
			List<Element> elementList = filePaser.getElementList();
			
			// Dzielenie zestawu na trening i test
			assignElements(elementList);
			
			// Okno danych
			gui.setAttribute(filePaser.getAttributes());
			gui.setTrainingSet(trainingSet);
			gui.setTestSet(testSet);
			
			// Drzewo decyzyjne przy pomocy zestawu szkoleniowy
			DecisionTree decisionTree = new DecisionTree(trainingSet,
														filePaser.getAttributes(), 
														filePaser.getNumberOfOutput());
			
			// Rysuj drzewo
			gui.setTree(decisionTree);
			
			// Testowanie poprawności zestawu testowego
			accuracy = decisionTree.calculateAccuracy(testSet);
			//System.out.println(decisionTree.newDecision(testSet));
			String color = "gray";
			String weight = "heavy";
			String kindOfMaterial = "metal";
			String stateOfAgregation = "solid";
			newElement elementSet = new newElement(color,weight,kindOfMaterial,stateOfAgregation);

			System.out.println("Wczytuję paczkę: "+color+","+weight+","+kindOfMaterial+","+stateOfAgregation);

			String result = decisionTree.newDecision(elementSet);
			System.out.println("Proponowany sektor: " + result);


		} while (accuracy < MINACCURACY);

		gui.setAccuracy(accuracy * 100);

	}
	

	private static void assignElements(List<Element> elementList) {

		//System.out.println(elementList.size());
		int numOfTest = (int)(elementList.size() * PERCENT);
		
		// Informacje podróżuje losowo wybrany zestaw testowy
		for (int i = 0; i < numOfTest; i++) {
			int randomNum = (int)(Math.random() * (elementList.size() - 1));
			testSet.add(elementList.get(randomNum));
			elementList.remove(randomNum);
		}
		
		// Pozostałe dane dla zbioru uczącego
		trainingSet = elementList;
	}
}