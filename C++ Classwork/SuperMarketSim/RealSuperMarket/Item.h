//Item.h
//Anthony Chesebro
//Simulates an item in a store.

#ifndef Item_H
#define Item_H

#include <string>
#include <iostream>

class Item
{
    public:
    
    //Constructor
    Item();
    Item(int inBarcode, std::string inName, int inPrice, int inQuantity);
    
    //Barcode
    void setBarcode(int inBarcode);
    int getBarcode();
    
    //Name
    void setName(std::string inName);
    std::string getName();
    
    //Price
    void setPrice(int inPrice);
    int getPrice();
    
    //Quantity
    void setQuantity(int newQuantity);
    int getQuantity();
    void incrementQuantity();
    void incrementQuantity(int inc);
    void decrementQuantity();
    void decrementQuantity(int dec);
    
    //Match
    bool isSameAs(int otherBarcode);
    
    //Equality operators
    friend bool operator == (Item& item1, Item& item2);
    friend bool operator != (Item& item1, Item& item2);
    
    //Stream operators
    friend std::ostream& operator << (std::ostream& stream, Item& item);
    friend std::istream& operator >>  (std::istream& stream, Item& item);
    
    private:
    //Stores the attributes of the item
    int barcode;
    std::string name;
    int price;
    int quantity;
    
};

#endif
