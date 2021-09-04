




-------------------------------------------------------------------------

Outline
=========
This project is a text editor with all the features like spelling check, auto complete, text generation and spelling suggestion.  
A few other features have also been added to this project. It can calculate the flesch score, Markov text generation model has been added, word paths(game - described at the end of the document) has also been implemented


Benchmarking between Linked Lists and balanced Binary Search Trees
Linked Lists and BST are used to store the words as in dictionary
Markov text generation

BasicDocument vs EffiientDocument - efficient document stores everything in instance variables with one call to getTokens ()

-------------------------------------------------------------------------
---------------------------------------------------------------------------
Description
==============

Number of words, number of sentences and number of syllables are calculated using Lists. This process is then made efficient by storing all the details in instance variables in eficientDocument in one pass instead of calulating it in every call to flesch score  

Flesch Score calculation is implemented which determines how diffiult a writing is. Flesch score is calculated using the following formula  
![Flesch Score](Flesch Score.png)

Linked list is implemented with all the methods and tests

Markov text genertion is implemented using linked list(Java Collections frame work). An inner class is used to store a word and all the words following it. 

Spell cheking is implemented using linked lists and BST. Ditionaries are implemented to store all the words in the text using linked lists and BST. written text is checked against these dictionaries for spell checking

Auto complete is implemented using the Trie data structure. BFS is used to generate all the completions. Auto complete can distinguish between upper case and lower case letters and words generated will be different based on the case of the letters


Spelling suggestion is implemented using a linked list and BFS algorithm. Spelling suggestion are based off string mutations of the original word. A threshold of 1000 words is enforeced to look for spelling suggestion so as not to deviate too far from the original word.

Benchmarking
-------------
Some aspects of the project are implemented in different ways and then performance is measured with the help of benchmarking.  

Flesch score is calculated in two different ways and is benchmarked for performance analysis. Results are plotted and shown below    

![Basic Document vs Efficient Document](Basic Document vs Efficient Document.JPG)   


Spell checking is implemented in two different ways and is then benchmarked for performance analysis. results are plotted and shown below  

![Performane Linked list vs BST](Performane Linked list vs BST.PNG)   




---------------------------------------------------------------------------
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

This is the course project for Course 2 in the
Java Programming: Object Oriented Design of 
Data Structures Specialization

Starter Code and GUI Application for has been provided along with grading previews and 
testing files to be used in completing the course programming 
assignments. 

-------------------------------------------------------------------------

Word Paths is a game where we'll try to find a path from one word to another with the restriction that we can only make one change at a time (letter permutation, letter insertion, letter deletion) and that whatever change we make, has to result in a real word.

  For example, I can create a path from the word "time" to "theme" through 4 changes (or 5 total words including "time" and "theme"):

time -> tie -> tee -> thee -> theme

The changes below you'll recognize from spelling suggestions:

time -> tie         results from a character deletion (m)

tie -> tee           results from a character modification (i to e)

tee -> thee        results from a character addition (e)

thee -> theme   results from a character addition (m)


<img src = "./Performane Linked list vs BST.PNG">

![asta la vista ](https://github.com/HamadKhushik/UCSD-Text-Editor/blob/master/MOOCTextEditor/images/Basic%20Document%20vs%20Efficient%20Document.jpg)

![Flesch Score](Flesch Score.png)

![Basic Document vs Efficient Document](Basic Document vs Efficient Document.JPG)
