package classifier.myClassifier;

import classifier.abstractClasses.MyClassifier;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.trees.DecisionStump;
import weka.core.Instances;

public class MyDecisionStump extends MyClassifier {

  public MyDecisionStump(
      Instances train,
      Instances validation,
      Instances attack,
      int trainNumbers,
      int attacksInTrain) {
    super(train, validation, attack, trainNumbers, attacksInTrain);
  }

  @Override
  public void evaluate() throws Exception {
    getResultClassifier("weka.classifiers.trees.DecisionStump");
  }


}
