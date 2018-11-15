const express = require('express');
const mysqlConnection = require('../Database/Database').mysqlConnection;
const loginMiddleware = require('../Middleware/LoginMiddleware').LoginMiddleware;
const validator = require('../Middleware/Validators/UsersTrainingsRegisterValidator').UsersTrainingsRegisterValidator;

const router = express.Router();

router.get('/', loginMiddleware, (req, res) => {
    mysqlConnection.query('SELECT * FROM userstrainings', (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.post('/', loginMiddleware, validator, (req, res) => {
    mysqlConnection.query('INSERT INTO userstrainings SET ?', {UserId: req.userId, ...req.body}, (err, rows, fields) => {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    })
});

router.get('/mytrainings', loginMiddleware, (req, res) => {
    var name = req.query.name;
    var userId = req.userId;

    mysqlConnection.query('SELECT ID, Name, URL, Description FROM userstrainings WHERE UserId = ?', userId, function (err, rows, fields) {
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
                res.json(answer);
            }
        }
    });
});


router.post('/mytrainings', loginMiddleware, (req, res) => {
    mysqlConnection.query('DELETE FROM userstrainings WHERE URL = ?', req.body.URL, function (err, rows, fields) {
        if (!err)
            res.json(rows);
        else
            res.json(err);
    });
});

module.exports = router;