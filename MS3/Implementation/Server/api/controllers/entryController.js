'use strict';


var mongoose = require('mongoose'),
    Entry = mongoose.model('Entry'),
    request = require('request');


var ghApiKey = "fb03d204-d685-4044-a463-d57207621892";
var ghURL = "https://graphhopper.com/api/1/geocode";


exports.list_entrys = function(req, res) {
  Entry.find({}, function(err, entry) {
    if (err)
      res.send(err);
    res.json(entry);
  });
};


exports.create_entry = function(req, res) {
  var url1 = ghURL + "?q=" + req.body.route.start.text + "&locale=de&debug=true&key=" + ghApiKey;
  url1 = encodeURI(url1);
  console.log(url1);
  request(url1, { json: true }, (err1, res1, body1) => {
    if (err1) console.log(err1);
    console.log(body1);
    req.body.route.start.lat = body1.hits[0].point.lat;
    req.body.route.start.lng = body1.hits[0].point.lng;
    var url2 = ghURL + "?q=" + req.body.route.destination.text + "&locale=de&debug=true&key=" + ghApiKey;
    url2 = encodeURI(url2);
    request(url2, { json: true }, (err2, res2, body2) => {
      if (err2) console.log(err2);
      req.body.route.destination.lat = body2.hits[0].point.lat;
      req.body.route.destination.lng = body2.hits[0].point.lng;
      var new_entry = new Entry(req.body);
      new_entry.save(function(err, entry) {
        if (err)
          res.send(err);
        res.json(entry);
      });

    });
  });

};


exports.read_entry = function(req, res) {
  Entry.findById({_id: req.params.entryId}, function(err, entry) {
    if (err)
      res.send(err);
    res.json(entry);
  });
};


exports.update_entry = function(req, res) {
  Entry.findOneAndUpdate({_id: req.params.entryId}, req.body, {new: true}, function(err, entry) {
    if (err)
      res.send(err);
    res.json(entry);
  });
};


exports.delete_entry = function(req, res) {
  Entry.remove({
    _id: req.params.entryId
  }, function(err, entry) {
    if (err)
      res.send(err);
    res.json({ message: 'Eintrag wurde gelöscht' });
  });
};
