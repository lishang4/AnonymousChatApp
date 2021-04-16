#!/usr/bin/env python

# WS client example

import asyncio
import websockets

async def keep_alive():
    uri = "ws://192.168.3.105:8765"
    async with websockets.connect(uri, close_timeout=1800) as websocket:
        while 1:
            userInput = input()
            await websocket.send(userInput)
            await asyncio.sleep(10)
            greeting = await websocket.recv()
            print(f'> got received {greeting}')

asyncio.get_event_loop().run_until_complete(keep_alive())
