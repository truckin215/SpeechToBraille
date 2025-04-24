from flask import Flask
import RPi.GPIO as GPIO

app = Flask(__name__)

# List of GPIO pins you want to control
PIN_LIST = [17, 27, 22, 23, 24, 25, 5, 6, 13, 19, 26]  # Change as needed

GPIO.setmode(GPIO.BCM)
for pin in PIN_LIST:
    GPIO.setup(pin, GPIO.OUT)
    GPIO.output(pin, GPIO.LOW)  # Start off

@app.route('/fire/<int:pin>')
def fire(pin):
    case
    if pin in PIN_LIST:
        GPIO.output(pin, GPIO.HIGH)
        return f'Pin {pin} ON'
    return 'Invalid pin', 400

@app.route('/release/<int:pin>')
def release(pin):
    if pin in PIN_LIST:
        GPIO.output(pin, GPIO.LOW)
        return f'Pin {pin} OFF'
    return 'Invalid pin', 400

@app.route('/')
def index():
    return 'GPIO server running'

@app.route('/reset')
def reset():
    for pin in PIN_LIST:
        GPIO.output(pin, GPIO.LOW)
    return 'All pins reset to LOW'


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

