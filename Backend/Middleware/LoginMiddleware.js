var jwt = require('jsonwebtoken');
const mysqlConnection = require('../Database/Database').mysqlConnection;

function LoginMiddleware(req, res, next) {
    const secret = "kacsakacsa";
    const token = req.headers.authorization;

    if(token == null){
        return res.status(401).json({ error: "Nem jó token!1", attributeName: "wrongToken" });
    } 
    
    try{
        const decoded = jwt.verify(token, secret);
        
        if(decoded.exp < Date.now()){
            return res.status(401).json({ error: "Nem jó token!2", attributeName: "wrongToken" });
        }

        mysqlConnection.query("SELECT * FROM users WHERE Id = ?", [decoded.data], (err, rows, fields) => {
            if (err || rows.length < 0){
                return res.status(401).json({ error: "Nem jó token!3", attributeName: "wrongToken" });
            } else {
                req.userId = decoded.data;
                next();
            }
        });

    } catch(error){
        return res.status(401).json({ error: "Nem jó token!4", attributeName: "wrongToken" });
    }
}

module.exports = { LoginMiddleware: LoginMiddleware };