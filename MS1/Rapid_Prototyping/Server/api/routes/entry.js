'use strict';
module.exports = function(app) {
  var entry = require('../controllers/entryController');

  // todoList Routes
  app.route('/entry')
    .get(entry.list_entrys)
    .post(entry.create_entry);


  app.route('/tasks/:taskId')
    .get(todoList.read_entry)
    .put(todoList.update_entry)
    .delete(todoList.delete_entry);
};
