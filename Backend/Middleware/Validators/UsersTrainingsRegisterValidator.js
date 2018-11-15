const validator = require('validator');
const mysqlConnection = require('../../Database/Database').mysqlConnection;

function UsersTrainingsRegisterValidator(req, res, next) {
    var fault = false;

    if (!req.body.Name) {
        fault = true;
        return res.status(403).json({ error: "A név nem lehet üres!", attributeName: "name" });
    }

    if (!req.body.Description) {
        fault = true;
        return res.status(403).json({ error: "A leírás nem lehet üres!", attributeName: "description" });
    }

    if (req.body.URL) {
        mysqlConnection.query('SELECT * FROM userstrainings WHERE URL = ?', [req.body.URL], function (err, rows, fields) {
            if (rows.length > 0) {
                if (rows[0].URL == req.body.URL) {
                    fault = true;
                    return res.status(403).json({ error: "Ezt a gyakorlatot már felvette!", attributeName: "url"});
                } 
            }
            else {
                next();
            }
        });
    }
    else {
        fault = true;
        return res.status(403).json({ error: "Az URL nem lehet üres!", attributeName: "url" });
    }
}

module.exports = { UsersTrainingsRegisterValidator: UsersTrainingsRegisterValidator };