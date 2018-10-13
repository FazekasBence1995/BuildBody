const validator = require('validator');
const mysqlConnection = require('../../Database/Database').mysqlConnection;

function UserRegisterValidator(req, res, next) {

    var fault = false;

    if (!req.body.name) {
        fault = true;
        return res.status(403).json({ error: "A név nem lehet üres!" });
    }

    if (!req.body.password) {
        fault = true;
        return res.status(403).json({ error: "A jelszó nem lehet üres!" });
    }

    if (!req.body.gender) {
        fault = true;
        return res.status(403).json({ error: "A biológiai nem, nem lehet üres!" });
    }

    if (!req.body.activity_level) {
        fault = true;
        return res.status(403).json({ error: "Az aktivitási szint nem lehet üres!" });
    }

    if (req.body.age) {
        if (validator.isInt(req.body.age.toString()) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem egész számban adta meg az életkort!" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az életkor nem lehet üres!" });
    }

    if (req.body.weight) {
        if (validator.isInt(req.body.weight.toString()) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem egész számban adta meg a súlyt!" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az súly nem lehet üres!" });
    }

    if (req.body.height) {
        if (validator.isInt(req.body.height.toString()) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem egész számban adta meg a magasságot!" });
        }
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az magasság nem lehet üres!" });
    }

    if (req.body.email) {
        if (validator.isEmail(req.body.email) === false) {
            fault = true;
            return res.status(403).json({ error: "Nem jó email formátum!" , });
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
        return res.status(403).json({ error: "Az email nem lehet üres!" });
    }
}

module.exports = { UserRegisterValidator: UserRegisterValidator };