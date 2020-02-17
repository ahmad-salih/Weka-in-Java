package SVM;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.lazy.LWL;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.*;
import weka.classifiers.trees.m5.M5Base;
import weka.core.Instances;

public class Classifier {

  private final Instances train;
  private final Instances validation;
  private Instances attack;
  private int i;
  private int j;

  public Classifier(Instances train, Instances validation, Instances attack, int i, int j) {
    this.train = train;
    this.validation = validation;
    this.attack = attack;
    this.i = i;
    this.j = j;
  }

  public Classifier(Instances train, Instances validation, Instances attack) {
    this.train = train;
    this.validation = validation;
    this.attack = attack;
  }

  public Classifier(Instances train, Instances validation) {
    this.train = train;
    this.validation = validation;
  }


    public String evaluateSVM() throws Exception {
    print();
    System.out.println("Running SVM");

    SMO classifier = new SMO();
    classifier.buildClassifier(train);
    classifier.setCalibrator(new LinearRegression());
    return cl(classifier, train.numInstances(), "SVM");
  }

  private void print() {
    System.out.print(i + "  " + j + "--");
  }

  public String evaluateDecisionStump() throws Exception {
    print();
    System.out.println("Running Decision Stump");

    DecisionStump classifier = new DecisionStump();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Decision Stump");
  }

  public String evaluateHoeffdingTree() throws Exception {
    print();
    System.out.println("Running Hoeffding Tree");

    HoeffdingTree classifier = new HoeffdingTree();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Hoeffding Tree");
  }

  public String evaluateDecisionTable() throws Exception {
    print();
    System.out.println("Running Decision Table");

    DecisionTable classifier = new DecisionTable();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Decision Table");
  }

  public String evaluateZeroR() throws Exception {
    print();
    System.out.println("Running ZeroR");

    ZeroR classifier = new ZeroR();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "ZeroR");
  }

  public String evaluateLMT() throws Exception {
    print();
    System.out.println("Running LMT");

    LMT classifier = new LMT();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "LMT");
  }

  public String evaluateRandomForest() throws Exception {
    print();
    System.out.println("Running Random Forest");

    RandomForest classifier = new RandomForest();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Random Forest");
  }

  public String evaluateRandomTree() throws Exception {
    print();
    System.out.println("Running Random Tree");

    RandomTree classifier = new RandomTree();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Random Tree");
  }

  public String evaluateBayesNet() throws Exception {
    print();
    System.out.println("Running Bayes Net");

    BayesNet classifier = new BayesNet();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Bayes Net");
  }

  public String evaluateJ48() throws Exception {
    print();
    System.out.println("Running J48");

    J48 classifier = new J48();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "J48");
  }

  public String evaluateIBk() throws Exception {
    print();
    System.out.println("Running IBk");

    IBk classifier = new IBk();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "IBk");
  }

  public String evaluateKStar() throws Exception {
    print();
    System.out.println("Running KStar");

    KStar classifier = new KStar();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "KStar");
  }

  public String evaluateLWL() throws Exception {
    print();
    System.out.println("Running LWL");

    LWL classifier = new LWL();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "LWL");
  }

  public String evaluateLinearRegression() throws Exception {
    print();
    System.out.println("Running Linear Regression");

    LinearRegression classifier = new LinearRegression();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Linear Regression");
  }

  public String evaluateNaiveBayes() throws Exception {
    print();
    System.out.println("Running Naive Bayes");

    NaiveBayes classifier = new NaiveBayes();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "Naive Bayes");
  }

  public String evaluateOneR() throws Exception {
    print();
    System.out.println("Running OneR");

    OneR classifier = new OneR();
    classifier.buildClassifier(train);
    return cl(classifier, train.numInstances(), "OneR");
  }

  private String cl(AbstractClassifier abstractClassifier, int trainNumber, String mLName)
      throws Exception {
    StringBuilder stringBuilder = new StringBuilder();

    Evaluation eval1 = new Evaluation(validation);
    eval1.evaluateModel(abstractClassifier, validation);

    Evaluation eval2 = new Evaluation(attack);
    eval2.evaluateModel(abstractClassifier, attack);

    prepareOutput(eval1, stringBuilder, trainNumber, validation.numInstances(), "Normal", mLName);
    prepareOutput(eval2, stringBuilder, trainNumber, attack.numInstances(), "Attack", mLName);

    return stringBuilder.toString();
  }

  private void prepareOutput(
      Evaluation eval,
      StringBuilder stringBuilder,
      int trainNumber,
      int number,
      String type,
      String name) {
    stringBuilder
        .append(name)
        .append(',')
        .append(type)
        .append(',')
        .append(trainNumber)
        .append(',')
        .append(number)
        .append(',')
        .append(eval.pctCorrect())
        .append(',')
        .append(eval.pctIncorrect())
        .append(',')
        .append(eval.weightedTruePositiveRate())
        .append(',')
        .append(eval.weightedFalsePositiveRate())
        .append(',')
        .append(eval.weightedTrueNegativeRate())
        .append(',')
        .append(eval.weightedFalseNegativeRate())
        .append(',')
        .append(eval.weightedPrecision())
        .append(',')
        .append(eval.weightedRecall())
        .append(',')
        .append(eval.weightedFMeasure())
        .append('\n');
  }
}
