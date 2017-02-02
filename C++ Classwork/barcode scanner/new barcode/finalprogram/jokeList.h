//jokeList.h
//Jeffrey Jones, Tom Miller, and Anthony Chesebro
//This class holds a list of jokes and can be called to print a random joke.

#ifndef jokeList_H
#define jokeList_H
#include <string>
#include <iostream>
#include "Vector.h"
#include <fstream>

class jokeList
{
public:
    
    jokeList();
    
    int chooseRandom();
    
    std::string getJoke();
    
    friend std::ostream& operator << (std::ostream& stream, jokeList list);
    
private:
    Vector<std::string> jokes;
    
};

#endif
