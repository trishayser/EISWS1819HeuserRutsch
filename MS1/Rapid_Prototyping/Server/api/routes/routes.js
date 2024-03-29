'use strict';
module.exports = function(app) {
  var entry = require('../controllers/entryController');
  var match = require('../controllers/matchController')

  // Entry Routes
  app.route('/entry')
    .get(entry.list_entrys)
    .post(entry.create_entry);

  app.route('/entry/:entryId')
    .get(entry.read_entry)
    .put(entry.update_entry)
    .delete(entry.delete_entry);

  //  Match Routes
  app.route('/match/:entryID')
    .get(match.match);
};
