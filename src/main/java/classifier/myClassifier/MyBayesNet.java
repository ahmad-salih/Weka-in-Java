package classifier.myClassifier;

import classifier.abstractClasses.MyClassifier;
import weka.core.Instances;

public class MyBayesNet extends MyClassifier {
    public MyBayesNet(Instances train, Instances validation, Instances attack, int trainNumbers, int attacksInTrain) {
        super(train, validation, attack, trainNumbers, attacksInTrain);
    }

    @Override
    public void evaluate() throws Exception {
        getResultClassifier("weka.classifiers.bayes.NaiveBayes");
    }
}
