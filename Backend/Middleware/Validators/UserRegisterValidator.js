const validator = require('validator');
const mysqlConnection = require('../../Database/Database').mysqlConnection;

function UserRegisterValidator(req, res, next) {

    var fault = false;

    if (!req.body.name) {
        fault = true;
        return res.status(403).json({ error: "A név nem lehet üres!", attributeName: "name" });
    }

    if (!req.body.password) {
        fault = true;
        return res.status(403).json({ error: "A jelszó nem lehet üres!", attributeName: "password" });
    }

    if (!req.body.gender) {
        fault = true;
        return res.status(403).json({ error: "A biológiai nem, nem lehet üres!", attributeName: "gender" });
    }

    if (!req.body.activity_level) {
        fault = true;
        return res.status(403).json({ error: "Az aktivitási szint nem lehet üres!", attributeName: "activity_level" });
    }

    if (req.body.age) {
        if (validator.isInt(req.body.age.toString()) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem egész számban adta meg az életkort!", attributeName: "age" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az életkor nem lehet üres!", attributeName: "age" });
    }

    if (req.body.weight) {
        if (validator.isInt(req.body.weight.toString()) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem egész számban adta meg a súlyt!", attributeName: "weight" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az súly nem lehet üres!", attributeName: "weight" });
    }

    if (req.body.height) {
        if (validator.isInt(req.body.height.toString()) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem egész számban adta meg a magasságot!", attributeName: "height" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az magasság nem lehet üres!", attributeName: "height" });
    }

    if (req.body.email) {
        if (validator.isEmail(req.body.email) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem jó email formátum!", attributeName: "email" });
        }
        else {
            mysqlConnection.query('SELECT * FROM users WHERE email = ?', [req.body.email], function (err, rows, fields) {
                if (rows.length > 0) {
                    if (rows[0].Email == req.body.email) {
                        fault = true;
                        return res.status(403).json({ error: "Ez az email már foglalt!", attributeName: "email"});
                    } 
                }
                else {
                    next();
                }
            });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az email nem lehet üres!", attributeName: "email" });
    }
}

module.exports = { UserRegisterValidator: UserRegisterValidator };