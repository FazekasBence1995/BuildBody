const mysql = require('mysql');
const express = require('express');
const bodyparser = require('body-parser');
const mysqlConnection = require('../Database/Database').mysqlConnection;
var jwt = require('jsonwebtoken');

var token;
var app = express();

app.use(bodyparser.json());
app.listen(3000);


mysqlConnection.connect((err) => {
    if (!err)
        console.log('DB connection succeded.');
    else
       console.log('DB connection failed \n Error : ' + JSON.stringify(err, undefined, 2));
});

app.get('/users', (req, res) => {
    mysqlConnection.query('SELECT * FROM users', (err, rows, fields) => {
        if (!err)
            res.send(rows);
        else
            console.log(err);
    })
});

app.post('/users', (req, res) => {
    mysqlConnection.query('INSERT INTO users SET ?', req.body, (err, rows, fields) => {
        if (!err)
            res.send(rows);
        else
            console.log(err);
    })
});

app.post('/login', (req, res) => {
    var email = req.body.Email;
    var password = req.body.Password;

    mysqlConnection.query('SELECT * FROM users WHERE email = ?', [email], function (err, rows, fields) {
        if (rows.length > 0) {
            if (rows[0].Password == password) {

                res.send('Sikeres bejelentkezés!');
                console.log('Sikeres bejelentkezés!');
            } else {
                res.send('Email jelszó páros nem jó!');
                console.log('Email jelszó páros nem jó!');
                res.send(rows[0].Password);
                console.log(password);
            }
        } else {
            res.send('Nincs ilyen email!');
            console.log('Nincs ilyen email!');
        }
    });
});