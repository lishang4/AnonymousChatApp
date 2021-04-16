#!/usr/bin/env python

# WS client example

import asyncio
import websockets

async def hello():
    uri = "ws://localhost:8765"
    async with websockets.connect(uri, close_timeout=1800, ping_interval=5) as websocket:
        while 1:
            await websocket.ping()
            name = input()
            if not websocket.open:
                websocket = websockets.connect(uri, close_timeout=1800)
                await asyncio.sleep(1)
            await websocket.send(name)
            print(f"> {name}")
            greeting = await websocket.recv()
            print(f"< {greeting}")

asyncio.get_event_loop().run_until_complete(hello())
