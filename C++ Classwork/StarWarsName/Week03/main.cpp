#include <iostream>
using namespace std;

void generateStarWarsName(string first, string last, string mom, string place)
{
    cout << last[0] << last[1] << last[2] << first[0] << first[1] << " " << mom[0] << mom[1] << place[0] << place[1] << place[2] ;
}

int main(int argc, const char * argv[])
{
    string fName;
    string lName;
    string mName;
    string cName;
    
    cout << "What is your first name?" << endl;
    cin >> fName;
    cout << "What is your last name?" << endl;
    cin >> lName;
    cout << "What is your mother's maiden name?" << endl;
    cin >> mName;
    cout << "What city or town were you born in?" << endl;
    cin >> cName;
    
    generateStarWarsName(fName, lName, mName, cName);
    return 0;
}