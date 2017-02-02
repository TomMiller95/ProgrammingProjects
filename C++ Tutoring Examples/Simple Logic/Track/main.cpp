#include <iostream>
#include <cmath>
using namespace std;


float a,b;

float getMax(float a, float b);

int main(int argc, const char * argv[]) {
    
    cout << "whats a";
    cin >> a;
    
    cout << "whats b";
    cin >> b;
    
    cout << getMax(a,b);
    
    return 0;
}

float getMax(float a, float b)
{
    if (a>b)
    {
        return a;
    }
    else if (a<b)
    {
        return b;
    }
    else
    {
        cout << "you lose";
    }
    return 0;
}

