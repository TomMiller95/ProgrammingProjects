#include "jokeList.h"
#include <iostream>
#include <vector>
#include <fstream>



void jokeList::addJokes(std::string fileName)
{
    srand ( time(NULL) );
    std::vector<std::string> jokes;
    
    jokes.push_back("bluej's are awesome!");
    jokes.push_back("I like cookies");
    jokes.push_back("lets go to bluej's'!");
    jokes.push_back("where's the java?");
    jokes.push_back("clean up in aisle 7!");
    jokes.push_back("pls go home");
    jokes.push_back("r u even here?");
    jokes.push_back("this is nice");
    jokes.push_back("what is this even?");
    jokes.push_back("these went spoiled 3 days ago!");
    
    std::cout << jokes[(std::rand()%10)] << std::endl;

    std::ofstream appFile(fileName, std::ios::app);
    appFile << jokes[(std::rand()%10)] << std::endl;
    appFile.close();
}