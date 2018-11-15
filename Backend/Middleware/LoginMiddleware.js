var jwt = require('jsonwebtoken');
const mysqlConnection = require('../Database/Database').mysqlConnection;

function LoginMiddleware(req, res, next) {
    const secret = "kacsakacsa";
    const token = req.headers.authorization;

    if(token == null){
        return res.status(401).json({ error: "Nem jó token!", attributeName: "wrongToken" });
    } 
    
    try{
        const decoded = jwt.verify(token, secret);
        
        if(decoded.exp < Date.now()){
            return res.status(401).json({ error: "Nem jó token!", attributeName: "wrongToken" });
        }

        mysqlConnection.query("SELECT * FROM users WHERE Id = ?", [decoded.data], (err, rows, fields) => {
            if (err || rows.length < 0){
                return res.status(401).json({ error: "Nem jó token!", attributeName: "wrongToken" });
            } else {
                req.userId = decoded.data;
                next();
            }
        });

    } catch(error){
        return res.status(401).json({ error: "Nem jó token!", attributeName: "wrongToken" });
    }
}

module.exports = { LoginMiddleware: LoginMiddleware };