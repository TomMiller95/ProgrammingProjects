import urllib2

from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():

	class AppURLopener(urllib.request.FancyURLopener):
    		version = "App/1.7"

	urllib._urlopener = AppURLopener()
        return "Hello World!"


if __name__ == "__main__":
    app.run()