package main;

import classifier.abstractClasses.MyClassifier;
import classifier.myClassifier.MyJ48;
import classifier.myClassifier.MyRandomForest;
import classifier.myClassifier.MyRandomTress;
import classifier.myClassifier.MySVM;
import importFile.ImportFile;
import weka.core.Instances;

public class Main {

  public static void main(String[] args) throws Exception {

    int t = 1000;

    Runnable runnable1 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          for (int i = 50; i < 550; i += 50) {
            MyClassifier classifier = null;
            try {
              classifier = new MyRandomTress(train, validation, attack, t, i, "NGram/0/output.csv");
              classifier.evaluate();

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          System.out.println("Thread Finished");
        };
    new Thread(runnable1).start();

    Runnable runnable2 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          for (int i = 50; i < 550; i += 50) {
            MyClassifier classifier = null;
            try {
              classifier =
                  new MyRandomForest(train, validation, attack, t, i, "NGram/0/output.csv");
              classifier.evaluate();

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          System.out.println("Thread Finished");
        };
    new Thread(runnable1).start();

    Runnable runnable3 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          for (int i = 50; i < 550; i += 50) {
            MyClassifier classifier = null;
            try {
              classifier = new MySVM(train, validation, attack, t, i, "NGram/0/output.csv");
              classifier.evaluate();

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          System.out.println("Thread Finished");
        };
    new Thread(runnable1).start();

    Runnable runnable4 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          for (int i = 50; i < 550; i += 50) {
            MyClassifier classifier = null;
            try {
              classifier = new MyJ48(train, validation, attack, t, i, "NGram/0/output.csv");
              classifier.evaluate();

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          System.out.println("Thread Finished");
        };
    new Thread(runnable1).start();
  }
}
