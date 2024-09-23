# Url Shorten service

## Prerequisites
Docker installed on your machine.
## Getting Started
To get started with this project, follow these steps:

1. Build the Docker Image

Navigate to the root directory of the project where the `Dockerfile` is located.
```
docker build -t url-shorten-service .
```

This command builds a Docker image tagged as `url-shorten-service` using the `Dockerfile` in the current directory.

2. Run the Docker Container

Once the Docker image is built, you can run it using the following command:

```
docker run -p 8080:8080 url-shorten-service
```

This command starts a Docker container from the `url-shorten-service` image and maps port `8080` of the container to
port `8080` on your host machine.

### RESTful APIs for URL Shorten Service

1. API Endpoint for Creating Shortened URL

To create a shortened URL, send a POST request to the following endpoint:

```
POST /api/v1/short-urls
```

Request Body json:

```
{
   "longUrl": "https://www.example.com"
}
```

Example CURL Command:

```
curl -X POST "http://localhost:8080/api/v1/short-urls" -H "Content-Type: application/json" -d '{"longUrl":"https://www.example.com"}'
```

Upon successful creation, the API will return a JSON response containing the shortened URL:

```
{
    "id": 131779182080,
    "shortUrl": "2JqG18i"
}
```

2. API Endpoint for Retrieving the Original URL

To retrieve the original URL associated with a shortened URL, send a GET request to the following endpoint, with the
shortened url as part of the path:

```
GET /api/v1/short-urls/{shortUrl}
```

Example CURL Command:

```
curl -X GET "http://localhost:8080/api/v1/short-urls/2JqG18i"
```

Response:

```
{
    "id": 131779182080,
    "shortUrl": "2JqG18i",
    "longUrl": "https://www.example.com""
}
```