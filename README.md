#Schedule maker and scoreboard for the knockout stage of a football cup event

**What will the application do?**

The application is designed to simulate the whole process of the knockout stage of a football cup event. The user can enter
arbitrary-sized number of teams to initialize the program. Notice that the number of teams entered by the user must 
equal to *2<sup>n</sup> (n = 1, 2, 3, ... )*. After receiving the user's inputs, the application will simulate the draw 
ceremony. In real world, the draw ceremony means randomly pairing up teams from all qualified teams to generate games 
for the next round. Hence, randomization was introduced in this program to simulate this process. Notice that the draw
ceremonies will be held before each round of games. After the draw ceremony, the program will show the specific 
matchups on the screen to the user. The user need to enter the game results for each game in this round of games. The 
program will keep track of the winners of each game and will store the winners in this round in a list. The winners in a
round of games will be the participant teams for the next round of games. The losers from the games are eliminated from 
this World Cup event and not longer being considered. Then, the program will proceed to the next round of games. After 
several rounds of games, the final champion of this football cup event will be determined. Since in each round of games, the 
functionality is similar, recursive calls (natural recursion and mutual recursion) were constructed. Moreover, when the
football cup event has actually started in real world, this app can serve as a scoreboard to keep track of the game results
and goal information on the purpose of statistics analysis.

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
cup event, thus the number of teams initially entered by the user must be a multiple of 2.

##User Stories
- As a user, I want to add a new team to a list of teams
- As a user, I want to add a new game to a list of games
- As a user, I want to remove a team from a list of teams
- As a user, I want to remove a game from a list of games
- As a user, I want to remove all the games from an existing list of games. 
- As a user, I want to simulate the draw ceremonies.
- As a user, I want to be able to simulate the whole process of the knockout stage of a football cup event or to keep 
track of the scores of the games have finished in previous rounds. 
Specifically, the user can input the result for each game in each round until the final champion has been determined