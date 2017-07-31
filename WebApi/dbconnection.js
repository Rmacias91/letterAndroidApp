[code language = "javascript"]
var mysql = require('mysql');
var connection = mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'mysql'
});
module.exports = connection;
[/code]
