import types

from websocket import create_connection, ABNF


# 替换 WebSocket 的 send 函数
def send(self, payload, opcode=ABNF.OPCODE_TEXT):
    frame = ABNF.create_frame(payload, opcode)
    self.send_frame(frame)
    print("send: type: %s, frame: %s" % (frame.OPCODE_MAP[frame.opcode], frame))
    frame = self.recv_frame()
    print("recv: type: %s, frame: %s" % (frame.OPCODE_MAP[frame.opcode], frame))


def enhance(ws):
    ws.send = types.MethodType(send, ws)
    return ws


ws = enhance(create_connection("ws://127.0.0.1:8086/ws"))

ws.send("an text frame")
ws.send_binary(b"an binary frame")
ws.ping(b"hello")

ws.close()
