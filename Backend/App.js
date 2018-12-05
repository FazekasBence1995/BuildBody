const express = require('express');
const bodyparser = require('body-parser');
const UserController = require('./Controllers/UserController');
const TrainingController = require('./Controllers/TrainingController');
const UsersTrainingsController = require('./Controllers/UsersTrainingsController');
const path = require('path');

var app = express();

app.use("/gifs", express.static(path.join(__dirname, 'Gifs')))
app.use(bodyparser.json());
app.use("/users", UserController);
app.use("/trainings", TrainingController);
app.use("/userstrainings", UsersTrainingsController);
app.listen(3000);