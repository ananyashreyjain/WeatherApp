# WeatherApp

docker build -t weatherapp .
docker run -v $(pwd)/logs:/logs -p 8080:8080  weatherapp