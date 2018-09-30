const validator = require('validator');
const mysqlConnection = require('../../Database/Database').mysqlConnection;

function UserRegisterValidator(req, res, next) {

    var fault = false;

    if (!req.body.Name) {
        fault = true;
        return res.status(403).json({ Error: "A név nem lehet üres!" });
    }

    if (!req.body.Password) {
        fault = true;
        return res.status(403).json({ Error: "A jelszó nem lehet üres!" });
    }

    if (!req.body.Gender) {
        fault = true;
        return res.status(403).json({ Error: "A biológiai nem, nem lehet üres!" });
    }

    if (!req.body.Activity_level) {
        fault = true;
        return res.status(403).json({ Error: "Az aktivitási szint nem lehet üres!" });
    }

    if (req.body.Age) {
        if (validator.isInt(req.body.Age.toString()) === false) {
            fault = true;
            return res.status(403).json({ Error: "Nem egész számban adta meg az életkort!" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ Error: "Az életkor nem lehet üres!" });
    }

    if (req.body.Weight) {
        if (validator.isInt(req.body.Weight.toString()) === false) {
            fault = true;
            return res.status(403).json({ Error: "Nem egész számban adta meg a súlyt!" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ Error: "Az súly nem lehet üres!" });
    }

    if (req.body.Height) {
        if (validator.isInt(req.body.Height.toString()) === false) {
            fault = true;
            return res.status(403).json({ Error: "Nem egész számban adta meg a magasságot!" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ Error: "Az magasság nem lehet üres!" });
    }

    if (req.body.Email) {
        if (validator.isEmail(req.body.Email) === false) {
            fault = true;
            return res.status(403).json({ Error: "Nem jó email formátum!" });
        }
        else {
            mysqlConnection.query('SELECT * FROM users WHERE email = ?', [req.body.Email], function (err, rows, fields) {
                if (rows.length > 0) {
                    if (rows[0].Email == req.body.Email) {
                        fault = true;
                        return res.status(403).json({ Error: "Ez az email már foglalt!" });
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
        return res.status(403).json({ Error: "Az email nem lehet üres!" });
    }
}

module.exports = { UserRegisterValidator: UserRegisterValidator };