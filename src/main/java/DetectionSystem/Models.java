package DetectionSystem;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

import java.util.TreeMap;

public class Models {
    private final TreeMap<Integer, AbstractClassifier> allClassifiers;
    private int total;
    private double TP;
    private double TN;
    private double FN;
    private double FP;

    public Models(){
        allClassifiers = new TreeMap<>();
        total = 0;
        TP = 0;
        TN = 0;
        FN = 0;
        FP = 0;
    }


    public void addModel(AbstractClassifier abstractClassifier){
        allClassifiers.put(total++, abstractClassifier);
    }

    public double[] evaluateDataset(Instances train, Instances instances, double percent) throws Exception {
        TreeMap<Integer, double[]> predictions = new TreeMap<>();

        for (Integer i : allClassifiers.keySet()) {
            AbstractClassifier tempClassifier = allClassifiers.get(i);
            Evaluation eval = new Evaluation(train);
            double[] out = eval.evaluateModel(tempClassifier, instances);
            predictions.put(i, out);
        }

        double[] pre = new double[instances.size()];

        for (int i: predictions.keySet()){
            int j = 0;
            for (double d: predictions.get(i)){
                pre[j] += d;
                j++;
            }
        }

        double[] finalPrediction = new double[pre.length];

        int index = 0;
        for (double d: pre){
            finalPrediction[index++] = (d>total*percent)?1.0:0.0;
        }

        double[] old = instances.attributeToDoubleArray(instances.numAttributes()-1);

        for (int i = 0; i<old.length; i++){
            if (old[i] == 0.0){
                if (finalPrediction[i] == 0.0){
                    TN++;
                } else {
                    FP++;
                }
            } else {
                if (finalPrediction[i] == 1.0){
                    TP++;
                } else {
                    FN++;
                }
            }
        }

        return finalPrediction;
    }


    public double evaluateInstance(Instances train, Instance instance, double percent) throws Exception {
        double prediction = 0;

        for (Integer i : allClassifiers.keySet()) {
            AbstractClassifier tempClassifier = allClassifiers.get(i);
            Evaluation eval = new Evaluation(train);
            double out = eval.evaluateModelOnce(tempClassifier, instance);
            prediction += out;
        }

            return (prediction>total*percent)?1.0:0.0;


    }

    public double accuracy(){
        return (TP+TN)/(TP+TN+FP+FN);
    }

    public double TPRate(){
        return TP/(TP+FN);
    }

    public double FNRate(){
        return FN/(TP+FN);
    }

    public double TNRate() {
        return TN/(TN+FP);
    }

    public double FPRate() {
        return FP/(TN+FP);
    }

    public double precision (){
        return TP/(TP + FP);
    }

    public double recall (){
        return TP/(TP + FN);
    }

    public double fMeasure(){
        return 2*(precision()*recall())/(precision()+recall());
    }
}
