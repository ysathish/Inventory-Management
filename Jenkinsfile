pipeline {
    agent any

    environment {
        IMAGE_NAME = 'inventory-management-system-image'
        CONTAINER_NAME = 'inventory-management-system-container'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ysathish/Inventory-Management.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Create Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Stop and Remove Old Container') {
            steps {
                script {
                    sh 'docker rm -f $CONTAINER_NAME || true'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }

        stage('PostgreSQL Setup') {
            steps {
                script {
                    sh 'docker start my-postgres || true'
                }
            }
        }
    }
}
