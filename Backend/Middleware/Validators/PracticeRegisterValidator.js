const validator = require('validator');
const mysqlConnection = require('../../Database/Database').mysqlConnection;

function PracticeRegisterValidator(req, res, next) {

    var fault = false;

    if (!req.body.name) {
        fault = true;
        return res.status(403).json({ error: "A név nem lehet üres!", attributeName: "name" });
    }

    if (!req.body.url) {
        fault = true;
        return res.status(403).json({ error: "Az url nem lehet üres!", attributeName: "url" });
    }

    if (!req.body.description) {
        fault = true;
        return res.status(403).json({ error: "A leírás nem lehet üres!", attributeName: "description" });
    }

    if(fault === false){
        next();
    }

}

module.exports = { PracticeRegisterValidator: PracticeRegisterValidator };