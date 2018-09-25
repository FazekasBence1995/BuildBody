function CalorieCalculator(req, res, next){
    var standardMale = (10 * req.body.Weight) + (6.25 * req.body.Height) - (5 * req.body.Age) + 5;
	var standardFemale = (10 * req.body.Weight) + (6.25 * req.body.Height) - (5 * req.body.Age) - 161;
	
	if(req.body.Gender === "male"){
		if(req.body.Activity_level === "low"){
			return req.body.Calorie = standardMale * 1.4;
		}
		if(req.body.Activity_level === "medium"){
			return req.body.Calorie = standardMale * 1.7;
		}
		if(req.body.Activity_level === "high"){
			return req.body.Calorie = standardMale * 1.9;
		}
	}
	else{
		if(req.body.Activity_level === "low"){
			return req.body.Calorie = standardFemale * 1.4;
		}
		if(req.body.Activity_level === "medium"){
			return req.body.Calorie = standardFemale * 1.6;
		}
		if(req.body.Activity_level === "high"){
			return req.body.Calorie = standardFemale * 1.7;
		}
	}
}

module.exports={CalorieCalculator:CalorieCalculator};