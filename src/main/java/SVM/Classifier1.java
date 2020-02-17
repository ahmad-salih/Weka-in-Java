package SVM;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.lazy.LWL;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.trees.*;
import weka.core.Instance;
import weka.core.Instances;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Classifier1 {

  private final Instances train;
  private final Instances validation;
  private final Instances attack;

  private final int attacksInTrain;
  private final int trainNumbers;

  public Classifier1(
      Instances train,
      Instances validation,
      Instances attack,
      int trainNumbers,
      int attacksInTrain) {
    this.train = train;
    this.validation = validation;
    this.attack = attack;
    this.trainNumbers = trainNumbers;
    this.attacksInTrain = attacksInTrain;
  }

  public void evaluateSVM() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      SMO classifier = new SMO();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "SVM");
  }

  public void evaluateDecisionStump() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      DecisionStump classifier = new DecisionStump();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Decision Stump");
  }

  public void evaluateHoeffdingTree() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      HoeffdingTree classifier = new HoeffdingTree();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Hoeffding Tree");
  }

  public void evaluateDecisionTable() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      DecisionTable classifier = new DecisionTable();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Decision Table");
  }

  public void evaluateLMT() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      LMT classifier = new LMT();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "LMT");
  }

  public void evaluateRandomForest() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      RandomForest classifier = new RandomForest();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Random Forest");
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

  public void evaluateRandomTree() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      RandomTree classifier = new RandomTree();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Random Tree");
  }

  public void evaluateBayesNet() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      BayesNet classifier = new BayesNet();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Bayes Net");
  }

  public void evaluateJ48() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      J48 classifier = new J48();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "J48");
  }

  public void evaluateIBk() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      IBk classifier = new IBk();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "IBk");
  }

  public void evaluateKStar() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      KStar classifier = new KStar();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "KStar");
  }

  public void evaluateLWL() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      LWL classifier = new LWL();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "LWL");
  }

  public void evaluateNaiveBayes() throws Exception {
    double max = 0;
    AbstractClassifier abstractClassifier = null;
    for (int z = 0; z < trainNumbers; z++) {
      NaiveBayes classifier = new NaiveBayes();

      Instances[] all = getInstances();

      classifier.buildClassifier(all[0]);

      Instances validationSet = addToValidation(all[1]);

      Evaluation eval = new Evaluation(validationSet);
      eval.evaluateModel(classifier, validationSet);

      double temp = (eval.weightedTrueNegativeRate() + eval.weightedTruePositiveRate()) / 2;
      if (temp > max) {
        abstractClassifier = classifier;
        max = temp;
        if (temp == 1.0) {
          break;
        }
      }
    }

    saveModel(abstractClassifier, "Naive Bayes");
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
    weka.core.SerializationHelper.write("Best Trained/" + name + attacksInTrain +".model", abstractClassifier);
  }

  private void prepareOutput(
      Evaluation eval, StringBuilder stringBuilder, int number, String type, String name) {
    stringBuilder
        .append(name)
        .append(',')
        .append(attacksInTrain)
        .append(',')
        .append(number)
        .append(',')
        .append(type)
        .append(',')
        .append(eval.weightedTruePositiveRate())
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

  public String print(String name) throws Exception {

    AbstractClassifier abstractClassifier = loadModel(name);

    StringBuilder stringBuilder = new StringBuilder();

    Evaluation eval = new Evaluation(validation);
    eval.evaluateModel(abstractClassifier, validation);

    prepareOutput(eval, stringBuilder, validation.numInstances(), "Normal", name);

    Evaluation eval1 = new Evaluation(attack);
    eval1.evaluateModel(abstractClassifier, attack);

    prepareOutput(eval1, stringBuilder, attack.numInstances(), "Attack", name);

    return stringBuilder.toString();
  }

  private AbstractClassifier loadModel(String name) throws Exception {
    return (AbstractClassifier)
        weka.core.SerializationHelper.read("Best Trained/" + name + attacksInTrain + ".model");
  }

  public String evaluateModel(String name, int start, int end) throws Exception {
    StringBuilder stringBuilder = new StringBuilder();
    Instances instances = new Instances(validation);
    instances.addAll(attack);

    for (int i = start; i<=end; i+=50){
      AbstractClassifier abstractClassifier = (AbstractClassifier)
              weka.core.SerializationHelper.read("Best Trained/" + name + i + ".model");

      Evaluation eval = new Evaluation(instances);
      eval.evaluateModel(abstractClassifier, instances);

      prepareEvaluateModelOutput(eval, stringBuilder, i, instances.numInstances(), "Attack and Normal", name);
    }
    return stringBuilder.toString();
  }

  private void prepareEvaluateModelOutput(
          Evaluation eval, StringBuilder stringBuilder, int total, int number, String type, String name) {
    stringBuilder
            .append(name)
            .append(',')
            .append(total)
            .append(',')
            .append(number)
            .append(',')
            .append(type)
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
