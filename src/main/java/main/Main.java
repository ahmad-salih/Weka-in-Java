package main;

import classifier.abstractClasses.MyClassifier;
import classifier.myClassifier.MyRandomTress;
import importFile.ImportFile;
import weka.core.Instances;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  // Algorithm, Attacks in Train, Number of test, Type, TP, FP, TN, FN, Precision, Recall,
  // F-Measure\n
  public static void main(String[] args) throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter("NGram/0/output.csv", true));
    // writer.write("Algorithm, Attacks in Train, Number of test, Type, Correct, Incorrect,
    // Precision, Recall, F-Measure\n");
    writer.flush();
    int t = 1000;

    //StringBuilder stringBuilder = new StringBuilder();

Runnable runnable1 =
        () -> {
            Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
            Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
            Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          StringBuilder stringBuilder = new StringBuilder();
          for (int i = 50; i < 550; i += 50) {
            MyClassifier classifier = new MyRandomTress(train, validation, attack, t, i);
            try {
              classifier.evaluate();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          try {
            writer.write(stringBuilder.toString());
            writer.flush();
            System.out.println("Thread Finished");
          } catch (IOException e) {
            e.printStackTrace();
          }
        };
/*
    Runnable runnable1 =
        () -> {
            Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
            Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
            Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          StringBuilder stringBuilder = new StringBuilder();
          for (int i = 50; i < 550; i += 50) {
            Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
            try {
              classifier.evaluateSVM();
            } catch (Exception e) {
              e.printStackTrace();
            }
              try {
                stringBuilder.append(classifier.print("SVM"));
              } catch (Exception e) {
                e.printStackTrace();
              }

          }
          try {
            writer.write(stringBuilder.toString());
            writer.flush();
            System.out.println("Thread Finished");
          } catch (IOException e) {
            e.printStackTrace();
          }
        };*/

    /*Runnable runnable2 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          StringBuilder stringBuilder = new StringBuilder();

          for (int i = 50; i < 150; i += 50) {
            Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
            try {
              classifier.evaluateRandomForest();
            } catch (Exception e) {
              e.printStackTrace();
            }

            try {
                synchronized (stringBuilder) {
                    stringBuilder.append(classifier.print("Random Forest"));
                }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

          try {
            writer.write(stringBuilder.toString());
            writer.flush();
            System.out.println("Thread Finished");

          } catch (IOException e) {
            e.printStackTrace();
          }
        };

      Runnable runnable3 =
              () -> {
                  Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
                  Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
                  Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
                  StringBuilder stringBuilder = new StringBuilder();

                  for (int i = 150; i < 250; i += 50) {
                      Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
                      try {
                          classifier.evaluateRandomForest();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                      try {
                          synchronized (stringBuilder) {
                              stringBuilder.append(classifier.print("Random Forest"));
                          }
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }

                  try {
                      writer.write(stringBuilder.toString());
                      writer.flush();
                      System.out.println("Thread Finished");

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              };

      Runnable runnable4 =
              () -> {
                  Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
                  Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
                  Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
                  StringBuilder stringBuilder = new StringBuilder();

                  for (int i = 250; i < 350; i += 50) {
                      Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
                      try {
                          classifier.evaluateRandomForest();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                      try {
                          synchronized (stringBuilder) {
                              stringBuilder.append(classifier.print("Random Forest"));
                          }                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }

                  try {
                      writer.write(stringBuilder.toString());
                      writer.flush();
                      System.out.println("Thread Finished");

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              };


      Runnable runnable5 =
              () -> {
                  Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
                  Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
                  Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
                  StringBuilder stringBuilder = new StringBuilder();

                  for (int i = 350; i < 450; i += 50) {
                      Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
                      try {
                          classifier.evaluateRandomForest();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                      try {
                          synchronized (stringBuilder) {
                              stringBuilder.append(classifier.print("Random Forest"));
                          }                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }

                  try {
                      writer.write(stringBuilder.toString());
                      writer.flush();
                      System.out.println("Thread Finished");

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              };

    Runnable runnable6 =
        () -> {
          Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
          Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
          Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
          StringBuilder stringBuilder = new StringBuilder();

          for (int i = 450; i < 550; i += 50) {
            Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
            try {
              classifier.evaluateRandomForest();
            } catch (Exception e) {
              e.printStackTrace();
            }

            try {
              synchronized (stringBuilder) {
                stringBuilder.append(classifier.print("Random Forest"));
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

          try {
            writer.write(stringBuilder.toString());
            writer.flush();
            System.out.println("Thread Finished");

          } catch (IOException e) {
            e.printStackTrace();
          }
        };*/
    /*BufferedWriter writer1 = new BufferedWriter(new FileWriter("NGram/0/output1.csv",true));
    //writer1.write("Algorithm, Attacks in Train, Number of test, Type, Correct, Incorrect, TP, FP, TN, FN, Precision, Recall, F-Measure\n");
    writer1.flush();
    Runnable evaluate =
        () -> {
            Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
            Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
            Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();

            Classifier1 classifier = new Classifier1(attack, validation, attack,50,100);

          StringBuilder stringBuilder = new StringBuilder();

            try {
                stringBuilder
                    .append(classifier.evaluateModel("Random Forest", 50, 500))
                    ;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
            writer1.write(stringBuilder.toString());
            writer1.flush();
          } catch (IOException e) {
            e.printStackTrace();
          }
          System.out.println("Evaluate Thread Finished");
        };*/

    /*Runnable runnable3 =
            () -> {
                Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
                Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
                Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
              StringBuilder stringBuilder = new StringBuilder();

              for (int i = 50; i < 550; i += 50) {
                Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
                try {
                  classifier.evaluateLWL();
                } catch (Exception e) {
                  e.printStackTrace();
                }

                  try {
                    stringBuilder.append(classifier.print("LWL"));
                  } catch (Exception e) {
                    e.printStackTrace();
                  }

              }
              try {
                writer.write(stringBuilder.toString());
                writer.flush();
                System.out.println("Thread Finished");

              } catch (IOException e) {
                e.printStackTrace();
              }
            };

    Runnable runnable4 =
            () -> {
                Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
                Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
                Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
              StringBuilder stringBuilder = new StringBuilder();

              for (int i = 50; i < 550; i += 50) {
                Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
                try {
                  classifier.evaluateIBk();
                } catch (Exception e) {
                  e.printStackTrace();
                }

                  try {
                    stringBuilder.append(classifier.print("IBk"));
                  } catch (Exception e) {
                    e.printStackTrace();
                  }

              }
              try {
                writer.write(stringBuilder.toString());
                writer.flush();
                System.out.println("Thread Finished");

              } catch (IOException e) {
                e.printStackTrace();
              }
            };

      Runnable runnable5 =
              () -> {
                  Instances train = new ImportFile("NGram/0/Train1.arff").getDataset();
                  Instances validation = new ImportFile("NGram/0/Validation1.arff").getDataset();
                  Instances attack = new ImportFile("NGram/0/Attack1.arff").getDataset();
                  StringBuilder stringBuilder = new StringBuilder();

                  for (int i = 50; i < 550; i += 50) {
                      Classifier1 classifier = new Classifier1(train, validation, attack, t, i);
                      try {
                          classifier.evaluateRandomForest();
                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                          try {
                              stringBuilder.append(classifier.print("Random Forest"));
                          } catch (Exception e) {
                              e.printStackTrace();
                          }

                  }
                  try {
                      writer.write(stringBuilder.toString());
                      writer.flush();
                      System.out.println("Thread Finished");

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              };*/

    /*  new Thread(runnable1).start();*/
/*    new Thread(runnable2).start();
    new Thread(runnable3).start();
    new Thread(runnable4).start();
    new Thread(runnable5).start();
      new Thread(runnable6).start();*/
      new Thread(runnable1).start();

  }
}
