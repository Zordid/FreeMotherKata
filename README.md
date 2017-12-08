# After-Work Java Coding Dojo December 6th 2017
by  Software Craftsmanship Meetup Softwerkskammer München and it-economics GmbH

## Kata: Free The Mother of (Kotlin!)
To unlock the door, you must find a code that has to
satisfy 3 conditions.

1. it must consist of 9 **_unique_** digits [0..9]
2. when read from right to left, it must be a **_square number_**
3. when read from left to right, it must be the **_largest possible_** number

**Team: Gianluca, Torsten, Olaf**

This solution uses Kotlin as a programming language.

### First attempt on the evening of the Dojo

Start trying with the largest possible 9-digit number 999,999,999 and
iterating down until all conditions are met.

The three conditions were directly implemented in code.

1. `fun testCondition1(code: String): Boolean `

   This tests a given string against three aspects:

   - containing only the valid characters '0' to '9' (not really necessary
   as we will feed the algorithm only with numbers)
   - containing of exactly 9 unique digits

2. `fun testCondition2(code: String): Boolean `

   This tests whether the number created by reversing the code is a square number

The third condition is a little tricky. To test whether the given code
is indeed the largest possible 9-unique-digit number, it is necessary to go
upwards again towards the maximum 9-digit-unique number, falsifying the two
first conditions again.

As the main cracker loop starts testing conditions 1 and 2 starting from
the largest possible combination 987,654,321 (a good performance boost over
starting with 999,999,999!), this third condition is really not
necessary to be tested again.

Nevertheless, this third condition check has also been implemented afterwards.

### Vast improvements done by further optimization

As it turns out, the "brute force" attempt to count down the numbers from 999,999,999
and filtering out the ones containing duplicate digits by `code.toSet().size ==9`
is the main bottleneck. There are just way too many impossible combinations to be
checked during the countdown.

One idea is to implement an iterator, more specifically a descending iterator, that
contains an algorithm to only emit suitable codes containing each digit at most one
time.

This is done with the `UniqueCodeDescIterator` and dedicated tests for this
class.

It is amazing how fast this iterator generates the correct, unique numbers.


