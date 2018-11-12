const express = require('express');
const mysqlConnection = require('../Database/Database').mysqlConnection;
const validator = require('../Middleware/Validators/PracticeRegisterValidator').PracticeRegisterValidator;

const router = express.Router();

router.get('/', (req, res) => {
    mysqlConnection.query('SELECT * FROM practices', (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/', validator, (req, res) => {
    mysqlConnection.query('INSERT INTO practices SET ?', req.body, (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.get('/bodypart', (req, res) => {
    var name = req.query.name;

    mysqlConnection.query('SELECT * FROM practices WHERE name = ?', [name], function (err, rows, fields) {
        if (rows.length > 0) {
            res.json(rows);
        } else {
            res.status(401).json({ error: "Nincs ilyen testrész!", attributeName: "nameEmpty" });
        }
    });
});

module.exports = router;