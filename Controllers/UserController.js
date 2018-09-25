const express = require('express');
const cryptoJS = require('crypto-js');
const mysqlConnection = require('../Database/Database').mysqlConnection;
const validator = require('../Middleware/Validators/UserRegisterValidator').UserRegisterValidator;
const calorie = require('../Counters/CalorieCounter').CalorieCalculator;
var jwt = require('jsonwebtoken');

const secret = "kacsakacsa";
const router = express.Router();

mysqlConnection.connect((err) => {
    if (!err)
        console.log('DB connection succeded.');
    else
       console.log('DB connection failed \n Error : ' + JSON.stringify(err, undefined, 2));
});

router.get('/', (req, res) => {
    mysqlConnection.query('SELECT * FROM users', (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/', validator, (req, res) => {
    req.body.Password = cryptoJS.SHA256(req.body.Password);
    mysqlConnection.query('INSERT INTO users SET ?', req.body, (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/login', (req, res) => {
    var email = req.body.Email;
    var password = cryptoJS.SHA256(req.body.Password).toString();

    mysqlConnection.query('SELECT * FROM users WHERE email = ?', [email], function (err, rows, fields) {
        if (rows.length > 0) {
            if (rows[0].Password == password) {
                var token = jwt.sign({
                    exp: Math.floor(Date.now() / 1000) + (60 * 60),
                    data: rows[0].Id
                  }, secret);
                res.json({token: token});
            } else {
                res.status(401).json({Error: "Email jelszó páros nem jó!" });
            }
        } else {
            res.status(401).json({Error: "Nincs ilyen email!" });
        }
    });
});

module.exports = router;