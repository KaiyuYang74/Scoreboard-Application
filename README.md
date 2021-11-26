#Scoreboard application for the knockout stage of a football cup event

**What will the application do?**

The application is designed to simulate the whole process of the knockout stage of a football cup event. The user can enter
arbitrary-sized number of teams to initialize the program. Notice that the number of teams entered by the user must 
equal to *2<sup>n</sup> (n = 1, 2, 3, ... )*. After receiving the user's inputs, the application will simulate the draw 
ceremony. In real world, the draw ceremony means randomly pairing up teams from all qualified teams to generate games 
for the next round. Hence, randomization was introduced in this program to simulate this process. Notice that the draw
ceremonies will be held before each round of games. After the draw ceremony, the program will show the specific 
matchups on the screen to the user. The user need to enter the game results for each game in this round of games. The 
program will keep track of the winners of each game and will store the winners in this round in a list of teams. The winners in a
round of games will be the participant teams for the next round of games. The losers from the games are eliminated from 
this football cup event and not longer being considered. Then, the program will proceed to the next round of games. After 
several rounds of games, the final champion of this football cup event will be determined. Since in each round of games, the 
functionality is similar, recursive calls (natural recursion and mutual recursion) were constructed. Plus, when the
football cup event has actually started in real world, this app can serve as a scoreboard to keep track of the game results
and goal information on the purpose of statistics analysis. Moreover, the user can decide whether to continue this
simulation or quit the program by keyboard operations.

**Who will use this application?**

This program is built for anyone who are interested in the football cup event such as football fans, football experts 
(they want to analyse the statistics), coaches, players, etc.

**Why is this project of interest to me?**

First, I'm a football fan. I'd like to explore any football related topics. Second, I leared
binary tree in CPSC 110. The knockout stage of a football cup event can be modeled using a binary tree thus can be 
handled by recursive functions. As a UBC CS student, I trust the natural recursions and I love recursions. It's a good 
practive for me to familiarize the contents from both CPSC 110 and CPSC 210.

**Why the number of input teams must equal to *2<sup>n</sup> (n = 1, 2, 3, ... )*?**

In each round of games, the number of participant teams must be an even number to make sure every participant team gets
assigned an opponent. As mentioned above, each round of games will eliminate half of the teams from the football 
cup event, thus the number of teams initially entered by the user must equal to 2 to the power of n with n are positive 
integers .

##Format for the user to enter the inputs
- team/score inputs should be separated by space. Only by doing so, the program can identify the information correctly.
- The input scores for a game should strictly follow: team1's score [space] team2's score.
- An example for team inputs: Canada China USA Japan Germany England Italy Brazil
- An example for team inputs: 1 2

##User Stories
- As a user, I want to add arbitrary-sized number of teams to a list of teams.
- As a user, I want to simulate the draw ceremonies before each round of games.
- As a user, I want to be able to simulate the whole process of the knockout stage of a football cup event from the
draw ceremony of the initial input teams until the final champion has been determined.
- As a user, I want to be able to choose between continuing the simulation and quiting it after each round of games.
- As a user, when I select the quit option from the application menu, I want to be reminded to save the candidate teams for next round of knockout games to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my the listOfTeam for next round of
games from file.

##Phase 4: Task 2
**a sample of events occured when running the console-based ui:**    

Thu Nov 25 16:02:16 PST 2021  
Add the candidate teams: Canada France Japan USA  
Thu Nov 25 16:02:16 PST 2021  
The draw is conducted  
Thu Nov 25 16:02:16 PST 2021  
A new game is added to listOfGame  
Thu Nov 25 16:02:16 PST 2021  
Remove the team in listOfTeam  
Thu Nov 25 16:02:16 PST 2021  
Remove the team in listOfTeam  
Thu Nov 25 16:02:16 PST 2021  
A new game is added to listOfGame  
Thu Nov 25 16:02:16 PST 2021  
Remove the team in listOfTeam  
Thu Nov 25 16:02:16 PST 2021  
Remove the team in listOfTeam  
Thu Nov 25 16:02:20 PST 2021  
The winner of a game is determined  
Thu Nov 25 16:02:22 PST 2021   
The winner of a game is determined  
Thu Nov 25 16:02:22 PST 2021  
a penalty kick game is played  
Thu Nov 25 16:02:22 PST 2021  
Remove all the finished games in listOfGame  

**a sample of events occured when running the GUI:**

Thu Nov 25 16:08:46 PST 2021  
Add the candidate teams: Canada USA France China  
Thu Nov 25 16:08:46 PST 2021  
The draw is conducted  
Thu Nov 25 16:08:50 PST 2021  
The listOfTeam is cleared  


##Citation
- My JSON related codes (persistence realization) are modeled from the demo application provided by Prof. Paul Carter.