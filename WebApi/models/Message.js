
var db = require('../dbconnection'); //reference of dbconnection.js
var Task = {
    getAllMessages: function(callback) {
        return db.query("Select * from messages", callback);
    },
    getMessageByid: function(id, callback) {
        return db.query("select * from messages where Id=?", [id], callback);
    },
    addMessage: function(Message, callback) {
        return db.query("Insert into messages values(?,?,?,?)", [Message.Id, Message.Letter, Message.Long, Message.Lat], callback);
    },
    deleteMessage: function(id, callback) {
        return db.query("delete from messages where Id=?", [id], callback);
    },
    updateMessage: function(id, Task, callback) {
        return db.query("update messages set Letter=?,Long=?,Lat=?, where Id=?", [Message.Letter, Message.Long, Message.Lat, id], callback);
    }
};
module.exports = Message;
