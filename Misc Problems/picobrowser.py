import requests

headers = {'User-Agent':'Mozilla/5.0 (compatible; Googlebot/2.1; +picobrowser.com)'}
r=requests.get("https://2019shell1.picoctf.com/problem/21851/flag", headers=headers)

print r.text