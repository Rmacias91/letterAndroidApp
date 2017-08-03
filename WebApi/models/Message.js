
var db = require('../dbconnection'); //reference of dbconnection.js
var Message = {
    getAllMessages: function(callback) {
        return db.query("Select * from messages", callback);
    },
    getMessageById: function(id, callback) {
        return db.query("select * from messages where Id=?", [id], callback);
    },
    addMessage: function(Message, callback) {
        return db.query("INSERT INTO MESSAGES (Id,Lat,Lon,Letter) VALUES (?,?,?,?)", [Message.Id,Message.Lat, Message.Lon, Message.Letter], callback);
    },
    deleteMessage: function(id, callback) {
        return db.query("delete from messages where Id=?", [id], callback);
    },
    updateMessage: function(id, Task, callback) {
        return db.query("update messages set Letter=?,Lat=?,Lon=?, where Id=?", [Message.Letter, Message.Lat,Message.Lon,  id], callback);
    }
};
module.exports = Message;
