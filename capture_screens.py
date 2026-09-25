import subprocess
import time
import json
import urllib.request
import base64
import websocket

CHROME_PATH = r"C:\Program Files\Google\Chrome\Application\chrome.exe"

def run_cdp():
    # Start Chrome with remote debugging
    cmd = [
        CHROME_PATH,
        "--headless=new",
        "--remote-debugging-port=9222",
        "--window-size=1440,900",
        "--no-sandbox",
        "--disable-gpu",
        "--remote-allow-origins=*",
        "about:blank"
    ]
    proc = subprocess.Popen(cmd)
    time.sleep(2)
    
    try:
        # Get target list
        with urllib.request.urlopen("http://localhost:9222/json") as response:
            targets = json.loads(response.read().decode())
        ws_url = targets[0]["webSocketDebuggerUrl"]
        
        ws = websocket.create_connection(ws_url)
        
        msg_id = 1
        def send(method, params=None):
            nonlocal msg_id
            payload = {"id": msg_id, "method": method}
            if params:
                payload["params"] = params
            ws.send(json.dumps(payload))
            msg_id += 1
            while True:
                res = json.loads(ws.recv())
                if res.get("id") == payload["id"]:
                    return res
        
        send("Page.enable")
        send("Runtime.enable")
        send("Network.enable")
        
        # 1. Capture Homepage
        print("Navigating to Homepage...")
        send("Page.navigate", {"url": "http://localhost:8080/"})
        time.sleep(2)
        res = send("Page.captureScreenshot", {"format": "png"})
        img_data = base64.b64decode(res["result"]["data"])
        with open("d:/java project/MBU.ROUTEX/screenshot_homepage.png", "wb") as f:
            f.write(img_data)
        print("Saved screenshot_homepage.png")
        
        # 2. Login as Student
        print("Logging in as Student...")
        send("Page.navigate", {"url": "http://localhost:8080/login/student"})
        time.sleep(1.5)
        # Evaluate login form fill and submit
        login_js = """
        (() => {
            const u = document.querySelector('input[name="username"]');
            const p = document.querySelector('input[name="password"]');
            if (u && p) {
                u.value = 'student1';
                p.value = 'password';
                const form = u.closest('form');
                if (form) form.submit();
            }
        })()
        """
        send("Runtime.evaluate", {"expression": login_js})
        time.sleep(2.5)
        res = send("Page.captureScreenshot", {"format": "png"})
        img_data = base64.b64decode(res["result"]["data"])
        with open("d:/java project/MBU.ROUTEX/screenshot_student.png", "wb") as f:
            f.write(img_data)
        print("Saved screenshot_student.png")
        
        # Logout
        send("Page.navigate", {"url": "http://localhost:8080/logout"})
        time.sleep(1)
        send("Runtime.evaluate", {"expression": "(() => { const form = document.querySelector('form'); if(form) form.submit(); })()"})
        time.sleep(1.5)
        
        # 3. Login as Driver
        print("Logging in as Driver...")
        send("Page.navigate", {"url": "http://localhost:8080/login/driver"})
        time.sleep(1.5)
        driver_js = """
        (() => {
            const u = document.querySelector('input[name="username"]');
            const p = document.querySelector('input[name="password"]');
            if (u && p) {
                u.value = 'driver1';
                p.value = 'password';
                const form = u.closest('form');
                if (form) form.submit();
            }
        })()
        """
        send("Runtime.evaluate", {"expression": driver_js})
        time.sleep(2.5)
        res = send("Page.captureScreenshot", {"format": "png"})
        img_data = base64.b64decode(res["result"]["data"])
        with open("d:/java project/MBU.ROUTEX/screenshot_driver.png", "wb") as f:
            f.write(img_data)
        print("Saved screenshot_driver.png")
        
        # Logout
        send("Page.navigate", {"url": "http://localhost:8080/logout"})
        time.sleep(1)
        send("Runtime.evaluate", {"expression": "(() => { const form = document.querySelector('form'); if(form) form.submit(); })()"})
        time.sleep(1.5)
        
        # 4. Login as Management
        print("Logging in as Management...")
        send("Page.navigate", {"url": "http://localhost:8080/login/management"})
        time.sleep(1.5)
        mgmt_js = """
        (() => {
            const u = document.querySelector('input[name="username"]');
            const p = document.querySelector('input[name="password"]');
            if (u && p) {
                u.value = 'management1';
                p.value = 'password';
                const form = u.closest('form');
                if (form) form.submit();
            }
        })()
        """
        send("Runtime.evaluate", {"expression": mgmt_js})
        time.sleep(2.5)
        res = send("Page.captureScreenshot", {"format": "png"})
        img_data = base64.b64decode(res["result"]["data"])
        with open("d:/java project/MBU.ROUTEX/screenshot_management.png", "wb") as f:
            f.write(img_data)
        print("Saved screenshot_management.png")
        
        ws.close()
    finally:
        proc.kill()

if __name__ == "__main__":
    run_cdp()
