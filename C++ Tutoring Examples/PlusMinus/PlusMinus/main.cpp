#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int getRand();
int processNumber();

int main(int argc, const char * argv[]) {
    srand(time(NULL));
    int plusCount;
    int minusCount;
    int sum;
    int num;
    int answers = 0;
    
    while (answers < 10)
    {
        answers++;
        num = processNumber();
        if (num == 1)
        {
            plusCount++;
        }
        else if (num == -1)
        {
            minusCount++;
        }
    
        sum = plusCount - minusCount;
        cout << "Answer: " << sum << endl;
    }

    return 0;
}

int processNumber()
{
    int num = getRand();
    
    if (num > 0)
    {
        return 1;
    }
    else if (num <= 0)
    {
        return -1;
    }
    return 0;
}

int getRand()
{
    int num = rand()%20 -10;
    return num;
}