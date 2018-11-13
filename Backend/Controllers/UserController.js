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

router.post('/', validator, calorie, (req, res) => {
    req.body.password = cryptoJS.SHA256(req.body.password);
    mysqlConnection.query('INSERT INTO users SET ?', req.body, (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/login', (req, res) => {
    var email = req.body.email;
    var password = cryptoJS.SHA256(req.body.password).toString();

    mysqlConnection.query('SELECT * FROM users WHERE email = ?', [email], function (err, rows, fields) {
        if (rows.length > 0) {
            if (rows[0].Password == password) {
                var token = jwt.sign({
                    exp: Math.floor(Date.now()) + (60 * 60 * 24 * 7 * 1000),
                    data: rows[0].Id
                }, secret);
                res.json({ token: token, calorie: rows[0].Calorie });
            } else {
                res.status(401).json({ error: "Email jelszó páros nem jó!", attributeName: "emailPassword" });
            }
        } else {
            res.status(401).json({ error: "Nincs ilyen email!", attributeName: "emailEmpty" });
        }
    });
});

module.exports = router;