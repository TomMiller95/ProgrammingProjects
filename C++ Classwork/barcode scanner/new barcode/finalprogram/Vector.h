//Vector.h
//Anthony Chesebro (with a lot of help from the textbook and handouts from Stanford University)
//This defines a more user-friendly vector class than the standard C++ vector.  The main difference between the two is that this class is based on arrays, while the standard one is based on linked lists.
//There is no .cpp file with this header because of compilation issues.  The easiest way to fix the problem was to define all of the functions in the header class.


#ifndef Vector_H
#define Vector_H

template <typename ElemType>
class Vector
{
    public:
    Vector(){
        size = 10;
        used = 0;
        vector = new ElemType[size];
    }
    ~Vector(){
        delete[] vector;
    }
    
    void append(ElemType input){
        if(used < size){
            vector[used] = input;
            used++;
        } else {
            createNewStorage();
            vector[used] = input;
            used++;
        }
    }
    void remove(int position){
        if(positionExists(position)){
            for(int i = position + 1; i < size; i++){
                vector[i-1] = vector[i];
            }
        }
        used--;
    }
    
    void insertAt(int position, ElemType input){
        append(vector[size-1]);
        for(int i = size - 1; i > position; i--){
            vector[i] = vector[i-1];
        }
        vector[position] = input;
        used++;
    }
    
    void setAt(int position, ElemType input){
        if(positionExists(position)){
            vector[position] = input;
        }
    }
    
    int find(ElemType input){
        for(int i = 0; i < size; i++){
            if(vector[i] == input){
                return i;
            }
        }
        return -1;
    }
    
    ElemType at(int position){
        if(positionExists(position)){
            return vector[position];
        }
        ElemType myObject;
        return myObject;
    }
    
    int length(){
        return used - 1;
    }
    
    private:
    ElemType *vector;
    int size, used;
    void createNewStorage(){
        ElemType *bigger = new ElemType[size*2];
        
        for(int i = 0; i < size; i++){
            bigger[i] = vector[i];
        }
        
        delete[] vector;
        vector = bigger;
        size *= 2;
    }
    
    bool positionExists(int position){
        return position < size;
    }
    
    
};

#endif
