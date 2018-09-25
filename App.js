const express = require('express');
const bodyparser = require('body-parser');
const UserController = require('./Controllers/UserController');

var app = express();

app.use(bodyparser.json());
app.use("/users", UserController);
app.listen(3000);