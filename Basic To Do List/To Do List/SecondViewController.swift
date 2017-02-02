//
//  SecondViewController.swift
//  To Do List
//
//  Created by Tom Miller on 3/16/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import UIKit

var toDoList = [String]()

class SecondViewController: UIViewController {

    
    @IBOutlet weak var item: UITextField!
    
    @IBAction func addItem(sender: AnyObject)
    {
        toDoList.append(item.text!)
        item.text = ""
        NSUserDefaults.standardUserDefaults().setObject(toDoList, forKey: "toDoList")
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldshouldReturn(textField: UITextField) -> Bool {
        item.resignFirstResponder()
        return true
    }
    
}

