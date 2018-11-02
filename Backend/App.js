const express = require('express');
const bodyparser = require('body-parser');
const UserController = require('./Controllers/UserController');
const PracticeController = require('./Controllers/PracticeController');

var app = express();

app.use(bodyparser.json());
app.use("/users", UserController);
app.use("/practices", PracticeController);
app.listen(3000);