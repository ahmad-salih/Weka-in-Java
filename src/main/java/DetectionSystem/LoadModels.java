package DetectionSystem;

import weka.classifiers.AbstractClassifier;
import weka.core.Instances;

import java.io.File;

public class LoadModels {
  String path;
  String classifier;
  Models models;

  public LoadModels() {
    models = new Models();
  }

  public String getPath() {
    return path;
  }

  public LoadModels setPath(String path) {
    this.path = path;
    return this;
  }

  public String getClassifier() {
    return classifier;
  }

  public LoadModels setClassifier(String classifier) {
    this.classifier = classifier;
    return this;
  }

  public void loadModels() throws Exception {
    loadModels(new File(path));
  }

  private void loadModels(File folder) throws Exception {
    for (File file : folder.listFiles()) {
      if (file.isDirectory()) {
        loadModels(file);
      } else {
        String name = file.getAbsolutePath();
        String fileName = name.substring(name.lastIndexOf("/")+1);
        if (fileName.contains("model")) {
          addModel(name);
        }
      }
    }
  }

  private void addModel(String name) throws Exception {
    AbstractClassifier temp = (AbstractClassifier) weka.core.SerializationHelper.read(name);
    models.addModel(temp);
  }

  public void evaluateDataset(Instances validation, double d) throws Exception {
    models.evaluateDataset(validation, d);
  }

  public String getResaults() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
        .append(models.TPRate())
        .append(", ")
        .append(models.FNRate())
        .append(", ")
        .append(models.TNRate())
        .append(", ")
        .append(models.FPRate())
        .append(", ")
        .append(models.precision())
        .append(", ")
        .append(models.recall())
        .append(", ")
        .append(models.fMeasure())
        .append(", ")
        .append(models.accuracy())
        .append("\n");
    return stringBuilder.toString();
  }
}
