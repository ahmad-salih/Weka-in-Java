package importFile;

import com.sun.xml.internal.ws.api.policy.ModelGenerator;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportFile {
  private Instances dataset;

  public ImportFile(String path) {
    try {
      dataset = ConverterUtils.DataSource.read(path);
      if (dataset.classIndex() == -1) {
        dataset.setClassIndex(dataset.numAttributes() - 1);
      }
    } catch (Exception ex) {
      Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Instances getDataset() {
    return this.dataset;
  }
}
