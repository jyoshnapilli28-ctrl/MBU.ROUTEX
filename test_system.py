import re
import requests

BASE = 'http://localhost:8080'

def get_csrf(s, url):
    page = s.get(f'{BASE}{url}')
    match = re.search(r'name="_csrf"\s+value="([^"]+)"', page.text)
    return match.group(1) if match else None

def test_role(login_url, username, password, expected_dash, modules):
    print(f'=== Testing user: {username} ===')
    s = requests.Session()
    
    # 1. Fetch login page to get CSRF token
    csrf_token = get_csrf(s, login_url)
    print(f'CSRF Token: {csrf_token[:10]}...' if csrf_token else 'No CSRF token')

    payload = {
        'username': username,
        'password': password
    }
    if csrf_token:
        payload['_csrf'] = csrf_token

    # 2. Login POST
    res = s.post(f'{BASE}/login/process', data=payload, allow_redirects=True)
    print(f'Login -> Final URL: {res.url}, Status: {res.status_code}')
    assert expected_dash in res.url, f'Expected {expected_dash} in {res.url}'
    
    # 3. Check each module
    for mod in modules:
        mres = s.get(f'{BASE}{mod}')
        print(f'  GET {mod} -> {mres.status_code} ({len(mres.content)} bytes)')
        assert mres.status_code == 200, f'Failed {mod}: status {mres.status_code}'

    return s

# Test Student Flow
s_sess = test_role('/login/student', 'student1', 'password', '/student/dashboard', [
    '/student/dashboard',
    '/student/my-bus',
    '/student/live-track',
    '/student/complaint',
    '/student/emergency',
    '/student/attendance',
    '/student/attendance/qr-image',
    '/student/notifications',
    '/student/profile'
])

# Test Student Complaint Submission
s_complaint_csrf = get_csrf(s_sess, '/student/complaint')
post_complaint = s_sess.post(f'{BASE}/student/complaint', data={
    'category': 'Delay',
    'busNumber': 'MBU-001',
    'description': 'Automated verification test complaint for bus delay.',
    '_csrf': s_complaint_csrf
}, allow_redirects=True)
print(f'Student POST /student/complaint -> {post_complaint.status_code}, URL: {post_complaint.url}')
assert post_complaint.status_code == 200
assert 'Automated verification test complaint' in post_complaint.text

# Test Driver Flow
d_sess = test_role('/login/driver', 'driver1', 'password', '/driver/dashboard', [
    '/driver/dashboard',
    '/driver/bus-status',
    '/driver/service-details',
    '/driver/bus-complaints',
    '/driver/schedules',
    '/driver/emergency',
    '/driver/notifications',
    '/driver/profile'
])

# Test Driver Bus Status Update
d_status_csrf = get_csrf(d_sess, '/driver/bus-status')
post_status = d_sess.post(f'{BASE}/driver/bus-status/update', data={
    'status': 'ON_ROUTE',
    '_csrf': d_status_csrf
}, allow_redirects=True)
print(f'Driver POST /driver/bus-status/update -> {post_status.status_code}, URL: {post_status.url}')
assert post_status.status_code == 200

# Test Management Flow
m_sess = test_role('/login/management', 'management1', 'password', '/management/dashboard', [
    '/management/dashboard',
    '/management/bus-details',
    '/management/trips',
    '/management/complaints',
    '/management/maintenance',
    '/management/reports',
    '/management/students',
    '/management/notifications',
    '/management/profile'
])

# Test Management Complaint Status Update
m_complaint_csrf = get_csrf(m_sess, '/management/complaints')
post_mgmt_complaint = m_sess.post(f'{BASE}/management/complaints/1/update', data={
    'status': 'IN_REVIEW',
    'response': 'Reviewed by transport director - investigation underway.',
    '_csrf': m_complaint_csrf
}, allow_redirects=True)
print(f'Management POST /management/complaints/1/update -> {post_mgmt_complaint.status_code}, URL: {post_mgmt_complaint.url}')
assert post_mgmt_complaint.status_code == 200

# Test Security Role Protection
print('=== Testing Security Boundaries ===')
res_denied_student = s_sess.get(f'{BASE}/management/dashboard')
print(f'Student accessing /management/dashboard -> {res_denied_student.status_code} (Should be 403 or Access Denied)')
assert res_denied_student.status_code in [403, 200] and ('Access Denied' in res_denied_student.text or res_denied_student.status_code == 403)

res_denied_driver = d_sess.get(f'{BASE}/student/dashboard')
print(f'Driver accessing /student/dashboard -> {res_denied_driver.status_code} (Should be 403 or Access Denied)')
assert res_denied_driver.status_code in [403, 200] and ('Access Denied' in res_denied_driver.text or res_denied_driver.status_code == 403)

print('\n========================================')
print('[SUCCESS] ALL VERIFICATION CRITERIA PASSED 100%')
print('========================================')
