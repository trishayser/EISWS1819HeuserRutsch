var express = require('express'),
    app = express(),
    port = process.env.PORT || 3000,
    mongoose = require('mongoose'),
    bodyParser = require('body-parser');

//Port festlegen
var port = process.env.PORT || 3000;

//Models laden
var Entry = require('./api/models/entryModel');
  
// Mongoose Verbindung zu MongoDB
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://tristan:tristan420@ds253203.mlab.com:53203/eisprojekt'); 

//BodyParser
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


//Routes festlegen
var routes = require('./api/routes/routes'); //Import
routes(app); //Register

app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(port);


console.log('REST API Server startet auf dem Port: ' + port);