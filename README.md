# Mirrors & Lasers: The Game

## Description 🚀

The institute of mirrors, lasers and labyrinths, wants a program that allows to evaluate the reasoning capacity of the personnel who are hired. The program is simple and can be seen as a game. It is presented to the user a grid or table of n rows by m columns, within which there are k mirrors that are not visible.

Each of the cells of the grid are identified through a nomenclature in which the row is given by a whole number (the first row is assigned the number 1, the second row the number 2, etc.), and the column is given by an uppercase letter (the first column is A, the second B, etc.). Therefore, there are a maximum of 26 columns (for the 26 letters of the English alphabet).

Inside the grid, although they are not visible to the user, the mirrors are only arranged in two ways, inclined to the right like this / or inclined to the left like this \

The user of the program has the possibility to fire a laser beam horizontally or vertically from any cell on the edge of the grid. The initial direction of the fired beam is given by the place from which the shot is fired. If it is done from the cells on the left or from the right edge, the shot is horizontal. If it is done from the top or from the bottom edge, the shot is vertical. If shooting from a cell that is in a corner of the grid, the user must indicate not only the cell but the direction in which he/she wants to shoot: horizontally (H) or vertically (V). When a shot is done, the program indicates which cell in the grid the beam comes out of.

Once the shot is indicated, the program displays the same grid but indicating with a capital 'S' (from "Start") the cell from which the beam started, and an 'E' (from "End") where the shot leaves the grid.

The program has a simple menu with 3 options. The first option is to play, the second option is to see the leaderboard, and the third option is to exit the program.

When the user chooses to play, a game, a grid of size m x n is created, with k mirrors randomly placed in any of the cells on the grid. It is also randomly decided, at the moment of creating the grid, which is the arrangement of each mirror (if tilted to the right / or to the left \ ).

When returning to the main menu, either by typing 'menu' or by winning the game, a score is calculated for the user that is stored in a binary search tree ordered by score. The option 2 displays a list of the users' nickname with their respective scores, result of traversing the binary search tree in inorder travel.

### Score calculation

After doing many trials on the calculator with different mathematical operations, I came up with the following invented formula. There is no specific reason why I used an specific mathematical operation present in this formula. Each operation was just an idea that came to my mind and that's it. On the other hand, it must be clarified that with this formula, the more shots the player makes and the more failed location attempts he/she has, the higher his/her score will be. That is to say that the less the player shoots and the less he/she makes mistakes locating mirrors, the lower his/her score will be. Therefore, the one with the lowest score will be better ranked on the leaderboard.

* Each laser fired: + 50 points.
* Each failed location attempt: + 100 points.

Formula:



## Documentation 📃

Check the functional requirements and the UML Class Diagram [here]

## Technical conditions 🛠️

* Programming Language 💱 : [Java]
* Operating system used 💻 : macOS Catalina v. 10.15.7
* Integrated Development environment used 👨🏻‍💻 : Visual Studio Code v. 1.50.1
* Installation 🔧 : Download the .ZIP file. This program requires JRE 13.0.2+8

## Author 🖊️

[TheLordJuanes](https://github.com/TheLordJuanes)