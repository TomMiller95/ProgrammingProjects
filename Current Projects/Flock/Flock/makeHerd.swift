//
//  makeHerd.swift
//  Flock
//
//  Created by Tom Miller on 10/29/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import Foundation
import UIKit

class makeHerd: UIViewController {
    
    @IBOutlet var titleTxt: UITextField!
    @IBOutlet var descriptionTxt: UITextView!
    
    
    @IBAction func createHerd(_ sender: AnyObject) {
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpMakeHerd.php")!)
        request.httpMethod = "POST"
        let postString = "a=\(GLOBusername)&b=\(GLOBid)&c=\(titleTxt.text!)&d=\(descriptionTxt.text!)"
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
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
