//
//  newListItem.swift
//  Flock
//
//  Created by Tom Miller on 10/16/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import Foundation
import UIKit


class newListItem: UIViewController {
    
        
    @IBOutlet var item: UITextField!
        
    @IBAction func addItem(_ sender: AnyObject) {
        toDoList.append(item.text!)
        item.text = ""
        UserDefaults.standard.set(toDoList, forKey: "toDoList")
    }

        
        override func viewDidLoad() {
            super.viewDidLoad()
            // Do any additional setup after loading the view, typically from a nib.
        }
        
        override func didReceiveMemoryWarning() {
            super.didReceiveMemoryWarning()
            // Dispose of any resources that can be recreated.
        }
        
        override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
            self.view.endEditing(true)
        }
        
        func textFieldshouldReturn(_ textField: UITextField) -> Bool {
            item.resignFirstResponder()
            return true
        }
        
}
