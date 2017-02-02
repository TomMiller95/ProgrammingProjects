#include <iostream>
#include <vector>
#include <ctype.h>

using namespace std;

int main(int argc, const char * argv[]) {
    string letters [3] = {"c","o","w"};
    string guess;
    string answer [3] = {"*","*","*"};
    bool finished = false;
    bool check = false;
    
    while (finished == false)
    {
        cout << endl;
        cout << answer[0];
        cout << answer[1];
        cout << answer[2];
        cout << endl;
        cout << "what is your guess?" << endl;
        cin >> guess;
        for (int x = 0; x < (sizeof(letters)/sizeof(*letters)); x++)
        {
            if (guess == letters[x])
            {
                answer[x] = guess;
            }
        }
        
        check = true;
        for (int y = 0; y < (sizeof(answer)/sizeof(*answer)); y++)
        {
            if (answer[y] == "*")
            {
                check = false;
            }
        }
        if (check == true)
        {
            finished = true;
            cout << "Correct";
        }
    }
    return 0;
}