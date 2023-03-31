from flask import Flask, render_template

app = Flask(__name__)

@app.route("/")
@app.route("/main")
def index():
    return render_template('main.html')

@app.route("/select_list")
def list():
    return  render_template('select_list.html')

@app.route("/select_period")
def period():
    return  render_template('select_period.html')

@app.route("/result")
def result():
    return  render_template('result.html')


@app.route("/test")
def t1():
    return  render_template('test.html')    

@app.route('/test2')
def t2():
    return  render_template('test2.html')    

app.run(debug=True)
