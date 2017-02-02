//jokeList.cpp
//Main Programmer: Jeffrey Jones
//Assistant Programmers:  Tom Miller and Anthony Chesebro
//This class holds a list of jokes and can be called to print a random joke.

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
    srand (time(NULL));
}

std::string jokeList::getJoke()
{
    return jokes.at((std::rand()%10));
}

std::ostream& operator << (std::ostream& stream, jokeList list)
{
    stream << list.getJoke();
    return stream;
}