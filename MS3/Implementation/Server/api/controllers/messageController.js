'use strict';


var mongoose = require('mongoose'),
    Message = mongoose.model('Message');

exports.list_messages = function(req, res) {
  Message.find({}, function(err, Message) {
    if (err)
      res.send(err);
    res.json(message);
  });
};


exports.create_message = function(req, res) {
  var new_message = new Message(req.body);
  new_message.save(function(err, message) {
    if (err)
      res.send(err);
    res.json(message);
  });
};


exports.read_message = function(req, res) {
  Message.findById({_id: req.params.messageId}, function(err, message) {
    if (err)
      res.send(err);
    res.json(message);
  });
};


exports.update_message = function(req, res) {
  Message.findOneAndUpdate({_id: req.params.messageId}, req.body, {new: true}, function(err, message) {
    if (err)
      res.send(err);
    res.json(message);
  });
};


exports.delete_message = function(req, res) {
  Message.remove({
    _id: req.params.messageId
  }, function(err, entry) {
    if (err)
      res.send(err);
    res.json({ message: 'Nachricht wurde gelöscht' });
  });
};
