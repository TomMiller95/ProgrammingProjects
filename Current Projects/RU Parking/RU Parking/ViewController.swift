//
//  ViewController.swift
//  RU Parking
//
//  Created by Tom Miller on 2/1/17.
//  Copyright © 2017 tomillerable. All rights reserved.
//

import Foundation
import UIKit

class ViewController: UIViewController {

    
    var timer : Timer!
    
    @IBOutlet var outputTest: UILabel!
    
    
    func testGettingData()
    {
        var request = URLRequest(url:URL(string: "https://ruparking.000webhostapp.com/RUCheckCars.php")!)
        request.httpMethod = "POST"
        let postString = "nothing, probably not even needed..."
        request.httpBody = postString.data(using: String.Encoding.utf8)
        
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            if error != nil {
                print("error=\(error)")
                return
            }
            
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            
            print(responseString)
            
            self.outputTest.text = responseString as String?
    }
    )}
    
    
    func timerActions()
    {
        //Determines when to change to the next players turn.
        testGettingData()
    }
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        timer = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(timerActions), userInfo: nil, repeats: true) //Starts the timer
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

