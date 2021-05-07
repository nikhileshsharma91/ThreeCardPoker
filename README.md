"Three Card Poker" Judger
=====================

This program judges a game of three-card poker. The program is designed to find the winning hand and return the ID of the winning player.

Program Design
------------
1. The input gets converted into a list of cards with each card having a rank and a suit associated with it.
2. A hand object is create from the list of cards which will contain the information such as player's ID, hand type (Straight Flush, Three of A Kind, ...) etc.
3. All the hands are then stores in a list of hands which will be evaluated.
4. The ThreeCardPokerHandEvaluator class will evaluate the hand type (Straight Flush, Three of A Kind etc) and run a comparison to find the winner.

How to build and test
------------
### Prerequisite: ###
* Java 8
* Python 3

### Step 1: Change directory to source ###
```
cd src
```
### Step 2: Compile the code ###
```
javac ThreeCardPoker.java
```
### Step 2: Run the tests ###
```
python3 run_tests "java ThreeCardPoker"
```

Program Limitations
------------
The current program is limited to a Three Card Poker game. However the program is designed in order to make room for scalability such as for a Five Card Poker Game. <br />
The following classes can be reused:
* Card.java
* Hand.java 
* HandType.java (Enum)
* PokerHandEvaluator.java (Interface)
