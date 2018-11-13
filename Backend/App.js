const express = require('express');
const bodyparser = require('body-parser');
const UserController = require('./Controllers/UserController');
const TrainingController = require('./Controllers/TrainingController');
const UsersTrainingsController = require('./Controllers/UsersTrainingsController');

var app = express();

app.use(bodyparser.json());
app.use("/users", UserController);
app.use("/trainings", TrainingController);
app.use("/userstrainings", UsersTrainingsController);
app.listen(3000);