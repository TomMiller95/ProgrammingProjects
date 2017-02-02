/**#include "Jokes.h"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <algorithm>

int Jokes::myrandom (int i)
{
    return std::rand()%i;
}
void Jokes::makeJokes ()
{
    int x;
    int num = 0;
    
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
    
    std::cin >> x;
    
    if (x > num)
    {
        srand(time(0));
        std::random_shuffle (jokes.begin(),jokes.end(), myrandom);
        std::random_shuffle(jokes.begin(),jokes.end());
        
        std::cout << jokes[3] << std::endl;
    }
}*/