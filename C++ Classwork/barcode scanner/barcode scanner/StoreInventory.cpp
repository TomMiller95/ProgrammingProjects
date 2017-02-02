#include "StoreInventory.h"

StoreInventory::StoreInventory(){
    readFromFile();
}

void StoreInventory::endStoreInventory(){
    writeToFile();
}

Item StoreInventory::find(int barcode){
    for(int i = 0; i < inventory.length(); i++){
        if(inventory.at(i).getBarcode()==barcode){
            return inventory.at(i);
        }
    }
    Item nullItem(0, "", 0, 0);
    return nullItem;
}

void StoreInventory::decrementItem(Item item){
    for(int i = 0; i < inventory.length(); i++){
        if(inventory.at(i).getBarcode() == item.getBarcode()){
            Item update = inventory.at(i);
            update.decrementQuantity();
            inventory.setAt(i, update);
        }
    }
}

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

void StoreInventory::writeToFile(){
    std::ofstream outFile("UpdatedInventory.txt", std::ios::out);
    for(int i = 0; i < inventory.length(); i++){
        Item next = inventory.at(i);
        outFile << next;
    }
    outFile.close();
}