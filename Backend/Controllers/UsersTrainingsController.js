const express = require('express');
const mysqlConnection = require('../Database/Database').mysqlConnection;
const loginMiddleware = require('../Middleware/LoginMiddleware').LoginMiddleware;

const router = express.Router();

router.get('/', loginMiddleware, (req, res) => {
    mysqlConnection.query('SELECT * FROM userstrainings', (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/', loginMiddleware, (req, res) => {
    mysqlConnection.query('INSERT INTO userstrainings SET ?', {UserId: req.userId, ...req.body}, (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/mytrainings', (req, res) => {
    var email = req.body.email;
    var name = req.body.name;

    mysqlConnection.query('SELECT * FROM userstrainings WHERE email = ?', [email], function (err, rows, fields) {
        if (rows.length > 0) {
            var n = rows.length;
            var answer = [];
            var answerSize = 0;

            for(var i = 0; i < n; i++){
                if(rows[i].Name == name){
                    answer[answerSize] = rows[i];
                    answerSize++;
                }
            }
            if(answer.length == 0){
                res.status(401).json({ error: "Nem vettél fel még a testrészhez gyakorlatot!", attributeName: "trainingEmpty" });
            } else{
                res.json({ answer: answer });
            }
        }
    });
});

module.exports = router;