//
//  createHerdEvent.swift
//  Flock
//
//  Created by Tom Miller on 11/23/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import Foundation
import UIKit


var herdList = [String]()

class createHerdEvent: UIViewController, UITableViewDelegate {
    
    @IBOutlet var eventName: UITextField!
    @IBOutlet var eventDescription: UITextView!
    
    
    func loadHerds()
    {
        herdList.append("herd one")
        UserDefaults.standard.set(herdList, forKey: "herdList")
    }
    
    @IBAction func createEvent(_ sender: Any) {
        /**
        LAST TIME I WORKED ON THIS I ADDED THE ABILITY TO ADD EVENTS TO HERDS. THE DATE DOES NOT GO THROUGH AND IT ONLY ADDS IT TO ONE HERD, BUT PROGRESS IS PROGRESS I GUESS
        **/
 
        var herdTitle = "TestTable"
        
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpMakeHerdEvent.php")!)
        request.httpMethod = "POST"
        let postString = "a=\(GLOBusername)&b=\(herdTitle)&c=\(eventName.text!)&d=\("01")&e=\(eventDescription.text!)"
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
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return herdList.count
    }
    
   func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let eventCell = UITableViewCell(style: UITableViewCellStyle.default, reuseIdentifier: "eventMakingCell")
        eventCell.textLabel?.text = "TEST"//herdList[indexPath.row]
        print (herdList[0] + " HERE ")
        return eventCell
        
        //**PROBLEM**
        // It seems to think that the cell is NIL, so I have to 
        // figure out why it is not reading any data into it
        //    I believe I checked that the array is being filled, but maybe
        //    check just incase.   
        //         UPDATE-- I did check and the array and it was filled
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
        
        loadHerds()
        UserDefaults.standard.set(herdList, forKey: "herdList")
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}
