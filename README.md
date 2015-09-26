# Sequential_Covering_Algorithm
Classifier Type: Rule Based Classifier

Sequential covering algorithm extracts rules directly from data. Rules are grown in a greedy fashion based on certain evaluation.

The algorithm 
- extract rules from training data set based on the classes
- classes are sorted first from less frequent to more frequent
- applies Learn One Rule strategy to grow the rule
- Uses Laplace for rule evaluation and stop growing of rule further
- can have the option of growing rules by rule pruning in which validation data set is compared after a rule is formed and prunes based on the generalization error
- finally orders the pruned rules
- measures accuracy on the training and the special testing set

Program Language: Java in MVC design pattern

Object model was done using stacks

Module was developed only using standard Java libraries under JDK 8 and not by the use of any predefined data mining libraries
