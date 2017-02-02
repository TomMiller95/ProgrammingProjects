//Item.cpp
//Anthony Chesebro
//Simulates an item in a store.

#include "Item.h"

//Bogus constructor with no parameters
Item::Item()
{
    barcode = 1010101;
    name = "SURPRISE!";
    price = 9876;
    quantity = 12345;
}

//Real constructor with parameters
Item::Item(int inBarcode, std::string inName, int inPrice, int inQuantity)
{
    barcode = inBarcode;
    name = inName;
    price = inPrice;
    quantity = inQuantity;
}

//Constructor with string parameters
Item::Item(std::string line){
    instantiateFromString(line);
}

//Getter and setter functions for barcode
void Item::setBarcode(int inBarcode)
{
    barcode = inBarcode;
}
int Item::getBarcode()
{
    return barcode;
}

//Getter and setter functions for name
void Item::setName(std::string inName)
{
    name = inName;
}
std::string Item::getName()
{
    return name;
}

//Getter and setter functions for price
void Item::setPrice(int inPrice)
{
    price = inPrice;
}
int Item::getPrice()
{
    return price;
}

//Getter and setter functions for quantity
void Item::setQuantity(int newQuantity)
{
    quantity = newQuantity;
}
int Item::getQuantity()
{
    return quantity;
}

//Incrementer functions for quantity
void Item::incrementQuantity()
{
    quantity++;
}
void Item::incrementQuantity(int inc)
{
    quantity += inc;
}

//Decrementer functions for quantity
void Item::decrementQuantity()
{
    quantity--;
}
void Item::decrementQuantity(int dec)
{
    quantity -= dec;
}

//Check for match function
bool Item::isSameAs(int otherBarcode)
{
    if (barcode == otherBarcode){
        return true;
    } else {
        return false;
    }
}
bool operator == (Item& item1, Item& item2)
{
    return item1.getBarcode() == item2.getBarcode();
}
bool operator != (Item& item1, Item& item2)
{
    return item1.getBarcode() != item2.getBarcode();
}

std::ostream& operator << (std::ostream& stream, Item& item)
{
    stream << item.getBarcode() << " " << item.getName() << " $" << item.getPrice() << " qty. " << item.getQuantity() << "\n";
    return stream;
}

std::istream& operator >> (std::istream& stream, Item& item)
{
    std::string nextLine;
    getline(stream, nextLine);
    
    item.setBarcode(atoi(nextLine.substr(0, nextLine.find(" ")).c_str()));
    nextLine.erase(0, nextLine.find(" ")+1);
    
    item.setName(nextLine.substr(0, nextLine.find(" ")));
    nextLine.erase(0, nextLine.find(" ") + 2);
    
    item.setPrice(atoi(nextLine.substr(0, nextLine.find(" ")).c_str()));
    nextLine.erase(0, nextLine.find(" ") + 5);
    
    item.setQuantity(atoi(nextLine.c_str()));
    
    return stream;
}

void Item::instantiateFromString(std::string nextLine){
    setBarcode(atoi(nextLine.substr(0, nextLine.find(" ")).c_str()));
    nextLine.erase(0, nextLine.find(" ")+1);
    
    setName(nextLine.substr(0, nextLine.find(" ")));
    nextLine.erase(0, nextLine.find(" ") + 2);
    
    setPrice(atoi(nextLine.substr(0, nextLine.find(" ")).c_str()));
    nextLine.erase(0, nextLine.find(" ") + 5);
    
    setQuantity(atoi(nextLine.c_str()));
}

