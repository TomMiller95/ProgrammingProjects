#include "jokeList.h"


jokeList::jokeList(){
    jokes.append("bluej's are awesome!");
    jokes.append("I like cookies");
    jokes.append("lets go to bluej's'!");
    jokes.append("where's the java?");
    jokes.append("clean up in aisle 7!");
    jokes.append("pls go home");
    jokes.append("r u even here?");
    jokes.append("this is nice");
    jokes.append("what is this even?");
    jokes.append("these went spoiled 3 days ago!");
}

void jokeList::addJokes(std::string fileName)
{
    srand ( time(NULL) );
    
    std::cout << jokes.at((std::rand()%10)) << std::endl;
    std::ofstream appFile(fileName, std::ios::app);
    appFile << jokes.at((std::rand()%10)) << std::endl;
}