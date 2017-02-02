#ifndef StoreInventory_H
#define StoreInventory_H
#include "Item.h"
#include "Vector.h"
#include <string>
#include <iostream>
#include <fstream>

class StoreInventory{
    public:
    StoreInventory();
    
    void endStoreInventory();
    
    Item find(int barcode);
    void decrementItem(Item item);
    
    private:
    Vector<Item> inventory;
    void readFromFile();
    void writeToFile();
};

#endif
