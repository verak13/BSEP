import os
import random
from abc import ABC, abstractmethod
from random import randint
from time import sleep, strftime
from datetime import datetime


class Context:
    def __init__(self):
        self.state = NormalState()

    def run(self):
        self.state.run(self)

    @property
    def state(self):
        return self._state

    @state.setter
    def state(self, value):
        self._state = value


#log format: yyyy-MM-dd HH:mm:ss source type severity ip username message1 message2...


SEVERITY = ['DEBUG', 'INFO', 'WARN', 'ERROR', 'FATAL', 'TRACE']
BLACKLISTED_IP = ['20.20.20.23']
PATHS = ['simulator1.log', 'simulator2.log']
STATES = ['NORMAL_STATE', 'BRUTE_FORCE_ATTACK_STATE', 'DOS_ATTACK_STATE', 'BLACKLISTED_IP_STATE']

class State(ABC):
    @abstractmethod
    def run(self, context):
        return NotImplemented

#log format: yyyy-MM-dd HH:mm:ss source type severity ip username message1 message2...
class NormalState(State):
    def run(self, context):
        now = datetime.now()
        log1 = now.strftime("%Y-%m-%d %H:%M:%S") + " SOURCE LOGIN TRACE 20.20.20.20 admin@gmail.com Successfully logged in."
        log2 = now.strftime("%Y-%m-%d %H:%M:%S") + " SOURCE LOGIN TRACE 20.20.20.21 a@gmail.com Unsuccessfull log in."

        f = open(random.choice(PATHS), "a")
        f.write(log1 + "\n")
        f.write(log2 + "\n")
        f.close()
        sleep(2)

        context.state = get_next_state()
        context.state.run(context)

#log format: yyyy-MM-dd HH:mm:ss source type severity ip username message1 message2...
class BruteForceAttackState(State):
    def run(self, context):
        now = datetime.now()
        path = "simulator2.log"
        for i in range(100):
            log = now.strftime("%Y-%m-%d %H:%M:%S") + " SOURCE LOGIN_ERROR TRACE 20.20.20.20 doctor@gmail.com Unsuccessful logg in."
            f = open(path, "a")
            f.write(log + "\n")
            f.close()
        sleep(2)

        context.state = get_next_state()
        context.state.run(context)


class BlacklistedIPState(State):
    def run(self, context):
        now = datetime.now()
        log = now.strftime("%Y-%m-%d %H:%M:%S") + " SOURCE LOGIN TRACE " + random.choice(BLACKLISTED_IP) + \
              " doctor@gmail.com Unsuccessful logg in."

        f = open(random.choice(PATHS), "a")
        f.write(log + "\n")
        f.close()
        sleep(2)

        context.state = get_next_state()
        context.state.run(context)


class DoSAttackState(State):
    def run(self, context):
        now = datetime.now()
        path = random.choice(PATHS)
        for i in range(15):
            log = now.strftime("%Y-%m-%d %H:%M:%S") + " SOURCE DOS TRACE 20.20.20.20 doctor@gmail.com Requested resource index.html."

            f = open(path, "a")
            f.write(log + "\n")
            f.close()
        sleep(2)

        context.state = get_next_state()
        context.state.run(context)

#STATES = ['NORMAL_STATE', 'BRUTE_FORCE_ATTACK_STATE', 'DOS_ATTACK_STATE', 'BLACKLISTED_IP_STATE']
def get_next_state():
    next = random.choice(STATES)
    if next == 'BRUTE_FORCE_ATTACK_STATE':
        return BruteForceAttackState()
    elif next == 'BLACKLISTED_IP_STATE':
        return BlacklistedIPState()
    elif next == 'DOS_ATTACK_STATE':
        return DoSAttackState()
    else:
        return NormalState()




if __name__ == '__main__':
    pass
    ctx = Context()
    ctx.run()
