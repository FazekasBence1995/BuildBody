function CalorieCalculator(req, res, next) {
	var standardMale = (10 * req.body.weight) + (6.25 * req.body.height) - (5 * req.body.age) + 5;
	var standardFemale = (10 * req.body.weight) + (6.25 * req.body.height) - (5 * req.body.age) - 161;

	if (req.body.gender === "Férfi") {
		if (req.body.activity_level === "Könnyű") {
			req.body.Calorie = standardMale * 1.4;
		}
		if (req.body.activity_level === "Közepes") {
			req.body.Calorie = standardMale * 1.7;
		}
		if (req.body.activity_level === "Nehéz") {
			req.body.Calorie = standardMale * 1.9;
		}
	}
	else {
		if (req.body.activity_level === "Könnyű") {
			req.body.Calorie = standardFemale * 1.4;
		}
		if (req.body.activity_level === "Közepes") {
			req.body.Calorie = standardFemale * 1.6;
		}
		if (req.body.activity_level === "Nehéz") {
			req.body.Calorie = standardFemale * 1.7;
		}
	}
	next();
}

module.exports={CalorieCalculator:CalorieCalculator};