from flask import Flask

app = Flask(__name__)

 

@app.route('/')
def index():
    return 'pvalue = ' + 'a'

@app.route('/create')
def create():
    return 'Create'

app. run(port=5001, debug=True) # 실제로 실행할 땐 debug코드 제거해야 함.