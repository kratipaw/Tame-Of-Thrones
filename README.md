# Tame-Of-Thrones

There is no ruler in the universe of Southeros and pandemonium reigns. 
There are six kingdoms in Southeros.Each kingdom has an animal emblem. They are: 
LAND emblem - Panda, 
WATER emblem - Octopus, 
ICE emblem - Mammoth, 
AIR emblem - Owl, 
FIRE emblem - Dragon,
Space emblem - Gorilla

## Problem 1: A Golden Crown

Shan, the gorilla king of the Space kingdom wants to rule all Six Kingdoms in the universe of Southeros.
Shan needs to send a message with the animal in the message to win them over. 
He needs the support of 3 more kingdoms to be the ruler.

Your coding challenge is to have King Shan send secret message to each kingdom and win them over.
Once he wins 3 more kingdoms, he is the ruler! The secret message needs to contain the letters of the animal 
in their emblem. 
For example, secret message to the Land kingdom (emblem: Panda) needs to have the letter 'p','n','d' at-
least once and 'a' at-least twice. If he sends "a1d22n333a4444p" to the Land kingdom, he will win them over.

### Sample Input Output

**Example 1 :** 
Who is the ruler of Southeros?
Ouput: None
Allies of Ruler?
Output: None

Input Messages to kingdoms from King Shan:

Input: Air, “oaaawaala” Input: 
Input: Land, “a1d22n333a4444p” Input: 
Input: Ice, “zmzmzmzaztzozh” 

Output :

Who is the ruler of Southeros?
Output: King Shan
Allies of Ruler?
Output: Air, Land, Ice

**Exaxmple 2 :**

Who is the ruler of Southeros?
Output: None
Allies of King Shan?
Output: None

Input Messages to kingdoms from King Shan: 
Input: Air, “Let’s swing the sword together”
Land, “Die or play the tame of thrones”
Ice, “Ahoy! Fight for me with men and money”
Input: Water, “Summer is coming”
Input: Fire, “Drag on Martin!”

Who is the ruler of Southeros?
Output: King Shan
Allies of King Shan?
Output: Air, Land, Ice, Fire

## Problem 2: Breaker Of Chains

The other kingdoms in the Universe also yearn to be the ruler of Southeros and war is imminent! 
The High Priest of Southeros intervenes and is trying hard to avoid a war and he suggests a ballot system 
to decide the ruler. Your coding challenge is to help the High Priest choose the ruler of Southeros 
through the ballot system.

**Rules of the Ballot system**

1. Any kingdom can compete to be the ruler.
2. They should send a message to all other kingdoms asking for allegiance.
3. This message will be put in a ballot from which the High Priest will pick 6 random messages.
4. The High Priest then hands over the 6 messages to the respective receiving kingdoms.
5. The kingdom that receives the highest number of allegiance is the ruler.

**Rules to decide allegiance by a kingdom**

1. The receiving kingdom has to give allegiance to the sending kingdom if the message contains the letters 
	of the animal in their emblem (same as problem 1).
2. If the receiving kingdom is competing to be the ruler, they will not give their allegiance even 
	if the message they received is correct.
3. A kingdom cannot give their allegiance twice. If they have given their allegiance once, 
	they will not give their allegiance again even if they get a second message and the message is correct.

**In case there is a tie**
1. If there is a tie, the whole ballot process is repeated but only with the tied kingdoms till 
there is a winner.

**Sending messages format :**
 
The format of the message dropped in the ballot should contain :
- The Sender kingdom
- The Receiver kingdom
- The Message (should be selected randomly from the messages provided in the message.txt file)

### Sample Input Output 

**Example 1 :**

Who is the ruler of Southeros?
Output: None
Allies of Ruler?
Output: None

Enter the kingdoms competing to be the ruler:
Input: Ice Space Air

(the messages should now be randomly picked from the given table for each).

Results after round one ballot count
Output: Allies for Ice : 2
Output: Allies for Space: 1
Output: Allies for Air: 0

Who is the ruler of Southeros?
Output: Ice
Allies of Ruler?
Output: Land Fire

**Example 2 :**

Who is the ruler of Southeros? 
Output: None 
Allies of Ruler? 
Output: None 

Enter the kingdoms competing to be the ruler: 
Input: Land Air 

Results after round one ballot count 
Output: Allies for Land : 1 
Output: Allies for Air: 1 

Results after round two ballot count 
Output: Allies for Land : 1 
Output: Allies for Air: 2 

Who is the ruler of Southeros? 
Output: Air 
Allies of Ruler? 
Output: Fire Space 

**Example 3 :** 

Who is the ruler of Southeros?
Output: None
Allies of Ruler?
Output: None

Enter the kingdoms competing to be the ruler:
Input: Fire Space

Results after round one ballot count
Output: Allies for Fire : 0
Output: Allies for Space: 0

Results after round two ballot count
Output: Allies for Fire : 1
Output: Allies for Space: 2

Who is the ruler of Southeros?
Output: Space
Allies of Ruler?
Output: Land Ice

