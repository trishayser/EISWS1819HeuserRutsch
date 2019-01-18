'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var EntrySchema = new Schema({
  "userID": String,
  "username": String,
  "name": String,
  "surname": String,
  "residence": String,
  "email": String,
  "gender": String,
  "picture": String,
  "password": String,
  "rating": {
      "userID": Array,
      "points": Array,
      "date": Array,
      "comment": Array
   }
});

module.exports = mongoose.model('User', EntrySchema);
