//main.cpp
//Tom Miller
//This class runs the entire receipt printing program.  It is a solution to Programming Problem #6 of Chapter 11.
//NOTE:  The Item class is structured in such a way that the restrictions placed upon the original problem (e.g. 2 digit barcode, 30 character description, etc.) can be handled.  It is possible, however, to have as many characters in each portion as the user wishes without the program crashing.

#include <iostream>
#include <fstream>
#include "Vector.h"
#include "Item.h"
#include "jokeList.h"
#include "StoreInventory.h"


int main(int argc, const char * argv[]) {
    
    jokeList j;
    StoreInventory inventory;
    
    Vector<Item> receipt;
    std::string inBarcode;
    std::string response;
    std::string fileName = "Receipt.txt";
    int total = 0;
    Item nullItem(0, "", 0, 0);
    
    while (true){
        std::cout << "Enter barcode...Enter 0 to exit" << std::endl;
        std::cin >> inBarcode;
        int barcode = atoi(inBarcode.c_str());
        
        if (barcode <= 0){
           //tm
            if (barcode < 0)
            {
            std::cout << "Invalid input. Program ended.\n";
            }
            
            break;
        } else {
            Item i = inventory.find(barcode);
            
            //tm
            if (i.getBarcode() != nullItem.getBarcode() && i.getQuantity() == 0)
            {
                std::cout << i.getName() << " is out of stock!\n";
            }
            
            
            if(i.getBarcode() != nullItem.getBarcode() && i.getQuantity() != 0){
                i.setQuantity(1);
                inventory.decrementItem(i);
                receipt.append(i);
                total += i.getPrice();
            }
        }
    }
    
    std::ofstream outFile(fileName, std::ios::out);
    for(int i = 0; i <= receipt.length(); i++){
        Item next = receipt.at(i);
        outFile << next;
        outFile << j.getJoke() << std::endl;
    }
    outFile << "\nYour total is: $" << total << std::endl;
    outFile << "Thank you for shopping at BlueJ's!";
    outFile.close();
    
    inventory.endStoreInventory();
    std::cout << "The receipt has been printed!" << std::endl;
    return 0;
}