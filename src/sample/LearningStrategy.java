package sample;

import java.util.List;
import java.util.Map;

/**
 * Created by Rafał on 2016-05-15.
 */
public interface LearningStrategy {
    int[] findDestinationPlace(List<String> properties, Map<String, List<int[]>> areasData);

    int[] findDestinationPlace(KnowledgeBase knowledgeBase, String caseName, Map<String, List<int[]>> areasData);
}
