'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EntrySchema = new Schema({
 "id": String,
 "userID": String,
 "date": String,
 "title": String,
 "route": {
   "start": {
     "lat": String,
     "lng": String,
     "text": String
   },
   "destination": {
     "lat": String,
     "lng": String,
     "text": String
   }
 },
 "period": {
   "start": String,
   "end": String
 },
 "needObstacles": {
   "haveTransporter": Boolean,
   "driveTransporter": Boolean,
   "canMontate": Boolean,
   "canInstall": Boolean,
   "canDischarge": Boolean,
   "canTransport": Boolean
 },
 "haveObstacles": {
   "haveTransporter": Boolean,
   "driveTransporter": Boolean,
   "canMontate": Boolean,
   "canInstall": Boolean,
   "canDischarge": Boolean,
   "canTransport": Boolean
 },
 "charge": {
   "package": String,
   "weight": String,
   "size": {
     "height": String,
     "length": String,
     "width": String
   },
 },
   "matchedPartner": String,
   "active": Boolean,
   "succeed": Boolean,
   "transporter": String
});

module.exports = mongoose.model('Entry', EntrySchema);
