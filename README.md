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

### Running Tests with Gradle Wrapper

Run the following command to execute all tests defined in the project:

```
./gradlew test
```
## Production Environment Considerations

In the production environment, several optimizations and infrastructure choices can enhance the performance,
scalability, and reliability of the URL shortening service.

### Distributed Key/Value NoSQL Database

For persistent storage of URL mappings across multiple nodes and high availability, consider using a distributed
key/value NoSQL database like Cassandra.

### Load Balancers

Utilize load balancers to distribute incoming requests among multiple instances of the application service. This ensures
efficient utilization of resources and improves overall reliability by reducing the impact of individual node failures.

### Distributed Cache (Redis)

Integrate a distributed caching solution such as Redis to cache frequently accessed or popular URLs. This helps reduce
latency and improves performance by serving cached content directly from memory.

### Container Orchestration with Kubernetes

Deploy the application services as containers orchestrated by Kubernetes. Kubernetes provides automated container
deployment, scaling, and management, making it suitable for deploying and managing containerized applications across
cloud providers or on-premises infrastructure.

### CI/CD pipelines
You can automate the process of building Docker image and publishing it to Docker registries using GitHub Actions.

## Architecture
![Architecture diagram in production](https://github.com/mehdichitforoosh/url-shortener/blob/main/scalable-production-diagram.svg)