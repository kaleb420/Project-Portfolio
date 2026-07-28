Hello, and welcome to my project portfolio, this is a collection of every project I have ever worked on or been a part of. Anything with a vague name such as Intro to Java is a collection of projects I had to do for a class. While anything with a specific name like Tigris and Euphrates is a personal project I worked on. There will be a header for each project so anything of interest can be researched with ctrl+f. 

Computer Systems:

A collection of projects that focused on developing our skills in understanding the core ideology of computer design. This class limited the amount of outside function help we could use, for example, the base calculator project could not be completed by simply using a pre-made function to translate a number into a different base. Through these projects I developed a better understanding of the under-the-hood operations of computers. Translating numbers into floats, using threads to make the program more efficient, or serializing and deserializing a set of instructions according to the RISC-V format. This course is the pre-cursor to the Operating Systems course. 

Data Structures:

This class gave me a fundamental understanding of data structures and their associated efficiency. A few key concepts we implemented was red black trees, doubly linked lists, probing, breath first search, depth first search, and dynamic programming. While these concepts are useful by themselves understanding how to use them is the bigger issue. Basing things on big O and knowing when to use what search algorithm is the key to successfully using search algorithms.

Intro to C:

This class taught me about the basics of the C programming language, primarily teaching pointers, structs, file IO, and doubly linked lists. 

Intro to Java:

This class taught me the basics of the Java programming language, primarly teaching design methodology, testing programs, file IO, data structures, recursion, problem solving, and designing readable and well-commented code.

Intro to Python:

This class is a beginner course to the Computer Science department, teaching very elementary level concepts in Python. The concepts taught were programming basics, loops, problem solving, file IO, and outside libraries. 

Spots:

Through this project I've gained firsthand experience in learning the importance of testing programs, having a well-thought out design plan, designing code to be readable and adjustable, keeping functions simple, having an understandable file structure, and adjusting code to account for user-input.

If I had to redo this project, first I would have a user input file, that takes care of all user input that may be needed for other functions. Then I would use superclasses more, as there are 5 green cards in the game, each one could be a subclass of the superclass green card. While I'm not fond of designing tests, this project needs more thorough test to account for user input. Visual elements would also be something that would enhance the project. 

This is a personal project, recreating the board game Spots. Spots is a push your luck, trick taking game focusing on rolling dice. These dice are the core mechanic of the game, as rolling a specific dice means if it can be put on a dog card, or must be placed in the yard. With too many dice being placed in the yard causing the player to bust and lose all progress up to that point. The trick taking relies on picking from a shared card pool in the center of the table, doing the associated action, and then flipping the card over, making it so the other players cannot use that card until the next round. This game heavily relies on user input dictating where dice should be placed, what cards are used, and whether to activate specific elements of those cards. 

Tigris and Euphrates:

A more in-depth (but still lackluster) explanation of the rules can be found below, but a simple explanation of what this project needed to implement is as follows. Search algorithms are the fundamental building block of this project. Monuments, conflicts, resource increments, treasure collection, etc. Using those search algorithms I was able to determine what is a part of a region, if that region is a kingdom, if that region is a kingdom if the monument should award a resource. Other than search algorithms, designing a convenient user experience is another key part of this project. A map must be displayed to the users, therefore, ensuring that they are able to easily read the map is a key part of this program. To accomplish this, under-the-hood elements were implemented, such as resource tiles string length being 1, leader string lengths being 2, and monument string lengths being 3. Therefore, the search algorithm was able to differentiate what position on the map is what without needing to analyze it further. Then following a consistent format, such as the leader string being the player's faction initial plus the resource type initial allowed for a convenient experience for who to add what resource to if certain conditions are met. 

While this project is an impressive feat it's not perfect. First, there is very likely a better way to implement this. The map was coded to be a 2d array of strings, and while this gets the job done it doesn't necessarily mean the code is clean and easy. The code provided does have clean comments that explain what the purposes of each function does and for the classes giving the purpose of the classes, but its very limiting. Perhaps a better implementation is to use a generic class for the map. And instead of relying on string input to dictate what that tile is, a class could be used instead, with the class itself being the key in the 2d array, and the map printing the printable value of the class. Another thing that could be improved is design methodology. Even though this was done in a reasonable amount of time (20 hours) it was not necessarily the cleanest implementation. Classes had to be reimplemented, functions had to be rewritten, ideas had to be rethought. Having a clearer idea of the impelmentation and what the goals of that implementation are could have been better understood. 

This is a personal project, recreating the board game Tigris and Euphrates (T&E). T&E is a tile placement game where players attempt to steal other resources from others. Tiles that can be placed (eihter directly or indirectly) are resources, leaders, monuments, catastrophes, and conflicts. Tiles are considered connected to one another if they have a tile directly adjacent, this occurs as long as there is a continuous stream of tiles connected. A continuous stream of tiles is called a region, and a region with a leader is considered a kingdom. If two same color leaders are in the same kingdom a conflict begins, and they must gain support either through the tiles adjacent to the leader or from the same color tiles in the kingdom. 
