#ifndef jokeList_H
#define jokeList_H
#include <string>
#include <iostream>
#include "Vector.h"
#include <fstream>
#include <time.h>

class jokeList
{
public:
    
    jokeList();
    
    int chooseRandom();
    
    void addJokes(std::string fileName);
    
private:
    Vector<std::string> jokes;
    
};

#endif
