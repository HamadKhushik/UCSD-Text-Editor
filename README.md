




-------------------------------------------------------------------------

Outline
=========
This project is a text editor with all the features like spelling check, auto complete, text generation and spelling suggestion.  
A few other features have also been added to this project. It can calculate the flesch score, Markov text generation model has been added, word paths(game - described at the end of the document) has also been implemented


Benchmarking is used for performance analysis of different implementations

-------------------------------------------------------------------------

--------------------------------------------------------------------------

Files
===================

Introduction and Working with Strings
---------------------------------------
document.Document.java  
document.BasicDocument.java  

Efficiency Analysis and Benchmarking
--------------------------------------
document.EfficientDocument.java  

Interfaces, Linked Lists vs. Arrays, and Correctness
--------------------------------------------------------
textgen.MyLinkedList*.java  
textgen.MarkovTextGenerator.java  
textgen.MarkovTextGenerator*.java  

Trees! (including Binary Search Trees and Tries)
-----------------------------------------------------
spelling.SpellingSuggest.java  
spelling.AutoComplete.java  
spelling.Dictionary.java  
spelling.Dictionary*.java  
spelling.AutoCompleteDictionaryTrie.java  
spelling.TrieNode.java  

Hash Maps and Edit Distance
-----------------------------------------
spelling.WordPath.java  
spelling.NearbyWords.java  
spelling.WPTree.java  

----------------------------------------------------------------- 

---------------------------------------------------------------------------
Description
==============

Number of words, number of sentences and number of syllables are calculated using Lists. This process is then made efficient by storing all the details in instance variables in eficientDocument in one pass instead of calulating it in every call to flesch score  

Flesch Score 
-------------
Flesch Sore is implemented which determines how readable the text is! Flesch score is calculated using the following formula  
![Flesch Score](https://github.com/HamadKhushik/UCSD-Text-Editor/blob/master/MOOCTextEditor/images/FleschScore.png)

<img src = "https://github.com/HamadKhushik/UCSD-Text-Editor/blob/master/MOOCTextEditor/images/FleschScore.png" height="50x" width="100">

Linked list
------------
Linked List is implemented with all the methods and tests

Markov text genertion
-----------------------
Markov Text Generation is implemented using linked list(Java Collections frame work). An inner class is used to store a word and all the words following it. 

Spelling check 
----------------
Spell checking is implemented using linked lists and BST. Ditionaries are implemented to store all the words in the text using linked lists and BST. written text is checked against these dictionaries for spell checking

Auto complete 
---------------
Auto Complete is implemented using the Trie data structure. BFS is used to generate all the completions. Auto complete can distinguish between upper case and lower case letters and words generated will be different based on the case of the letters


Spelling suggestion 
---------------------
SPelling Suggestion is implemented using a linked list and BFS algorithm. Spelling suggestion are based off string mutations of the original word. A threshold of 1000 words is enforeced to look for spelling suggestion so as not to deviate too far from the original word.

Benchmarking
-------------
Some aspects of the project are implemented in different ways and then performance is measured with the help of benchmarking.  

Flesch score is calculated in two different ways and is benchmarked for performance analysis. Results are plotted and shown below    

![](https://github.com/HamadKhushik/UCSD-Text-Editor/blob/master/MOOCTextEditor/images/Basic%20Document%20vs%20Efficient%20Document.jpg)  


Spell checking is implemented in two different ways and is then benchmarked for performance analysis. results are plotted and shown below  

![Linked list vs BST](https://github.com/HamadKhushik/UCSD-Text-Editor/blob/master/MOOCTextEditor/images/Performane%20Linked%20list%20vs%20BST.PNG)

---------------------------------------------------------------------------

Note
-------

This is the course project for Course 2 in the
Java Programming: Object Oriented Design of 
Data Structures Specialization

Starter Code and GUI Application for has been provided along with grading previews and 
testing files to be used in completing the course programming 
assignments. 

-------------------------------------------------------------------------

Word Paths 
-------------
It is a game where we'll try to find a path from one word to another with the restriction that we can only make one change at a time (letter permutation, letter insertion, letter deletion) and that whatever change we make, has to result in a real word.

  For example, I can create a path from the word "time" to "theme" through 4 changes (or 5 total words including "time" and "theme"):

time -> tie -> tee -> thee -> theme

The changes below you'll recognize from spelling suggestions:

time -> tie         results from a character deletion (m)

tie -> tee           results from a character modification (i to e)

tee -> thee        results from a character addition (e)

thee -> theme   results from a character addition (m)

---------------------------------------------------------------------------------------------------------------------