package uk.ac.manchester.ariskk.distributedWekaSpark.associationRules

import weka.associations.AssociationRule


class UpdatableRule (rule:AssociationRule) extends java.io.Serializable{
  
  var support=rule.getTotalSupport()
  var premise=rule.getPremiseSupport()
  var consequence=rule.getConsequenceSupport()
  var transactions=rule.getTotalTransactions()
  var ruleID=rule.getPremise()+" "+rule.getConsequence()
  
  
  
  def getRule():String=return ruleID
  
  def getRuleString:String=return rule.getPremise()+" "+getPremiseSupport+" "+rule.getConsequence()+" "+getSupportCount+" conf:"+getCondidence+
                                  " lift:"+getLift+" leverage:"+getLeverage+" conviction:"+getConviction
  
  def getSupportCount:Int=return support
  def setSupportCount(sup:Int):Unit=support=sup
  def addSupportCount(sup:Int):Unit=support+=sup
  
  
  
  def getPremiseSupport:Int=return premise
  def setPremiseSupport(pr:Int):Unit=premise=pr
  def addPremiseSupport(pr:Int):Unit=premise+=pr
  
  def getConsequenceSupport:Int=return consequence
  def setConsequenceSupport(con:Int):Unit=consequence=con
  def addConsequenceSupport(con:Int):Unit=consequence+=con
  
  def getTransactions:Int=return transactions
  def setTransactions(tr:Int):Unit=transactions=tr
  def addTransactions(tr:Int):Unit=transactions+=tr
  
  //semantic checking
  def getRuleSupport:Double=return support.toDouble/transactions.toDouble
  
  
  def getCondidence:Double=return support.toDouble/premise.toDouble
  //def setCondidence(conf:Double):Unit=confidence=conf

  def getLift:Double=return (support.toDouble*transactions.toDouble)/(premise.toDouble*consequence.toDouble)
 // def setSupport(lf:Double):Unit=lift=lf
  
  def getLeverage:Double=return support.toDouble/transactions.toDouble-(consequence.toDouble/transactions.toDouble)*(premise.toDouble/transactions.toDouble)
  //def setLeverage(lev:Int):Unit=leverage=lev
  
  def getConviction:Double={
    if(1-(support.toDouble/premise.toDouble)==0){return 0}
    return (1-(consequence.toDouble/transactions.toDouble))/(1-(support.toDouble/premise.toDouble))}
  //def setConviction(con:Double):Unit=conviction=con
}