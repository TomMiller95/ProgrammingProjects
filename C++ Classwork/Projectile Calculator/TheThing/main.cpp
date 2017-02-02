#include <iostream>
#include <cmath>
using namespace std;

double G = 32.17;

void instruct()
{
    cout << "This program will compute the duration of a projectile's flight and it's height above the ground when it reaches the target." << endl;
}


void displayResults(double t, double h)
{
    cout << "Time: " << t << endl;
    cout << "Height: " << h;
}

void calcHeight(double time, double t,  double d, double v)
{
    double height;
    height = v * (sin(t)) * time - ((G*pow(time, 2))/2);
    displayResults(time, height);
}

void calcTime(double t, double d, double v)
{
    double time;
    time = d / (v*cos(t));
    calcHeight(time, t, d, v);
}

void getInputs()
{
    double theta;
    double distance;
    double velocity;
    
    cout << "Theta?" << endl;
    cin >> theta;
    cout << "Distance?" << endl;
    cin >> distance;
    cout << "Velocity?" << endl;
    cin >> velocity;
    
    calcTime(theta, distance, velocity);
}

int main(int argc, const char * argv[]) {
    instruct();
    getInputs();
    return 0;
}
