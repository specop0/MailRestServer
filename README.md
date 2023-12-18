# REST endpoint to send an e-mail.

## Configuration in JSON file needed

```json
{
    "mail" : {
        "host" : "smtp.mail.whatever.com",
        "port" : "587",
        "email" : "SpecOp0@whatever.com",
        "username" : "SpecOp0",
        "password" : "a secrect password"
    },
    "server" : {
        "ipAddress": "mailrestserver",
        "port" : 6897
    }
}
```

## Usage

```
POST http://mailrestserver:6897/mail/send
Content-Type: application/json

{
    "subject" : "My Subject",
    "content" : "My e-mail content."
}
```

```
curl http://mailrestserver:6897/mail/send -X POST -H 'content-type: application/json' -d '{"subject" : "My Subject","content" : "My e-mail content."}'
```