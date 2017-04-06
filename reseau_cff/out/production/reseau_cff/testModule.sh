#!/bin/bash
# Pour utiliser ce fichier, 2 manières :
# 1) La commande suivante lance le test "1" et compare de manière brève (le 2ème argument "q") les fichiers produits.
# ./testModule.sh 1 q
# 2) La commande suivante lance le test "1" et compare side by side les fichiers (plus long)
# ./testModule.sh 1
cat ../../../../testModule/commandes_de_test/cmd_a_tester_pt_$1.txt | (java Main > ../../../../testModule/results_out/my_results_$1.txt) >& ../../../testModule/errors_out/my_results_$1.txt
diff -s$2y ../../../../testModule/results_out/my_results_$1.txt ../../../../testModule/results_out/_$1.txt
diff -s$2y ../../../../testModule/errors_out/my_results_$1.txt ../../../../testModule/errors_out/_$1.txt
