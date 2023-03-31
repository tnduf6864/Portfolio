from flask import flask
import random

app = Flask(__name__)


@app.route('/')
def index():
    return 'Welcom'


@app.route('/')
def create():
    return 'Welcom'

app.run(debug=True)