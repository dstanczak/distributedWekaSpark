package uk.ac.manchester.ariskk.distributedWekaSpark.associationRules

import weka.associations.AssociationRules
import java.util.HashSet
import weka.associations.AssociationRule
import scala.collection.mutable.HashMap



class WekaAssociationRulesPartitionMiningSparkReducer extends java.io.Serializable{
  
  
  def reduce(rulesMapA:HashMap[String,UpdatableRule],rulesMapB:HashMap[String,UpdatableRule]):HashMap[String,UpdatableRule]={
    println(rulesMapA.isEmpty+" "+rulesMapA.keys.size)
    println(rulesMapB.isEmpty+" "+rulesMapB.keys.size)
    var rulesMapA1=rulesMapA
    rulesMapB.foreach{
       rule=>{
         
       //  println(rule._1)
        if(rulesMapA1.contains(rule._1)){
        println("hooray")
        val modifiedRule=rulesMapA1(rule._1)
        modifiedRule.addConsequenceSupport(rule._2.getConsequenceSupport)
        modifiedRule.addPremiseSupport(rule._2.getPremiseSupport)
        modifiedRule.addSupportCount(rule._2.getSupportCount)
        modifiedRule.addTransactions(rule._2.getTransactions)
        rulesMapA1.update(rule._1,modifiedRule)
        
      }
      else{
        rulesMapA1+=(rule._1 -> rule._2)
      }
    }
    }
   // exit(0)
    return rulesMapA1
  }

}