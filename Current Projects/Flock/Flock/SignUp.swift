//
//  SignUp.swift
//  Flock
//
//  Created by Tom Miller on 10/15/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import Foundation
import UIKit

class SignUp: UIViewController, UITextFieldDelegate {
    
    @IBOutlet var usernameTxt: UITextField!
    @IBOutlet var emailTxt: UITextField!
    @IBOutlet var passwordTxt: UITextField!
    
    @IBOutlet var badUserLabel: UILabel!
    @IBOutlet var badEmailLabel: UILabel!
    
    //Test Stuff
    @IBOutlet var pageInfo: UILabel!
    
    @IBAction func displayPage(_ sender: AnyObject) {
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpMakeHerd.php")!)
        request.httpMethod = "POST"
        let postString = "a=\(usernameTxt.text!)&b=\(passwordTxt.text!)"
        request.httpBody = postString.data(using: String.Encoding.utf8)
        
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            
            if error != nil {
                print("error=\(error)")
                return
            }
            
            //print("response = \(response)")
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            //print("responseString = \(responseString)")
            self.cleanUp(responseString!)
        }) 
        task.resume()
    }
    
    
    
    /**
     Probably don't need to clean it up at all...
    */
    func cleanUp(_ input: NSString)
    {
        print("Input: " + (input as String))
    }
    //End of test stuff

    
    @IBAction func submitInfo(_ sender: AnyObject) {
        if (checkForDoubles() == true)
        {
            /*let request = NSMutableURLRequest(URL: NSURL(string: "https://flock.000webhostapp.com/phpStore.php")!)
            request.HTTPMethod = "POST"
            let postString = "a=\(usernameTxt.text!)&b=\(emailTxt.text!)&c=\(passwordTxt.text!)"
            request.HTTPBody = postString.dataUsingEncoding(NSUTF8StringEncoding)
        
            let task = NSURLSession.sharedSession().dataTaskWithRequest(request) {
                data, response, error in
            
                if error != nil {
                    print("error=\(error)")
                    return
                }
            
                print("response = \(response)")
            
                let responseString = NSString(data: data!, encoding: NSUTF8StringEncoding)
                print("responseString = \(responseString)")
            }
        task.resume()*/
        }
    }
    
    
    /**
    Created this function because apparently the if statements in checkForDoubles don't register until
     after the return value is returned...
    */
    func addToDB()
    {
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpMakeHerd.php")!)
        
        request.httpMethod = "POST"
        let postString = "a=\(usernameTxt.text!)&b=\(emailTxt.text!)&c=\(passwordTxt.text!)"
        request.httpBody = postString.data(using: String.Encoding.utf8)
        
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            
            if error != nil {
                print("error=\(error)")
                return
            }
            
            print("response = \(response)")
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            print("responseString = \(responseString)")
        }) 
        task.resume()
        
        //GOES TO NEXT PAGE
        let vc : AnyObject! = self.storyboard!.instantiateViewController(withIdentifier: "MainMenu")
        self.show(vc as! UIViewController, sender: vc)
        //
    }
    
    
    
    func getID() -> String
    {
        var output:String = "Nope there was an error in SignUp.swift in the getID() function"
        
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpMakeHerd.php")!)
        print("A")
        request.httpMethod = "POST"
        let postString = "a=\(usernameTxt.text!)&b=\(emailTxt.text!)"
        request.httpBody = postString.data(using: String.Encoding.utf8)
        print("B")
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            print("C")
            if error != nil
            {
                print("error=\(error)")
                return
            }
            print("D")
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            print("<" + String(describing: responseString) + ">")
            output = String(describing: responseString)
                   })        

        task.resume()
        return output
    }
    
    
    
    func checkForDoubles() -> Bool
    {
        var goodAccount = false
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpSignUp.php")!)
        request.httpMethod = "POST"
        let postString = "a=\(usernameTxt.text!)&b=\(emailTxt.text!)"
        request.httpBody = postString.data(using: String.Encoding.utf8)
        
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            
            if error != nil {
                print("error=\(error)")
                return
            }
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            
            if (responseString == "accepted")
            {
               //Successful
                goodAccount = true
                self.addToDB()
                GLOBusername = self.usernameTxt.text!
                GLOBemail = self.emailTxt.text!
                GLOBid = self.getID()
                print(GLOBid + " HEEEEEERRRRREEEE")
            }
            else if (responseString == "takenUser")
            {
                self.badUserLabel.text = "Sorry, that username is already taken."
                goodAccount = false
            }
            else if (responseString == "takenEmail")
            {
                self.badEmailLabel.text = "Sorry, that email address is already taken."
                goodAccount = false
            }
            else
            {
                print(responseString)
                print("Wow, that is a crazy error...maybe just restart it and hope it works.")
                goodAccount = false
            }
        })    //Im gonna guess that moving this parenthesis above the IF statements would fix the delay, and function calling issue...i've tweaked too much though so im not gonna even try it.
        task.resume()
        return goodAccount
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
        self.emailTxt.delegate = self
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
