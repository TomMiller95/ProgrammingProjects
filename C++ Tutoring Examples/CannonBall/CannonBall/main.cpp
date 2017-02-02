#include <iostream>
# include <cmath>

using namespace std;

int main(int argc, const char * argv[]) {
    int height;
    double gravity = 9.8;
    double velocity;
    
    cout << "What is the height?" << endl;
    cin >> height;
    
    velocity = sqrt(2 * gravity * height);
    
    cout << "Final Velocity: " << velocity;
    
    return 0;
}
