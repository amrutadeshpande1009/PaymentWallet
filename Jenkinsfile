pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']], // or '*/master'
                    extensions: [],
                    userRemoteConfigs: [[
                        url: 'https://github.com/amrutadeshpande1009/PaymentWallet.git',
                        credentialsId: 'aacbecd2-b608-4d02-9b31-35e3b16f9efe'
                    ]]
                )
            }
        }

        stage('Build') {
            steps {
                bat './mvnw clean install'
            }
        }

        stage('Test') {
            steps {
                bat './mvnw test'
            }
        }

        stage('Package') {
            steps {
                bat './mvnw package'
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
