//
//  ViewController.swift
//  Weather App
//
//  Created by Tom Miller on 3/26/16.
//  Copyright © 2016 tomillerable. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var cityText: UITextField!
    @IBOutlet weak var outputLabel: UILabel!
    
    @IBAction func findWeather(_ sender: AnyObject) {
        
        let url = URL(string: "http://www.weather-forecast.com/locations/" + cityText.text!.replacingOccurrences(of: " ", with: "-") + "/forecasts/latest")!

        let task = URLSession.shared.dataTask(with: url, completionHandler: { (data, response, error) -> Void in
            
            if let urlContent = data
            {
                let webContent = NSString(data: urlContent, encoding: String.Encoding.utf8.rawValue)
                
                let websiteArray = webContent!.components(separatedBy: "3 Day Weather Forecast Summary:</b><span class=\"read-more-small\"><span class=\"read-more-content\"> <span class=\"phrase\">")
                
                if websiteArray.count > 1
                {
                    let weatherArray = websiteArray[1].components(separatedBy: "</span>")
                    
                    if weatherArray.count > 0
                    {
                        let weatherSummary = weatherArray[0].replacingOccurrences(of: "&deg;", with: "º")
                        DispatchQueue.main.async(execute: { () -> Void in
                            self.outputLabel.text = weatherSummary
                        })
                    }
                }
            }
        }) 
        task.resume()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
           }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}
