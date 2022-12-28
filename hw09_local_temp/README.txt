=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: jlee0902
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
    -2D Arrays allowed me to track the guesses the user made. My 6x5 2D array stored
    Blocks, which were individual JTextField classes. This made it convenient to
    track each character of the guessed word and match them to return a color feedback
    on if the letter was in the right position, in the target word, or not in the
    target word at all. Using Blocks as the type opposed to Strings or Characters made
    it easier for the program to interact between the GUI and core-game model.

  2. File I/O
    -I used File I/O for two main purposes. The first one was to read from a word bank
    of valid words. This made it easy to check if the user guessed a valid word but also
    randomly generate target words for the user to guess. The second purpose was to save
    game states. I provided a Save button, which let the user save the current state of
    the game to come back later. When this button is pressed, the program writes the
    target word and the words guessed into a text file. When the Load button is pressed
    later in any game, the user is able to pick up from where they left from.

  3. Collections
    -I found Collections useful for two main reasons. The first was one store moves of
    the user. I stored the moves in a LinkedHashMap, which has an Integer that indicates
    the number of the move as the key and an ArrayList of the word the user guessed. This
    was convenient especially when saving the game state into a text file, since I could
    write what words were guessed by retrieving values from the Collection. Another reason
    I used Collections was to track each letter in the alphabet. I used a HashMap with
    the alphabet as the key and the corresponding color (Green, Yellow, Gray). This
    allowed me to display colors on the keyboard that helped the user track which letters
    of the alphabet they should use next.

  4. JUnit testable component
    -JUnit testable components were used for an overall smoother interaction between the
    underlying game state and the GUI. I created a separate Wordle class that runs a Wordle
    game internally. Testing the class itself helped me solidify the functionality before
    moving onto the GUI. If I didn't do this, so many more errors would've occurred and
    could've been harder to debug.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  -Game.java:
    -A class that simply invokes a RunWordle instance so the game can run.

  -Wordle.java:
    -A class that runs a Wordle game separate from the GUI but also dictates the actions the
    GUI should take. Its main functions include: guessWord(), reset(), undo() which returns
    feedback on a user's guess, reverts the game to its initial state, and cancels the previous
    step, respectively.

  -RunWordle.java:
    -A class that builds the main GUI the user will interact with. It displays a JFrame that contains
    JPanels and JButtons the user can use to play the game. The four main components of this JFrame
    are: control panel, game board, letter board, and game state display.

  -Instructions.java:
    -A class that invokes a JFrame including instructions on how to play the game. Text and image
    descriptions are included in the user interface.

  -Block.java:
    -A class that extends JTextField, which displays a corresponding letter and color according to its
    state. In the game board, users can input a letter into each Block to guess a word. In the letter
    board, each block will change color according to the guesses of the user, displaying either green,
    yellow, or gray.

  -GameBoard.java:
    -A class that the user directly interacts with to guess the target word. It extends JPanel, also
    containing a 6x5 2D array of Blocks. Users are allowed to change the characters in each block
    of the row they are currently on (the other rows are disabled from modification). Once the user
    guesses a word, they move onto the next row and the previous row displays colors for each Block,
    indicating the conditions to their guess.

  -LetterBoard.java:
    -A class that the user cannot interact with but should find extremely useful. It extends the
    JPanel, also containing a 3x10 array of Blocks. Each block displays a letter from the alphabet,
    which initially is white but changes color after each guess, indicating information about the
    guess.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
    -My biggest obstacle was passing information between classes, especially GameBoard.java and
    LetterBoard.java. The two had to work together to display information, but the parameters
    they needed were different. I initially thought of making any variable I need to pass
    public, but I later realized that was bad design. I figured out that I could simply
    make functions that return the value of the private variable. Another hardship I experienced
    was storing and recalling the game state. Since txt files only contain String values, it was
    a struggle for me to find how to store the number of guesses or current row the user was on.
    I later found that utilizing iteration while reading from the file would help facilitate the
    quantitative data I found difficult to track.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
    -I believe the separation of functionality between the Wordle game and the GUI was nice.
    However, I believe I could've done better in designing the GUI. The final decision I made
    was to separate the game board and letter board, but I think I should've put them together
    in one file. Separating them led to difficulties in passing information and encapsulating
    the private state. All I was worried was the GUI looking janky, but I could've worked that
    out which would've made the data structure design way more simple.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  -Text file for valid words: https://gist.github.com/dracos/dd0668f281e685bad51479e5acaadb93
  -Text file for target Wordle words: https://github.com/aheedshah/Wordle/blob/master/targetWords.txt
  -Wordle instructions: https://www.nytimes.com/games/wordle/index.html
