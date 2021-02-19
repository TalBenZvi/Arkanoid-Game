# Arkanoid
catch the balls as they drop and break all the blocks
<br>
<br>

## Features
- Main menu includes the ability to start the game (s), show hight score (h) or quit (q)
- Smooth animations and collision physics
- Press 'p' mid game to pause, press SPACE to resume
- High score tracker that remembers your high score between runs (high score saved in file 'highscores.txt')
- Current score and lives indicated at the top of the screen
- Play my premade levels by importing the 'resources' directory or design your own
<br>
<br>


## Level Design
In order to design your own levels you will need to create 2 types of text files:
- A block defenitions file for each level describing the types of blocks that the level uses
- 1 level defenitions file to describe the structure and parameters of all the levels
<br>

### Block Definitions File Format
The block definitions file maps characters to spacing elements or block-information.

- set default values for all the blocks by writing a single a single line in the next format:
default key1:value1 key2:value2 ...

- set values for a type of block and bind it to a symbol:
bdef symbol:a key1:value1 key2:value2
bdef symbol:b key1:value1 key2:value2
<br>

each block defenition includes the following keys:
- symbol: the character which is used to represent the block type in the levels-information file. Must be a single character.
- height: the height of the block, in pixels. Must be positive integer.
- width: the width of the block, in pixels. Must be a positive integer.
- fill: The block should be filled using one of the following value formats:
  - color(colorname), where colorname is one of black, blue, cyan, gray, lightGray, green, orange, pink, red, white, yellow.
  - color(RGB(x,y,z)), where x, y, z are integers. The block should have the RGB color specified by the given x, y and z components.
  - image(filaneme.png), where filename.png is the name of a file. In this case, the blocks appearance should be taken from the given file. (The image is loaded at the position     of the block, no resizing or clipping is required, the image size must match the size of the definition)
- stroke: The blocks border is drawn using one of the following value formats (if this property is missing, no border is drawn):
  - color(colorname), see definition above (same as with fill).
  - color(RGB(x,y,z)), see definition above (same as with fill).

<br>

- set values for a type of spacer and bind it to a symbol:
sdef symbol:* width:20
sdef symbol:^ width:10

A spacer-definition line has two properties (it does not inherit default definitions):
- symbol: the character which is used to represent the space-type in the levels-information file. Must be a single character.
- width: the width of the spacing element in pixels. Must be a positive integer.




