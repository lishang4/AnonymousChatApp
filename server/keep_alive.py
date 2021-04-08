#!/usr/bin/env python

# WS client example

import asyncio
import websockets

async def keep_alive():
    uri = "ws://localhost:8765"
    async with websockets.connect(uri, close_timeout=1800) as websocket:
        while 1:
            await websocket.send(uri)
            await asyncio.sleep(10)
            greeting = await websocket.recv()
            print('> got received')

asyncio.get_event_loop().run_until_complete(keep_alive())
