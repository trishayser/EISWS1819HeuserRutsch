'use strict';
module.exports = function(app) {
  var entry = require('../controllers/entryController');
  var match = require('../controllers/matchController');
  var user = require('../controllers/userController');
  var message = require('../controllers/messageController');

  // Entry Routes
  app.route('/entry')
    .get(entry.list_entrys)
    .post(entry.create_entry);

  app.route('/entry/:entryId')
    .get(entry.read_entry)
    .put(entry.update_entry)
    .delete(entry.delete_entry);

  //  Match Routes
  app.route('/entry/match/:entryID')
    .get(match.match);


  // User Routes
  app.route('/user/username/:username')
    .get(user.read_username)


  app.route('/user/:userID')
      .get(user.read_user)
      .put(user.update_user)
      .delete(user.delete_user);

  app.route('/user')
        .get(user.list_users)
        .post(user.create_user);

  // Message Routes
  app.route('/message')
    .get();

};
