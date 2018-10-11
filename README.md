# Battleship Clojure API

A little test drive of https://github.com/metosin/compojure-api which is probably
one of the nicest swagger 

I built a litt

I've been using it as the API for getting a sense of the way client side developers think and work

## Building and Running

At the moment you need lein installed.  So it's just 

    lein ring server-headless

or 

    lein ring server
    
If you want to open a browser. The swagger docs will be at 

    http://localhost:3000/index.html

You can test drive the API. Add a port to the end of the above, if you want it to run under another port. There is a Procfile for running on Heroku.


## Usage

The api is documented with swagger. The basic work flow is:

    /api/init   /*Initializes an empty gameboard for two players 
    /api/place/{player}/{piece}/{direction}  /* Places a piece on the board
    
Once all the pieces are placed on the board, you 
    


## Tips 

The api doesn't keep track of turn taking, or tracking which user is running in the broswer. The 

The entire user state is returned after each call.

The game keeps track of 


## Invitation to Improve

Feel free to improve this.

## License

The MIT License (MIT)

Copyright (c) 2015 Chris Kibble

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
