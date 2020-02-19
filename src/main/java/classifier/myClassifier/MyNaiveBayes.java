package classifier.myClassifier;

import classifier.abstractClasses.MyClassifier;
import weka.core.Instances;

import java.io.IOException;

public class MyNaiveBayes extends MyClassifier {

  public MyNaiveBayes(
      Instances train,
      Instances validation,
      Instances attack,
      int trainNumbers,
      int attacksInTrain,
      String output)
      throws IOException {
    super(train, validation, attack, trainNumbers, attacksInTrain, output);
  }

  @Override
  public void evaluate() throws Exception {
    getResultClassifier("weka.classifiers.lazy.LWL");
  }
}
