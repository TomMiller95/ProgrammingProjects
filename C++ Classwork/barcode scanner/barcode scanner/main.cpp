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
    
    while (true){
        std::cout << "Enter barcode...Enter 0 to exit" << std::endl;
        std::cin >> inBarcode;
        int barcode = atoi(inBarcode.c_str());
        
        if (barcode <= 0){
            break;
        } else {
            Item i = inventory.find(barcode);
            i.setQuantity(1);
            inventory.decrementItem(i);
            receipt.append(i);
        }
    }
    
    j.addJokes(fileName);
    
    std::ofstream outFile(fileName, std::ios::out);
    for(int i = 0; i <= receipt.length(); i++){
        Item next = receipt.at(i);
        outFile << next;
        j.addJokes(fileName);
    }
    outFile.close();
    
    inventory.endStoreInventory();
    return 0;
}