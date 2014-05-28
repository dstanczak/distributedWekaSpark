package uk.ac.man.aris

import weka.classifiers.Classifier
import org.apache.spark.rdd.RDD
import weka.core.Instances
import weka.classifiers.evaluation.Evaluation


class WekaClassifierEvaluationSparkJob {
  
  def evaluateClassifier (classifier:Classifier,headers:Instances,dataset:RDD[String]): Evaluation={
    
    
    val eval=dataset.glom.map(new WekaClassifierEvaluationSparkMapper().map(_,null)).reduce(new WekaClassifierEvaluationSparkReducer().reduce(_,_))
    return eval
  }

}