package main;

import classifier.abstractClasses.MyClassifier;
import classifier.myClassifier.MyRandomTress;
import importFile.ImportFile;
import weka.core.Instances;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws Exception {

    int t = 1000;

    // StringBuilder stringBuilder = new StringBuilder();

    Runnable runnable1 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          StringBuilder stringBuilder = new StringBuilder();
          for (int i = 50; i < 550; i += 50) {
            MyClassifier classifier = null;
            try {
              classifier =
                  new MyRandomTress(train, validation, attack, t, i, "NGram/0/output2.csv");
            } catch (IOException e) {
              e.printStackTrace();
            }
            try {
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
