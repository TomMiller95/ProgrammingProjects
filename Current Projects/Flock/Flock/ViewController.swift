//
//  ViewController.swift
//  Flock
//
//  Created by Tom Miller on 10/15/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBAction func esayPass(_ sender: Any) {
        //sends the user to another view controller
        let vc : AnyObject! = self.storyboard!.instantiateViewController(withIdentifier: "MainMenu")
        self.show(vc as! UIViewController, sender: vc)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

