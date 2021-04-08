import os
import asyncio
import websockets


class Server:

    def get_port(self):
        return os.getenv('WS_PORT', '8765')

    def get_host(self):
        return os.getenv('WS_HOST', 'localhost')


    def start(self):
        return websockets.serve(self.handler, self.get_host(), self.get_port())

    async def handler(self, websocket, path):
        async for message in websocket:
            if message != 'ws://localhost:8765':
                print('server received :', message[::-1])
            try:
                await websocket.send(message[::-1])
            except Exception as e:
                pass
            finally:
                await asyncio.sleep(1)

if __name__ == '__main__':
    ws = Server()
    asyncio.get_event_loop().run_until_complete(ws.start())
    asyncio.get_event_loop().run_forever()