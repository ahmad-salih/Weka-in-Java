package classifier.myClassifier;

import classifier.abstractClasses.MyClassifier;
import weka.core.Instances;

import java.io.IOException;

public class MyLMT extends MyClassifier {

  public MyLMT(
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
    getResultClassifier("weka.classifiers.trees.LMT");
  }
}
