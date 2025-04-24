from flask import Flask
import RPi.GPIO as GPIO

app = Flask(__name__)

# Logical pin → BCM GPIO map
PIN_MAP = {
    0: 17,
    1: 27,
    2: 22,
    3: 23,
    4: 24,
    5: 25,
}

# Setup GPIO
GPIO.setmode(GPIO.BCM)
for pin in PIN_MAP.values():
    GPIO.setup(pin, GPIO.OUT)
    GPIO.output(pin, GPIO.LOW)  # Start with all pins LOW

@app.route('/fire/<int:logical_pin>')
def fire(logical_pin):
    gpio_pin = PIN_MAP.get(logical_pin)
    if gpio_pin is not None:
        GPIO.output(gpio_pin, GPIO.HIGH)
        return f'Logical pin {logical_pin} → GPIO {gpio_pin} ON'
    return 'Invalid pin', 400

@app.route('/release/<int:logical_pin>')
def release(logical_pin):
    gpio_pin = PIN_MAP.get(logical_pin)
    if gpio_pin is not None:
        GPIO.output(gpio_pin, GPIO.LOW)
        return f'Logical pin {logical_pin} → GPIO {gpio_pin} OFF'
    return 'Invalid pin', 400

@app.route('/reset')
def reset():
    for gpio_pin in PIN_MAP.values():
        GPIO.output(gpio_pin, GPIO.LOW)
    return 'All pins reset to LOW'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
