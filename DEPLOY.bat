@echo off

rem Set variables
set SERVICE_NAME=fullstack-users
set IMAGE_NAME=wiljam/%SERVICE_NAME%
set PORT_NUMBER=8081

rem Build the Docker image
docker build -t %IMAGE_NAME%:latest .

rem Push the Docker image to Docker Hub (or your preferred registry)
docker push %IMAGE_NAME%:latest

rem Apply the Kubernetes deployment and service
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl rollout restart deployment %SERVICE_NAME%

echo Deployment completed, starting port-forward...

:portforward
echo Starting port-forward...
kubectl port-forward service/%SERVICE_NAME% %PORT_NUMBER%:%PORT_NUMBER%

rem Check the exit code of the port-forwarding command
if %errorlevel% neq 0 (
    echo Port-forwarding failed. Retrying in 3 seconds...
    timeout /nobreak /t 3 > nul
    goto portforward
)

echo Port-forwarding successful.
