//
//  Login.swift
//  Flock
//
//  Created by Tom Miller on 10/15/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import Foundation
import UIKit

var GLOBusername = ""
var GLOBemail = ""
var GLOBid = ""

class Login: UIViewController, UITextFieldDelegate {
    
    @IBOutlet var usernameTxt: UITextField!
    @IBOutlet var passwordTxt: UITextField!
    
    //Checks to see if the password and username match.
    //  If they do, then log the user into the account.
    //  If not, print out error message
    @IBAction func loginButton(_ sender: AnyObject) {
        verify()
    }
    
    
    
    func verify() -> Bool
    {
        var verified = false
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpSignIn.php")!)
        request.httpMethod = "POST"
        let postString = "a=\(usernameTxt.text!)&b=\(passwordTxt.text!)"
        request.httpBody = postString.data(using: String.Encoding.utf8)
        
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            if error != nil {
                print("error=\(error)")
                return
            }
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            var responseOne = responseString
            //print(responseOne!.substringWithRange(NSRange(location: 0, length: (responseOne as! String).characters.count)))
            
            if (responseOne!.substring(with: NSRange(location: 0, length: 8)) == "verified")
            {
                //Successful
                verified = true
                //print(responseOne)
                var firstComma = -1
                var secComma = -1
                var thirdComma = -1
                //Sets global vars
                for i in 0...(responseOne as! String).characters.count-1
                {
                    //print("In the for loop")
                    if (responseOne!.substring(with: NSRange(location: i, length: 1)) == ",")
                    {
                        if (firstComma == -1)
                        {
                            firstComma = i
                            //print("Found first comma")
                        }
                        else if (secComma == -1)
                        {
                            secComma = i
                            //print("Found second comma")
                        }
                        else if (thirdComma == -1)
                        {
                            thirdComma = i
                            //print("Found third comma")
                        }
                    }
                    else{
                        //print(responseOne!.substringWithRange(NSRange(location: i, length: 1)))
                    }
                }
                
                GLOBusername = responseOne!.substring(with: NSRange(location: 9, length: secComma - 9))
                //print("user set to " + GLOBusername)
                GLOBemail = responseOne!.substring(with: NSRange(location: secComma+1, length: thirdComma - secComma - 1))
                //print("email set to " + GLOBemail)
                GLOBid = responseOne!.substring(with: NSRange(location: thirdComma+1, length: (String(describing: responseOne).characters.count - 11) - thirdComma))
                //print("ID set to " + GLOBid)
                
                //print(GLOBusername + " " + GLOBemail + " " + GLOBid)
                
                
                //sends the user to another view controller
                let vc : AnyObject! = self.storyboard!.instantiateViewController(withIdentifier: "MainMenu")
                self.show(vc as! UIViewController, sender: vc)
            }
            else if (responseString == "not verified")
            {
                verified = false
            }
            else
            {
                verified = false
            }
        })    //Im gonna guess that moving this parenthesis above the IF statements would fix the delay, and function calling issue...i've tweaked too much though so im not gonna even try it.
        task.resume()
        return verified
    }
    
    
    
    
    //IDK, this should help remove the keyboard I think
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(_ textField: UITextField!) -> Bool
    {
        textField.resignFirstResponder()
        return true
    }
    //
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //The next lines (self.---.delegate = self) make hitting RETURN hide the keyboard.
        self.usernameTxt.delegate = self
        self.passwordTxt.delegate = self
        //self.emailTxt.delegate = self
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}
