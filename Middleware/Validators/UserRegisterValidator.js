const validator = require('validator');

function UserRegisterValidator(req, res, next){
    if(!req.body.Name) 
        return res.status(403).json({Error: "A név nem lehet üres!"});

    if(req.body.Email) {
        if(validator.isEmail(req.body.Email) === false)
            return res.status(403).json({Error: "Nem jó email formátum!"});
    }
    else
        return res.status(403).json({Error: "Az email nem lehet üres!"});

    if(!req.body.Password) 
        return res.status(403).json({Error: "A jelszó nem lehet üres!"});

    if(!req.body.Gender) 
        return res.status(403).json({Error: "A biológiai nem, nem lehet üres!"});

    if(!req.body.Activity_level) 
        return res.status(403).json({Error: "Az aktivitási szint nem lehet üres!"});
    
    if (req.body.Age) {
        if (validator.isInt(req.body.Age.toString()) === false)
            return res.status(403).json({Error: "Nem egész számban adta meg az életkort!"});
    }
    else
        return res.status(403).json({Error: "Az életkor nem lehet üres!"});

    if (req.body.Weight) {
        if (validator.isInt(req.body.Weight.toString()) === false)
            return res.status(403).json({Error: "Nem egész számban adta meg a súlyt!" });
    }
    else
        return res.status(403).json({Error: "Az súly nem lehet üres!" });

    if (req.body.Height) {
        if (validator.isInt(req.body.Height.toString()) === false)
            return res.status(403).json({Error: "Nem egész számban adta meg a magasságot!" });
    }
    else
        return res.status(403).json({ Error: "Az magasság nem lehet üres!" });   
        
    next();
}

module.exports={UserRegisterValidator:UserRegisterValidator};