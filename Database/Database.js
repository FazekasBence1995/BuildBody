const mysql = require('mysql');

const mysqlConnection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Zambrotta1995',
    database: 'BuildBody',
    multipleStatements: true
});


module.exports={mysqlConnection:mysqlConnection};