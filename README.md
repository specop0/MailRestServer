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
        "port" : 6897
    }
}
```

## Usage

```http
POST http://localhost:6897/mail/send
```

```json
{
    "subject" : "My Subject",
    "content" : "My e-mail content."
}
```