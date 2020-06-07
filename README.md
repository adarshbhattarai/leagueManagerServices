# leagueManagerServices
Backend services for league manager

Application is deployed in elastic beanstalk

Application URL : http://leaguemanager-env.eba-mceparfa.us-east-2.elasticbeanstalk.com/

Endpoints:

User Services: ("/auth) <br>
POST Mappings : <br>
    url: <b>/login</b> <br>
    desc: Provide Username and password for login <br>
    Request:    
    
    {
        "username":<username>,
        "password":<password>
    }


Response:
    
    
    {
        "username": "<<username>>",
        "roles": [
            "<role1>",
            "<role2>"
        ],
        "accessToken": "<returns a access token>",
        "tokenType": "Bearer"
    }
    
    Error Response:
    
    {
        "timestamp": "2020-06-07 13:30:44",
        "status": 401,
        "error": "Unauthorized",
        "message": "Error: Unauthorized",
        "path": "/auth/login"
    }
    
url: <b>/register</b> <br>

desc: Provide Username password firstname and lastname and email for registration <br>

    Request:    
    
    {
        "firstName":<firstName>,
        "lastName":<lastName>,
        "username":"<username/psId>"
        "email":"<email>"
        "password":"<password>"
    }

Response:
    
    
    {
        "username": "<<username>>",
        "roles": [
            "<role1>",
            "<role2>"
        ],
        "accessToken": "<returns a access token>",
        "tokenType": "Bearer"
    }
    
    Error Response:
    
    {
       "timestamp": "<asd>",
       "status": 401,
       "error": "<asd>",
       "message": "error message",
       "path": "/auth/register"
    }
    
 