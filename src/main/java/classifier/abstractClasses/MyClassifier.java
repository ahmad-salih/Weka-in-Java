package classifier.abstractClasses;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class MyClassifier {
  private static BufferedWriter writer = null;
  private final Instances train;
  private final Instances validation;
  private final Instances attack;
  private final int attacksInTrain;
  private final int trainNumbers;
  private Instances bestValue;
  private double max;
  private double current;

  public MyClassifier(
      Instances train,
      Instances validation,
      Instances attack,
      int trainNumbers,
      int attacksInTrain,
      String output)
      throws IOException {
    this.train = train;
    this.validation = validation;
    this.attack = attack;
    this.trainNumbers = trainNumbers;
    this.attacksInTrain = attacksInTrain;

    if (writer == null) {
      writer = new BufferedWriter(new FileWriter(output));

      writer.write(
          "Algorithm, Attacks in Train, TP, FP, TN, FN, Precision, Recall, F-Measure, Accuracy\n");
      writer.flush();
    }

    this.max = 0;
    this.current = -1;
  }

  protected Instances getTrain() {
    return train;
  }

  protected Instances getValidation() {
    return validation;
  }

  protected Instances getAttack() {
    return attack;
  }

  protected int getAttacksInTrain() {
    return attacksInTrain;
  }

  protected int getTrainNumbers() {
    return trainNumbers;
  }

  private int[] getRandoms(int number, int size) {
    Set<Integer> randoms = new HashSet<>();
    SecureRandom secureRandom = new SecureRandom();
    while (randoms.size() < number) {
      randoms.add(secureRandom.nextInt(size));
    }

    int[] array = new int[randoms.size()];

    int k = 0;
    for (Integer i : randoms) {
      array[k++] = i;
    }
    Arrays.sort(array);
    return array;
  }

  private Instances[] getInstances() {

    int[] randoms = getRandoms(attacksInTrain, attack.size());
    Instances trainSet = getRandomiseTrain();
    Instances attackSet = getRandomiseAttack();

    merge(trainSet, attackSet, randoms);

    Instances[] instances = new Instances[2];
    instances[0] = trainSet;
    instances[1] = attackSet;

    for (int x = attacksInTrain - 1; x > -1; x--) {
      Instance temp = instances[1].get(x);
      instances[0].add(temp);
      instances[1].remove(x);
    }

    return instances;
  }

  private Instances getRandomiseTrain() {
    train.randomize(new SecureRandom());
    return new Instances(train);
  }

  private Instances getRandomiseAttack() {
    attack.randomize(new SecureRandom());

    return new Instances(attack);
  }

  private void merge(Instances trainSet, Instances attackSet, int[] randoms) {
    for (int x = randoms.length - 1; x > -1; x--) {
      Instance temp = attackSet.get(x);
      trainSet.add(temp);
      attackSet.remove(x);
    }
  }

  private Instances addToValidation(Instances instances) {
    Instances instances1 = new Instances(validation);
    instances1.addAll(instances);
    return instances1;
  }

  private void saveModel(AbstractClassifier abstractClassifier, String name) throws Exception {
    weka.core.SerializationHelper.write(
        "Best Trained/" + name + attacksInTrain + ".model", abstractClassifier);
  }

  private Instances getResult(AbstractClassifier classifier) throws Exception {

    Instances[] all = getInstances();

    classifier.buildClassifier(all[0]);

    Instances validationSet = addToValidation(all[1]);

    Evaluation eval = new Evaluation(validationSet);
    eval.evaluateModel(classifier, validationSet);

    double TP = eval.weightedTruePositiveRate();
    double TN = eval.weightedTrueNegativeRate();
    double FP = eval.weightedFalsePositiveRate();
    double FN = eval.weightedFalseNegativeRate();

    current = (TP + TN) / (TP + TN + FP + FN);
    //current = (TP + TN) / 2;
    return validationSet;
  }

  protected void getResultClassifier(String name) throws Exception {

    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < getTrainNumbers(); z++) {
      Class<?> clazz = Class.forName(name);
      Constructor<?> ctor = clazz.getConstructor();
      AbstractClassifier classifier = (AbstractClassifier) ctor.newInstance();
      Instances instances = getResult(classifier);
      if (current > max) {
        abstractClassifier = classifier;
        bestValue = instances;
        max = current;
        if (current == 1.0) {
          break;
        }
      }
    }
    saveModel(abstractClassifier, name.substring(name.lastIndexOf('.') + 1));
    String out = evaluateModel(abstractClassifier, name.substring(name.lastIndexOf('.') + 1));
    writer.write(out);
    writer.flush();
  }

  protected String evaluateModel(AbstractClassifier abstractClassifier, String name)
      throws Exception {
    StringBuilder stringBuilder = new StringBuilder();

    Evaluation eval = new Evaluation(bestValue);
    eval.evaluateModel(abstractClassifier, bestValue);

    prepareEvaluateModelOutput(eval, stringBuilder, name);

    return stringBuilder.toString();
  }

  private void prepareEvaluateModelOutput(
      Evaluation eval, StringBuilder stringBuilder, String name) {

    double TP = eval.weightedTruePositiveRate();
    double TN = eval.weightedTrueNegativeRate();
    double FP = eval.weightedFalsePositiveRate();
    double FN = eval.weightedFalseNegativeRate();

    stringBuilder
        .append(name)
        .append(',')
        .append(attacksInTrain)
        .append(',')
        .append(bestValue.numInstances())
        .append(',')
        .append(TP)
        .append(',')
        .append(FP)
        .append(',')
        .append(TN)
        .append(',')
        .append(FN)
        .append(',')
        .append(eval.weightedPrecision())
        .append(',')
        .append(eval.weightedRecall())
        .append(',')
        .append(eval.weightedFMeasure())
        .append(',')
        .append((TP + TN) / (TP + TN + FP + FN))
        .append('\n');
  }

  public abstract void evaluate() throws Exception;
}
