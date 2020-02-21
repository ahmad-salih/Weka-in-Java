package main;

import DetectionSystem.LoadModels;
import DetectionSystem.Models;
import importFile.ImportFile;
import weka.classifiers.AbstractClassifier;
import weka.core.Instances;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.TreeMap;

public class Detection {
  public static void main(String[] args) throws Exception {

    Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
    Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
    Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();

    TreeMap<Integer, AbstractClassifier> allClassifiers = new TreeMap<>();

    String path = "Detection models/";
    String[] classifier = {"J48 + Random Forest + Random Tree",
            "J48 + Random Forest",
            "J48 + Random Tree",
            "Random Forest",
            "J48 + Random Forest + SVM",
            "J48",
            "J48 + SVM",
            "Random Forest + SVM",
            "SVM",
            "J48 + Random Tree + SVM",
            "Random Forest + Random Tree",
            "Random Tree + SVM",
            "Random Forest + Random Tree + SVM",
            "Random Tree",
            "J48 + Random Forest + Random Tree + SVM"};

    validation.addAll(attack);

    BufferedWriter writer = new BufferedWriter(new FileWriter(path + "out1.csv"));
    writer.write("Algorithms, percent of models, TP, FN, TN, FP, Precision, Recall, F-Measure, Accuracy\n");
    writer.flush();
    for (String c : classifier) {
      LoadModels loadModels = new LoadModels().setPath(path+c);
      loadModels.loadModels();

      for (double d = 0.1; d < 1.0; d += .1) {
        loadModels.evaluateDataset(train, validation, d);


        writer.write(c + ", " + d + ", " + loadModels.getResaults());
        writer.flush();
      }
    }


  }

  private static void all(File file) {
    for (File f: file.listFiles()){
      System.out.println(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("/")+1));
    }
  }
}
