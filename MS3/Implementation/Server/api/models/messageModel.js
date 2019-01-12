'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EntrySchema = new Schema({
   "messageID": String,
   "time": String,
   "fromUserID": String,
   "toUserID": String,
   "text": String,
   "attachment": {
       "type": String,
       "body": String
   }
});

module.exports = mongoose.model('Message', EntrySchema);
