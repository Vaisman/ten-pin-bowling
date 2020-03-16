## Bowling Score Calculator 

[![Travis build Status](https://travis-ci.org/Vaisman/ten-pin-bowling.svg?branch=master)](https://travis-ci.org/Vaisman/ten-pin-bowling)

[![License: ISC](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/ISC)

A bowling score calculator application.

## How does it work?

The score calculator works by implementing the rules of score calculation described here [Score Calculation](https://slocums.homestead.com/gamescore.html)

## Symbols and Information
  * **Strike** usually represented as an **X** is when the player knocks down all the pins during the first chance of a frame.
  * **Spare** usually represented as a **/** is when the player knocks down the rest of the remaining pins a next try.
  * **Miss** usually represented as a **-** is when the player does not knock down any pins upon throwing.

## Example 1
 * Input:  ```X|X|X|X|X|X|X|X|X|X||XX ```
 * Score:  ```300 ```
 
## Example 2
  * Input:  ```X|7/|9-|X|-8|8/|-6|X|X|X||81 ```
  * Score:  ```167```
 
## Usage example
 ```
   mvn clean install
   java -jar ten-pin-bowling.jar -s "X|7/|9-|X|-8|8/|-6|X|X|X||81"
   java -jar ten-pin-bowling.jar -s "X|7/|9-|X|-8|8/|-6|X|X|X||81" -o json
   java -jar ten-pin-bowling.jar -game-score-info "X|7/|9-|X|-8|8/|-6|X|X|X||81" -output json
   java -jar ten-pin-bowling.jar -game-score-info "X|7/|9-|X|-8|8/|-6|X|X|X||81" -output string
 ```

