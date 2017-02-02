//
//  MainMenu.swift
//  Flock
//
//  Created by Tom Miller on 10/15/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import Foundation
import UIKit

var toDoList = [String]()

class MainMenu: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet var toDoListTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //STARTED WORKING HERE
        populateTable()
                herdList.append("herd one")
                UserDefaults.standard.set(herdList, forKey: "herdList")
        //ENDED WORK HERE
        if UserDefaults.standard.object(forKey: "toDoListTable") != nil
        {
            toDoList = UserDefaults.standard.object(forKey: "toDoListTable") as! [String]
        }
        //down for testing work
        if UserDefaults.standard.object(forKey: "toDoList") != nil
        {
            toDoList = UserDefaults.standard.object(forKey: "toDoList") as! [String]
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    
    
    func populateTable()
    {
        //probably loop through with each herd
        var request = URLRequest(url:URL(string: "https://flock.000webhostapp.com/phpGetEvents.php")!)
        request.httpMethod = "POST"
        var tableNameTest = "TestTable"
        let postString = "a=\(tableNameTest)"
        request.httpBody = postString.data(using: String.Encoding.utf8)
        let task = URLSession.shared.dataTask(with: request, completionHandler: {
            data, response, error in
            if error != nil {
                print("error=\(error)")
                return
            }
        
            let responseString = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
            let list = responseString!.components(separatedBy: " ")
            
            for item in 0...list.count-1
            {
               print(String(list[item]) + ",")
            }
        }) 
        task.resume()
    }
    
    

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return toDoList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = UITableViewCell(style: UITableViewCellStyle.default, reuseIdentifier: "Cell")
        cell.textLabel?.text = toDoList[indexPath.row]
        
        return cell
    }
    
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath)
    {
        if editingStyle == UITableViewCellEditingStyle.delete
        {
            toDoList.remove(at: indexPath.row)
            
            UserDefaults.standard.set(toDoList, forKey: "toDoList")
            
            toDoListTable.reloadData()
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        toDoListTable.reloadData()
    }

    
}
