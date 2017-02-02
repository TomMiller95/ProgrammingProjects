#include "StoreInventory.h"

//Instantiates an inventory from a text file
StoreInventory::StoreInventory(){
    readFromFile();
}

//Writes the new inventory to a text file
void StoreInventory::endStoreInventory(){
    writeToFile();
}

//Finds an item in the inventory
Item StoreInventory::find(int barcode){
    for(int i = 0; i < inventory.length(); i++){
        if(inventory.at(i).getBarcode()==barcode){
            return inventory.at(i);
        }
    }
    Item nullItem(0, "", 0, 0);
    return nullItem;
}

//Decrements the quantity of an item in the inventory
void StoreInventory::decrementItem(Item item){
    for(int i = 0; i < inventory.length(); i++){
        if(inventory.at(i).getBarcode() == item.getBarcode()){
            Item update = inventory.at(i);
            update.decrementQuantity();
            inventory.setAt(i, update);
        }
    }
}

//Reads from a file
void StoreInventory::readFromFile(){
    std::string line;
    std::ifstream inFile ("storeInventory.txt");
    if (inFile.is_open())
    {
        while ( getline (inFile,line) )
        {
            Item nextItem(line);
            inventory.append(nextItem);
        }
        inFile.close();
    }
}

//Writes to a file
void StoreInventory::writeToFile(){
    std::ofstream outFile("UpdatedInventory.txt", std::ios::out);
    for(int i = 0; i <= inventory.length(); i++){
        Item next = inventory.at(i);
        outFile << next;
    }
    outFile.close();
}