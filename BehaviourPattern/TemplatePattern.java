package BehaviourPattern;
public class TemplatePattern {
    public static void main(String[] args) {
        System.out.println("Neural Network Model");
        ModelTrainer neuTrainer = new NeuralNetworkTrainer();
        neuTrainer.trainPipeLine("/data/images/");
        System.out.println("Decision Tree Model");
        ModelTrainer decTrainer = new DecisionTreeTrainer();
        decTrainer.trainPipeLine("/data/movies.csv");
 
    }
}

abstract class ModelTrainer {
    void trainPipeLine(String dataPath) {
        loadData(dataPath);
        preProcessingData();
        trainModel();
        evaluateModel();
        saveModel();
    }

    void loadData(String dataPath) {
        System.out.println("Loading DataSet From " + dataPath);
    }

    void preProcessingData() {
        System.out.println("Splitting into Train/test");
    }

    void saveModel() {
        System.out.println("Default Save");
    }

    abstract void trainModel();

    abstract void evaluateModel();

}

class NeuralNetworkTrainer extends ModelTrainer {

    @Override
    void trainModel() {
        System.out.println("Neural Network Train Model");
    }

    @Override
    void evaluateModel() {
        System.out.println("Neural Network Evaluate Model");
    }

    @Override
    void saveModel() {
        System.out.println("Neural Network save Model");
    }

}

class DecisionTreeTrainer extends ModelTrainer {

    @Override
    void trainModel() {
        System.out.println("DecisionTreeTrainer  Train Model");
    }

    @Override
    void evaluateModel() {
        System.out.println("DecisionTreeTrainer Evaluate Model");
    }
}