'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EntrySchema = new Schema({
"id": Number,
"user": String,
"date": String,
"title": String,
"text": String,
"body": {
  "route": {
    "start": {
      "lat": String,
      "lng": String
    },
    "destination": {
      "lat": String,
      "lng": String
    }
  },
  "period": {
    "start":String,
    "end": String
  },
  "obstacles": {
    "haveTransporter" : Boolean,
    "driveTransporter": Boolean,
    "canMontate": Boolean,
    "canInstall": Boolean,
    "canDischarge": Boolean
  },
  "notices": String
}
});

module.exports = mongoose.model('Entry', EntrySchema);