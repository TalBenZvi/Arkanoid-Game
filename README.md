# Arkanoid
catch the balls as they drop and break all the blocks
<br>
<br>

## Features
- Main menu includes the ability to start the game (s), show hight score (h) or quit (q)
- Smooth animations and collision physics
- Press 'p' mid game to pause, press SPACE to resume (or continue to the next screen)
- High score tracker that remembers your high score between runs (high score saved in file 'highscores.txt')
- Current score and lives indicated at the top of the screen
- Play my premade levels by importing the 'resources' directory or design your own (rules explained below)
<br>

### BIUOOP
Special thanks to Bar-Ilan University for providing me with the means to use their graphic features (the biuoop package is necessary for those to work)
<br>
<br>

## Level Design
In order to design your own levels you will need to create 2 types of text files:
- A block defenitions file for each level describing the types of blocks that the level uses
- 1 level defenitions file to describe the structure and parameters of all the levels
<br>

Run the program with the path to your level defenitions file as the first argument or run with no arguments to use the premade levels

### Block Definitions File Format
The block definitions file maps characters to spacing elements or block-information.

set default values for all the blocks by writing a single a single line in the next format:<br>
default key1:value1 key2:value2 ...

set values for a type of block and bind it to a symbol: <br>
bdef symbol:a key1:value1 key2:value2 ... <br>
bdef symbol:b key1:value1 key2:value2 ... 
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

set values for a type of spacer and bind it to a symbol:<br>
sdef symbol:* width:20 <br>
sdef symbol:^ width:10

A spacer-definition line has two properties (it does not inherit default definitions):
- symbol: the character which is used to represent the space-type in the levels-information file. Must be a single character.
- width: the width of the spacing element in pixels. Must be a positive integer.

<br>
<br>

### Level Definitions File Format
The file contains a sequence of level specifications. Each level specification begins with the line START_LEVEL and ends with the line END_LEVEL. <br>

level specifications format: <br>
START_LEVEL <br>
key1:value1 <br>
(...) <br>
keyN:valueN <br>
START_BLOCKS <br>
[BLOCK_LAYOUT] <br>
END_BLOCKS <br>
END_LEVEL <br>

<br>
Each level includes the following keys, as well as a BLOCKS section, which will be described below: <br>

- level_name specified the name of the level.
- ball_velocities specifies the ball velocities. This field is a space-separated list of items, each item is of the form a,s where s is the speed and a is the angle.
- background specifies the level's background. The format of its value is exactly the same as for the fill property when defining a block, it supports the two color formats and the image format.
- paddle speed specifies the paddle speed.
- paddle width specifies the paddle width.
- block_definitions specifies the file from which block definitions are read.
- blocks_start_x the blocks layout horizontal starting point, or the x value of the first block in every column.
- blocks_start_y the block layout vertical starting point, or the y value of the blocks on the first row.
- row_height- the blocks are arranged in equally-spaced rows. This field specifies the number of pixels in each row. If first row y value is 50 (blocks_start_y:50), the second row will be located at 50 + the value of row_height. 
- num_blocks the number of blocks that need to be destroyed in order to pass this level.
<br>

The block layout starts with a START_BLOCKS line and ends with an END_BLOCKS line. It should appear at the end of the level information. Within the block layout, each line (non empty) specifies one row of blocks, from row 0 downwards. A line with only spacer(s) means an empty row. Within each line, each character corresponds to either a block or an horizontal space, as defined in the blocks definition file.
<br>

<b>For examples of level and block design files, see 'resources'</b>

