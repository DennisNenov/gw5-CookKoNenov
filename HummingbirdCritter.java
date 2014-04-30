/*****************************************************************

    NAME --- TEAM NAME
    APCS pd #
    HW## -- GridWorld, Part 4
    yyyy-mm-dd *
    class ____Critter *
  A new type of Critter has appeared on the scene:

HummingbirdCritter Specs:
A HummingbirdCritter is a critter that changes the color 
(making them brighter) of Flower critters found in the locations 
directly to its left/right or in front/back of it (4 locations of 
the 8 surrounding). It can only move forward and moves 2 spots at 
a time. If a HummingBird Critter cannot move, it turns 45 degrees, 
randomly to left/right.

HummingbirdCritter Test Cases:
If a HummingbirdCritter finds a flower in the spot in front of it
 and one in the spot to its left, it should set the colors of both 
to be brighter, and then move 2 spots in its current direction.
If a HummingbirdCritter is facing a rock or the edge of the grid 
(or has one within the 2 spaces in front of it), it should turn45
 degrees right/left and move 2 spaces in that direction instead.
 If the chosen direction is also blocked, it should keep turning in 
that direction.

 *****************************************************************/ 

public class HummingbirdCritter  { }
