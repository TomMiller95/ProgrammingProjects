#include <iostream>
#include <fstream>
#include <vector>
#include "Item.h"
#include "jokeList.h"


int main(int argc, const char * argv[]) {

    jokeList j;
    
    std::vector<Item> receipt;
    int barcode;
    bool notDone = true;
    std::string response;
    std::string fileName = "Receipt.txt";
    
    while (notDone){
        std::cout << "Enter barcode...Enter 0 to exit" << std::endl;
        std::cin >> barcode;
        
        if (barcode <= 0)
        {
        
            notDone = false;
            Item i(10,"string", 100, 9);
        //Item i = //get from inventory
        receipt.push_back(i);
        }
        else
        {
            notDone = false;
        }
    }
    j.addJokes(fileName);
    
    for (std::vector<Item>::iterator it = receipt.begin(); it != receipt.end(); ++it)
    {
        //* means pointer
        std::cout << *it;
        std::ofstream outFile(fileName, std::ios::out);
        outFile << *it << std::endl;
        outFile << "Thank you for shopping at BlueJ's!" << std::endl;
        //j.addJokes("Receipt.txt");
        outFile.close();
    }
    return 0;
}